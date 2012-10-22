/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.runtime.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.jcip.annotations.GuardedBy;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.wst.server.core.IServer;
import org.jboss.ide.eclipse.as.ui.views.as7.management.content.IContainerNode;
import org.jboss.ide.eclipse.as.ui.views.as7.management.content.IContentNode;
import org.teiid.designer.runtime.DqpPlugin;
import org.teiid.designer.runtime.ExecutionConfigurationEvent;
import org.teiid.designer.runtime.IExecutionConfigurationListener;
import org.teiid.designer.runtime.TeiidServer;
import org.teiid.designer.runtime.ui.DqpUiConstants;
import org.teiid.designer.runtime.ui.views.content.DataSourcesFolder;
import org.teiid.designer.runtime.ui.views.content.TeiidResourceNode;
import org.teiid.designer.runtime.ui.views.content.TranslatorsFolder;
import org.teiid.designer.runtime.ui.views.content.VdbsFolder;
import org.teiid.designer.ui.common.util.UiUtil;
import org.teiid.designer.ui.viewsupport.ModelerUiViewUtils;


/**
 * Class provides content and label information for ConnectorBindings and ModelInfos in ConnectorsView
 * 
 * @since 8.0
 */
public class TeiidServerContentProvider implements ITreeContentProvider {

    /** Represents a pending request in the tree. */
    static final Object PENDING = new Object();
    private ConcurrentMap<IContainerNode, Object> pendingUpdates = new ConcurrentHashMap<IContainerNode, Object>();
    private transient TreeViewer viewer;
    
    private static final String LOAD_ELEMENT_JOB = DqpUiConstants.UTIL.getString(TeiidServerContentProvider.class.getSimpleName() + ".jobName"); //$NON-NLS-1$
    
    /**
     * Loads content for specified nodes, then refreshes the content in the
     * tree.
     */
    private Job loadElementJob = new Job(LOAD_ELEMENT_JOB) {
        
        @Override
        public boolean shouldRun() {
            return pendingUpdates.size() > 0;
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            monitor.beginTask(LOAD_ELEMENT_JOB, IProgressMonitor.UNKNOWN);
            try {
                final List<IContainerNode> updated = new ArrayList<IContainerNode>(pendingUpdates.size());
                for (IContainerNode node : pendingUpdates.keySet()) {
                    try {
                        node.load();
                        updated.add(node);
                    } catch (Exception e) {
                    }
                    if (monitor.isCanceled()) {
                        pendingUpdates.keySet().removeAll(updated);
                        return Status.CANCEL_STATUS;
                    }
                }
                final Viewer viewer = TeiidServerContentProvider.this.viewer;
                if (viewer == null) {
                    pendingUpdates.keySet().clear();
                } else {
                    
                    viewer.getControl().getDisplay().asyncExec(new Runnable() {
                        
                        @Override
                        public void run() {
                            if (viewer.getControl().isDisposed()) {
                                return;
                            }
                            for (Object node : updated) {
                                pendingUpdates.remove(node);
                                if (viewer instanceof StructuredViewer) {
                                    ((StructuredViewer) viewer).refresh(node);
                                    if (node instanceof TeiidResourceNode && viewer instanceof CommonViewer) {
                                        ((CommonViewer) viewer).setExpandedState(node, true);
                                        ((CommonViewer) viewer).expandToLevel(node, 2);
                                    }
                                }
                                else
                                    viewer.refresh();
                            }
                        }
                    });
                }
            } finally {
                monitor.done();
            }
            return Status.OK_STATUS;
        }
    };
    
    private boolean showVDBs = true;
    private boolean showDataSources = true;
    private boolean showTranslators = true;

    /**
     * Servers that a connection can't be established. Value is the last time establishing a connection was tried.
     */
    @GuardedBy( "offlineServersLock" )
    private final Map<TeiidServer, Long> offlineServerMap = new HashMap<TeiidServer, Long>();

    private IExecutionConfigurationListener configListener = new IExecutionConfigurationListener() {
        
        @Override
        public void configurationChanged( final ExecutionConfigurationEvent event ) {
            UiUtil.runInSwtThread(new Runnable() {
                @Override
                public void run() {
                    if (viewer.getTree().isDisposed())
                        return;
                    
                    Tree tree = viewer.getTree();
                    TeiidResourceNode trn = null;
                    
                    // Preserve the selection
                    int selectionCount = tree.getSelectionCount();
                    ISelection currentSelection = viewer.getSelection();
                    
                    // Refresh the viewer
                    viewer.refresh();
                    
                    /*
                     * If the TeiidResourceNode is expanded then we will need to
                     * re-expand it since it has now been refreshed.
                     *
                     * If not found, then the node was never expanded so nothing
                     * need be done
                     */
                    TreePath[] expandedElements = viewer.getExpandedTreePaths();
                    for (TreePath o : expandedElements) {
                        Object element = o.getLastSegment();
                        
                        if (isTeiidResourceNode(element)) {
                            trn = (TeiidResourceNode) element;
                            break;
                        }
                        
                        Object[] children = TeiidServerContentProvider.this.getChildren(element);
                        for (Object child : children) {
                            if (isTeiidResourceNode(child)) {
                                trn = (TeiidResourceNode) child;
                                break;
                            }
                        }
                    
                        if (isTeiidResourceNode(trn)) {
                            break;
                        }
                    }
                    
                    // Re-expand the TeiidResourceNode if
                    // it was expanded previously
                    if (trn != null) {
                        viewer.setExpandedState(trn, true);
                        viewer.expandToLevel(trn, 2);
                    }

                    // Try and reset the selection
                    if (selectionCount == 1) {
                        viewer.setSelection(new StructuredSelection());
                        viewer.setSelection(currentSelection);
                    }
                        
                    // Refresh the Model Explorer too
                    ModelerUiViewUtils.refreshModelExplorerResourceNavigatorTree();    
                }
                
                private boolean isTeiidResourceNode(Object o) {
                    return o instanceof TeiidResourceNode;
                }
                
            }, false);
        }
    };
    
    /**
     * Content will include VDBs, translators, and data sources.
     * @since 5.0
     */
    public TeiidServerContentProvider() {
        super();
        
        // Wire as listener to server manager and to receive configuration changes
        DqpPlugin.getInstance().getServerManager().addListener(configListener);
    }

    /**
     * @param showVDBs 
     * @param showTranslators 
     * @param showDataSources 
     * 
     * @since 5.0
     */
    public TeiidServerContentProvider( boolean showVDBs,
                                  boolean showTranslators,
                                  boolean showDataSources ) {
        this();
        this.showVDBs = showVDBs;
        this.showTranslators = showTranslators;
        this.showDataSources = showDataSources;
    }
    
    /**
     * @return the showDataSources
     */
    public boolean isShowDataSources() {
        return this.showDataSources;
    }
    
    /**
     * Set show data sources flag
     * 
     * @param show
     */
    public void setShowDataSources(boolean show) {
        this.showDataSources = show;
    }
      
    /**
     * @return the showTranslators
     */
    public boolean isShowTranslators() {
        return this.showTranslators;
    }
    
    /**
     * Set show translators flag
     * 
     * @param show
     */
    public void setShowTranslators(boolean show) {
        this.showTranslators = show;
    }
    
    /**
     * @return the showVDBs
     */
    public boolean isShowVDBs() {
        return this.showVDBs;
    }
    
    /**
     * Set show vdbs flag
     * 
     * @param show
     */
    public void setShowVdbs(boolean show) {
        this.showVDBs = show;
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     * @since 4.2
     */
    @Override
    public Object[] getChildren( Object parentElement ) {
        if (parentElement == null)
            return new Object[0];
        
        if (parentElement instanceof IServer) {
            TeiidResourceNode node = TeiidResourceNode.getInstance((IServer) parentElement, this);
            return new Object[] { node };
            
        } else if (parentElement instanceof IContainerNode) {
            IContainerNode<?> container = (IContainerNode<?>) parentElement;
            if (pendingUpdates.containsKey(container)) {
                return new Object[] { PENDING };
            }
            List<? extends IContentNode<?>> children = container.getChildren();
            if (children == null) {
                pendingUpdates.putIfAbsent(container, PENDING);
                loadElementJob.schedule();
                return new Object[] { PENDING };
            }
            return children.toArray();
        }

        return new Object[0];
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     * @since 4.2
     */
    @Override
    public Object[] getElements( Object inputElement ) {
        return getChildren(inputElement);
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     * @since 4.2
     */
    @Override
    public Object getParent( Object element ) {
        if (element instanceof IContentNode) {
            Object parent = ((IContentNode<?>) element).getContainer();
            if (parent == null) {
                parent = ((IContentNode<?>) element).getServer();
            }
            return parent;
        }
        return null;
    }

    /**
     * @param server the server whose Data Source folder is being requested
     * @return the folder
     */
    public Object getDataSourceFolder(Object server) {
        for (Object child : getChildren(server)) {
            if (child instanceof DataSourcesFolder) {
                return child;
            }
        }
        
        return null;
    }
    
    /**
     * @param server the server whose Translators folder is being requested
     * @return the folder
     */
    public Object getTranslatorFolder(Object server) {
        for (Object child : getChildren(server)) {
            if (child instanceof TranslatorsFolder) {
                return child;
            }
        }
        
        return null;
    }
    
    /**
     * @param server the server whose VDBs folder is being requested
     * @return the folder
     */
    public Object getVdbFolder(Object server) {
        for (Object child : getChildren(server)) {
            if (child instanceof VdbsFolder) {
                return child;
            }
        }
        
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     * @since 4.2
     */
    @Override
    public boolean hasChildren( Object element ) {
        if (element instanceof IServer) {
            return true;
        } else if (element instanceof IContainerNode) {
            return true;
        }
        
        return false;
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     *      java.lang.Object)
     * @since 4.2
     */
    @Override
    public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
        if (viewer instanceof TreeViewer) {
            this.viewer = (TreeViewer) viewer;
        } else {
            this.viewer = null;
        }
    }
    

    @Override
    public void dispose() {
        viewer = null;
        loadElementJob.cancel();
        pendingUpdates.clear();
        
        DqpPlugin.getInstance().getServerManager().removeListener(configListener);
    }
}
