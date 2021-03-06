/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.ui.favorites.actions;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.teiid.designer.ui.PluginConstants;
import org.teiid.designer.ui.UiConstants;
import org.teiid.designer.ui.UiPlugin;
import org.teiid.designer.ui.actions.ModelObjectAction;
import org.teiid.designer.ui.common.eventsupport.SelectionUtilities;
import org.teiid.designer.ui.common.util.UiUtil;
import org.teiid.designer.ui.common.util.WidgetUtil;
import org.teiid.designer.ui.favorites.FavoritesView;


/**
 * @since 8.0
 */
public class AddToMetadataFavoritesAction extends ModelObjectAction {

    
    /**
     * Construct an instance of NewCustomDiagramAction.
     */
    public AddToMetadataFavoritesAction() {
        super(UiPlugin.getDefault());
        setText(UiConstants.Util.getString("AddToMetadataFavoritesAction.label")); //$NON-NLS-1$
        setImageDescriptor(
        		AbstractUIPlugin.imageDescriptorFromPlugin(UiConstants.PLUGIN_ID, PluginConstants.Images.METADATA_FAVORITES));
    }

    /**
     * Construct an instance of NewCustomDiagramAction.
     * 
     * @param theStyle
     */
    public AddToMetadataFavoritesAction( int theStyle ) {
        super(UiPlugin.getDefault(), theStyle);
    }


    @Override
    public void doRun() {
        Collection objs = new ArrayList(SelectionUtilities.getSelectedEObjects(getSelection()));
        try {
            IViewPart viewPart = UiUtil.getWorkbenchPage().showView(UiConstants.Extensions.FAVORITES_VIEW_ID);
            if (viewPart instanceof FavoritesView) {
                UiPlugin.getDefault().getEObjectCache().addAll(objs);
//                ((FavoritesView)viewPart).addToClipboard(objs);
            }
        } catch (final PartInitException err) {
            UiConstants.Util.log(err);
            WidgetUtil.showError(err.getLocalizedMessage());
        }
    }

    /**
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(IWorkbenchPart, ISelection)
     * @since 4.0
     */
    @Override
    public void selectionChanged( final IWorkbenchPart part,
                                  final ISelection selection ) {
        super.selectionChanged(part, selection);
        determineEnablement();
    }
    
    /**
     * @since 4.0
     */
    private void determineEnablement() {
        boolean enable = false;
        
        // check to make sure the selection is good (should be, the plugin
        //  settings pretty much mandate it:
        if (!SelectionUtilities.getSelectedEObjects(getSelection()).isEmpty() ) {
            // yes, the selection has an EObject.
            // defect 15111 - disable this menu in favorites view
            // check what view we came from:
            IWorkbenchPart iwp = getActiveWorkbenchPart();

            if (!(iwp instanceof FavoritesView)) {
                // was not the favorites, enable:
                enable = true;
            } // endif -- not from FavoritesView
        } // endif -- eobject selected

        setEnabled(enable);
    }

    /**
     * @return the currently active IWorkbenchPart, presumably the view
     *   triggering the popup.
     */
    private static IWorkbenchPart getActiveWorkbenchPart() {
        IWorkbench        iwb = UiPlugin.getDefault().getWorkbench();
        IWorkbenchWindow iwbw = iwb.getActiveWorkbenchWindow();
        return iwbw.getActivePage().getActivePart();
    }
    
    /* (non-Javadoc)
     * @see org.teiid.designer.ui.actions.ModelObjectAction#requiresEditorForRun()
     */
    @Override
    protected boolean requiresEditorForRun() {
        return false;
    }
}
