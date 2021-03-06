/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.transformation.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.teiid.designer.metamodels.transformation.MappingClassSet;
import org.teiid.designer.metamodels.transformation.MappingClassSetContainer;
import org.teiid.designer.metamodels.transformation.TransformationPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Mapping Class Set Container</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.teiid.designer.metamodels.transformation.impl.MappingClassSetContainerImpl#getMappingClassSets <em>Mapping Class
 * Sets</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 *
 * @since 8.0
 */
public class MappingClassSetContainerImpl extends EObjectImpl implements MappingClassSetContainer {

    /**
     * The cached value of the '{@link #getMappingClassSets() <em>Mapping Class Sets</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMappingClassSets()
     * @generated
     * @ordered
     */
    protected EList mappingClassSets = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MappingClassSetContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.eINSTANCE.getMappingClassSetContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public EList getMappingClassSets() {
        if (mappingClassSets == null) {
            mappingClassSets = new EObjectContainmentEList(MappingClassSet.class, this,
                                                           TransformationPackage.MAPPING_CLASS_SET_CONTAINER__MAPPING_CLASS_SETS);
        }
        return mappingClassSets;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
                                             int featureID,
                                             Class baseClass,
                                             NotificationChain msgs ) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TransformationPackage.MAPPING_CLASS_SET_CONTAINER__MAPPING_CLASS_SETS:
                    return ((InternalEList)getMappingClassSets()).basicRemove(otherEnd, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( EStructuralFeature eFeature,
                        boolean resolve ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TransformationPackage.MAPPING_CLASS_SET_CONTAINER__MAPPING_CLASS_SETS:
                return getMappingClassSets();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet( EStructuralFeature eFeature,
                      Object newValue ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TransformationPackage.MAPPING_CLASS_SET_CONTAINER__MAPPING_CLASS_SETS:
                getMappingClassSets().clear();
                getMappingClassSets().addAll((Collection)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset( EStructuralFeature eFeature ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TransformationPackage.MAPPING_CLASS_SET_CONTAINER__MAPPING_CLASS_SETS:
                getMappingClassSets().clear();
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet( EStructuralFeature eFeature ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TransformationPackage.MAPPING_CLASS_SET_CONTAINER__MAPPING_CLASS_SETS:
                return mappingClassSets != null && !mappingClassSets.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} // MappingClassSetContainerImpl
