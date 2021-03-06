/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.workspace;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.core.ModelerCore;
import org.teiid.designer.core.util.ModelResourceContainerFactory;
import org.teiid.designer.metamodels.core.Annotation;


/**
 * ModelObjectAnnotationsImpl
 *
 * @since 8.0
 */
public class ModelObjectAnnotationsImpl extends NonOpenableModelWorkspaceItemImpl implements ModelObjectAnnotations {

    protected static final String DEFAULT_NAME = ModelerCore.Util.getString("ModelObjectAnnotationsImpl.defaultName"); //$NON-NLS-1$

    /**
     * Constructor needed for test cases.
     */
    ModelObjectAnnotationsImpl() {
        super(ANNOTATIONS, null, DEFAULT_NAME);
    }

    /**
     * Construct an instance of ModelObjectAnnotationsImpl.
     * 
     * @since 4.0
     */
    public ModelObjectAnnotationsImpl( final ModelWorkspaceItem parent ) {
        super(ANNOTATIONS, parent, DEFAULT_NAME);
    }

    public ModelResource getModelResource() {
        return (ModelResource)this.getParent();
    }

    /**
     * @see org.teiid.designer.core.workspace.ModelWorkspaceItem#getResource()
     */
    @Override
	public IResource getResource() {
        return this.getModelResource().getResource();
    }

    /**
     * @see org.teiid.designer.core.workspace.ModelWorkspaceItem#getUnderlyingResource()
     */
    @Override
	public IResource getUnderlyingResource() {
        return getResource();
    }

    /**
     * @see org.teiid.designer.core.workspace.ModelWorkspaceItem#getPath()
     */
    @Override
	public IPath getPath() {
        return this.getModelResource().getPath();
    }

    /**
     * @see org.teiid.designer.core.workspace.ModelWorkspaceItem#hasChildren()
     */
    @Override
    public boolean hasChildren() {
        return false;
    }

    protected ModelBufferImpl getModelBuffer() throws ModelWorkspaceException {
        final ModelResourceImpl modelResourceImpl = (ModelResourceImpl)this.getModelResource();
        return (ModelBufferImpl)modelResourceImpl.getBuffer(); // loads if req'd
    }

    /**
     * @see org.teiid.designer.core.workspace.ModelObjectAnnotations#getAnnotation(org.eclipse.emf.ecore.EObject)
     * @since 4.3
     */
    @Override
	public Annotation getAnnotation( final EObject target ) throws ModelWorkspaceException {
        return getModelBuffer().getModelContents().getAnnotation(target);
    }

    /**
     * This method provides a robust method to completely create a new annotation and add it correctly to a ModelResource. This is
     * accomplished by specifically calling a ModelResourceContainerFactory method which requires a Resource. This call will
     * assert this requirement. If user desires to create an annotation with fewer restrictions... see
     * org.teiid.designer.core.util.ModelResourceContainerFactory.createNewAnnotation(...) methods.
     * 
     * @see org.teiid.designer.core.workspace.ModelObjectAnnotations#createNewAnnotation(org.eclipse.emf.ecore.EObject)
     * @since 4.3
     */
    @Override
	public Annotation createNewAnnotation( final EObject target ) throws ModelWorkspaceException {
        return ModelResourceContainerFactory.createNewAnnotation(target, getModelResource().getEmfResource());
    }

    /**
     * This method provides a robust method to completely delete an annotation and remove it correctly from a ModelResource. This
     * is accomplished by specifically calling a ModelResourceContainerFactory method which requires an Resource.
     * 
     * @see org.teiid.designer.core.workspace.ModelObjectAnnotations#delete(org.teiid.designer.metamodels.core.Annotation)
     * @since 4.3
     */
    @Override
	public boolean delete( final Annotation annotation ) {
        return ModelResourceContainerFactory.deleteAnnotation(annotation);
    }

    /**
     * @see org.teiid.designer.core.workspace.ModelWorkspaceItem#getOpenable()
     */
    @Override
    public Openable getOpenable() {
        return getOpenableParent();
    }

}
