/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.xml.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.xsd.XSDComponent;
import org.teiid.designer.metamodels.xml.BuildStatus;
import org.teiid.designer.metamodels.xml.ChoiceErrorMode;
import org.teiid.designer.metamodels.xml.ChoiceOption;
import org.teiid.designer.metamodels.xml.XmlChoice;
import org.teiid.designer.metamodels.xml.XmlDocumentEntity;
import org.teiid.designer.metamodels.xml.XmlDocumentPackage;
import org.teiid.designer.metamodels.xml.XmlEntityHolder;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Xml Choice</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.teiid.designer.metamodels.xml.impl.XmlChoiceImpl#getDefaultErrorMode <em>Default Error Mode</em>}</li>
 * <li>{@link org.teiid.designer.metamodels.xml.impl.XmlChoiceImpl#getDefaultOption <em>Default Option</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 *
 * @since 8.0
 */
public class XmlChoiceImpl extends XmlContainerNodeImpl implements XmlChoice {
    /**
     * The default value of the '{@link #getDefaultErrorMode() <em>Default Error Mode</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDefaultErrorMode()
     * @generated
     * @ordered
     */
    protected static final ChoiceErrorMode DEFAULT_ERROR_MODE_EDEFAULT = ChoiceErrorMode.THROW_LITERAL;

    /**
     * The cached value of the '{@link #getDefaultErrorMode() <em>Default Error Mode</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDefaultErrorMode()
     * @generated
     * @ordered
     */
    protected ChoiceErrorMode defaultErrorMode = DEFAULT_ERROR_MODE_EDEFAULT;

    /**
     * The cached value of the '{@link #getDefaultOption() <em>Default Option</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDefaultOption()
     * @generated
     * @ordered
     */
    protected ChoiceOption defaultOption = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected XmlChoiceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ChoiceOption basicGetDefaultOption() {
        return defaultOption;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDefaultOption( final ChoiceOption newDefaultOption,
                                                    NotificationChain msgs ) {
        final ChoiceOption oldDefaultOption = defaultOption;
        defaultOption = newDefaultOption;
        if (eNotificationRequired()) {
            final ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                                                                         XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION,
                                                                         oldDefaultOption, newDefaultOption);
            if (msgs == null) msgs = notification;
            else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainer( final NotificationChain msgs ) {
        if (eContainerFeatureID >= 0) switch (eContainerFeatureID) {
            case XmlDocumentPackage.XML_CHOICE__PARENT:
                return eContainer.eInverseRemove(this, XmlDocumentPackage.XML_ENTITY_HOLDER__ENTITIES, XmlEntityHolder.class, msgs);
            default:
                return eDynamicBasicRemoveFromContainer(msgs);
        }
        return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( final EStructuralFeature eFeature,
                        final boolean resolve ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case XmlDocumentPackage.XML_CHOICE__ENTITIES:
                return getEntities();
            case XmlDocumentPackage.XML_CHOICE__CHOICE_CRITERIA:
                return getChoiceCriteria();
            case XmlDocumentPackage.XML_CHOICE__CHOICE_ORDER:
                return new Integer(getChoiceOrder());
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_FOR:
                if (resolve) return getDefaultFor();
                return basicGetDefaultFor();
            case XmlDocumentPackage.XML_CHOICE__BUILD_STATE:
                return getBuildState();
            case XmlDocumentPackage.XML_CHOICE__EXCLUDE_FROM_DOCUMENT:
                return isExcludeFromDocument() ? Boolean.TRUE : Boolean.FALSE;
            case XmlDocumentPackage.XML_CHOICE__MIN_OCCURS:
                return new Integer(getMinOccurs());
            case XmlDocumentPackage.XML_CHOICE__MAX_OCCURS:
                return new Integer(getMaxOccurs());
            case XmlDocumentPackage.XML_CHOICE__XSD_COMPONENT:
                if (resolve) return getXsdComponent();
                return basicGetXsdComponent();
            case XmlDocumentPackage.XML_CHOICE__PARENT:
                return getParent();
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_ERROR_MODE:
                return getDefaultErrorMode();
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION:
                if (resolve) return getDefaultOption();
                return basicGetDefaultOption();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd( final InternalEObject otherEnd,
                                          final int featureID,
                                          final Class baseClass,
                                          NotificationChain msgs ) {
        if (featureID >= 0) switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
            case XmlDocumentPackage.XML_CHOICE__ENTITIES:
                return ((InternalEList)getEntities()).basicAdd(otherEnd, msgs);
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_FOR:
                if (defaultFor != null) msgs = ((InternalEObject)defaultFor).eInverseRemove(this,
                                                                                            XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION,
                                                                                            XmlChoice.class,
                                                                                            msgs);
                return basicSetDefaultFor((XmlChoice)otherEnd, msgs);
            case XmlDocumentPackage.XML_CHOICE__PARENT:
                if (eContainer != null) msgs = eBasicRemoveFromContainer(msgs);
                return eBasicSetContainer(otherEnd, XmlDocumentPackage.XML_CHOICE__PARENT, msgs);
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION:
                if (defaultOption != null) msgs = ((InternalEObject)defaultOption).eInverseRemove(this,
                                                                                                  XmlDocumentPackage.CHOICE_OPTION__DEFAULT_FOR,
                                                                                                  ChoiceOption.class,
                                                                                                  msgs);
                return basicSetDefaultOption((ChoiceOption)otherEnd, msgs);
            default:
                return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
        }
        if (eContainer != null) msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove( final InternalEObject otherEnd,
                                             final int featureID,
                                             final Class baseClass,
                                             final NotificationChain msgs ) {
        if (featureID >= 0) switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
            case XmlDocumentPackage.XML_CHOICE__ENTITIES:
                return ((InternalEList)getEntities()).basicRemove(otherEnd, msgs);
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_FOR:
                return basicSetDefaultFor(null, msgs);
            case XmlDocumentPackage.XML_CHOICE__PARENT:
                return eBasicSetContainer(null, XmlDocumentPackage.XML_CHOICE__PARENT, msgs);
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION:
                return basicSetDefaultOption(null, msgs);
            default:
                return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet( final EStructuralFeature eFeature ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case XmlDocumentPackage.XML_CHOICE__ENTITIES:
                return entities != null && !entities.isEmpty();
            case XmlDocumentPackage.XML_CHOICE__CHOICE_CRITERIA:
                return CHOICE_CRITERIA_EDEFAULT == null ? choiceCriteria != null : !CHOICE_CRITERIA_EDEFAULT.equals(choiceCriteria);
            case XmlDocumentPackage.XML_CHOICE__CHOICE_ORDER:
                return choiceOrder != CHOICE_ORDER_EDEFAULT;
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_FOR:
                return defaultFor != null;
            case XmlDocumentPackage.XML_CHOICE__BUILD_STATE:
                return buildState != BUILD_STATE_EDEFAULT;
            case XmlDocumentPackage.XML_CHOICE__EXCLUDE_FROM_DOCUMENT:
                return excludeFromDocument != EXCLUDE_FROM_DOCUMENT_EDEFAULT;
            case XmlDocumentPackage.XML_CHOICE__MIN_OCCURS:
                return getMinOccurs() != MIN_OCCURS_EDEFAULT;
            case XmlDocumentPackage.XML_CHOICE__MAX_OCCURS:
                return getMaxOccurs() != MAX_OCCURS_EDEFAULT;
            case XmlDocumentPackage.XML_CHOICE__XSD_COMPONENT:
                return xsdComponent != null;
            case XmlDocumentPackage.XML_CHOICE__PARENT:
                return getParent() != null;
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_ERROR_MODE:
                return defaultErrorMode != DEFAULT_ERROR_MODE_EDEFAULT;
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION:
                return defaultOption != null;
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet( final EStructuralFeature eFeature,
                      final Object newValue ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case XmlDocumentPackage.XML_CHOICE__ENTITIES:
                getEntities().clear();
                getEntities().addAll((Collection)newValue);
                return;
            case XmlDocumentPackage.XML_CHOICE__CHOICE_CRITERIA:
                setChoiceCriteria((String)newValue);
                return;
            case XmlDocumentPackage.XML_CHOICE__CHOICE_ORDER:
                setChoiceOrder(((Integer)newValue).intValue());
                return;
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_FOR:
                setDefaultFor((XmlChoice)newValue);
                return;
            case XmlDocumentPackage.XML_CHOICE__BUILD_STATE:
                setBuildState((BuildStatus)newValue);
                return;
            case XmlDocumentPackage.XML_CHOICE__EXCLUDE_FROM_DOCUMENT:
                setExcludeFromDocument(((Boolean)newValue).booleanValue());
                return;
            case XmlDocumentPackage.XML_CHOICE__XSD_COMPONENT:
                setXsdComponent((XSDComponent)newValue);
                return;
            case XmlDocumentPackage.XML_CHOICE__PARENT:
                setParent((XmlEntityHolder)newValue);
                return;
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_ERROR_MODE:
                setDefaultErrorMode((ChoiceErrorMode)newValue);
                return;
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION:
                setDefaultOption((ChoiceOption)newValue);
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
    protected EClass eStaticClass() {
        return XmlDocumentPackage.eINSTANCE.getXmlChoice();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset( final EStructuralFeature eFeature ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case XmlDocumentPackage.XML_CHOICE__ENTITIES:
                getEntities().clear();
                return;
            case XmlDocumentPackage.XML_CHOICE__CHOICE_CRITERIA:
                setChoiceCriteria(CHOICE_CRITERIA_EDEFAULT);
                return;
            case XmlDocumentPackage.XML_CHOICE__CHOICE_ORDER:
                setChoiceOrder(CHOICE_ORDER_EDEFAULT);
                return;
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_FOR:
                setDefaultFor((XmlChoice)null);
                return;
            case XmlDocumentPackage.XML_CHOICE__BUILD_STATE:
                setBuildState(BUILD_STATE_EDEFAULT);
                return;
            case XmlDocumentPackage.XML_CHOICE__EXCLUDE_FROM_DOCUMENT:
                setExcludeFromDocument(EXCLUDE_FROM_DOCUMENT_EDEFAULT);
                return;
            case XmlDocumentPackage.XML_CHOICE__XSD_COMPONENT:
                setXsdComponent((XSDComponent)null);
                return;
            case XmlDocumentPackage.XML_CHOICE__PARENT:
                setParent((XmlEntityHolder)null);
                return;
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_ERROR_MODE:
                setDefaultErrorMode(DEFAULT_ERROR_MODE_EDEFAULT);
                return;
            case XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION:
                setDefaultOption((ChoiceOption)null);
                return;
        }
        eDynamicUnset(eFeature);
    }

    protected List getChoiceOptions() {
        final List choices = new LinkedList();

        // Iterate through the owned entities, looking for the existing options ...
        final Iterator iter = getEntities().iterator();
        while (iter.hasNext()) {
            final XmlDocumentEntity xmlEntity = (XmlDocumentEntity)iter.next();
            if (xmlEntity != null) choices.add(xmlEntity);
        }

        return choices;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public ChoiceErrorMode getDefaultErrorMode() {
        return defaultErrorMode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public ChoiceOption getDefaultOption() {
        if (defaultOption != null && defaultOption.eIsProxy()) {
            final ChoiceOption oldDefaultOption = defaultOption;
            defaultOption = (ChoiceOption)eResolveProxy((InternalEObject)defaultOption);
            if (defaultOption != oldDefaultOption) if (eNotificationRequired()) eNotify(new ENotificationImpl(
                                                                                                              this,
                                                                                                              Notification.RESOLVE,
                                                                                                              XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION,
                                                                                                              oldDefaultOption,
                                                                                                              defaultOption));
        }
        return defaultOption;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
	public List getOrderedChoiceOptions() {
        final List choices = getChoiceOptions();

        // Sort the list using the comparator ...
        Collections.sort(choices, new ChoiceOptionSorter());

        return choices;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public List getOrderedChoiceOptionsGen() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public void setDefaultErrorMode( final ChoiceErrorMode newDefaultErrorMode ) {
        final ChoiceErrorMode oldDefaultErrorMode = defaultErrorMode;
        defaultErrorMode = newDefaultErrorMode == null ? DEFAULT_ERROR_MODE_EDEFAULT : newDefaultErrorMode;
        if (eNotificationRequired()) eNotify(new ENotificationImpl(this, Notification.SET,
                                                                   XmlDocumentPackage.XML_CHOICE__DEFAULT_ERROR_MODE,
                                                                   oldDefaultErrorMode, defaultErrorMode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public void setDefaultOption( final ChoiceOption newDefaultOption ) {
        if (newDefaultOption != defaultOption) {
            NotificationChain msgs = null;
            if (defaultOption != null) msgs = ((InternalEObject)defaultOption).eInverseRemove(this,
                                                                                              XmlDocumentPackage.CHOICE_OPTION__DEFAULT_FOR,
                                                                                              ChoiceOption.class,
                                                                                              msgs);
            if (newDefaultOption != null) msgs = ((InternalEObject)newDefaultOption).eInverseAdd(this,
                                                                                                 XmlDocumentPackage.CHOICE_OPTION__DEFAULT_FOR,
                                                                                                 ChoiceOption.class,
                                                                                                 msgs);
            msgs = basicSetDefaultOption(newDefaultOption, msgs);
            if (msgs != null) msgs.dispatch();
        } else if (eNotificationRequired()) eNotify(new ENotificationImpl(this, Notification.SET,
                                                                          XmlDocumentPackage.XML_CHOICE__DEFAULT_OPTION,
                                                                          newDefaultOption, newDefaultOption));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
	public void setOrderedChoiceOptions( final List options ) {
        // Iterate through the options, and set the indexes ...
        int index = 0;
        final Iterator iter = options.iterator();
        while (iter.hasNext()) {
            final ChoiceOption option = (ChoiceOption)iter.next();
            if (option != null) // Make sure that the option is a child of this choice ...
            if (option.eContainer().equals(this)) {
                option.setChoiceOrder(index);
                ++index;
            }
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setOrderedChoiceOptionsGen( final List options ) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (defaultErrorMode: "); //$NON-NLS-1$
        result.append(defaultErrorMode);
        result.append(')');
        return result.toString();
    }

    /**
     * This Comparator orders {@link ChoiceOption} instances by their {@link ChoiceOption#getChoiceOrder()} attribute value. The
     * value may be -1, which means that the order is implicit in the order of the option in its container; and because this may be
     * a problem in some cases (see defect 12578) the order may need to be explicitly set to something other than -1.
     * 
     * @since 4.2
     */
    protected class ChoiceOptionSorter implements Comparator {
        @Override
		public int compare( final Object o1,
                            final Object o2 ) {
            if (o1 instanceof ChoiceOption && o2 instanceof ChoiceOption) {
                final ChoiceOption co1 = (ChoiceOption)o1;
                final ChoiceOption co2 = (ChoiceOption)o2;
                final int order1 = co1.getChoiceOrder();
                final int order2 = co2.getChoiceOrder();
                if (order1 == -1) {
                    if (order2 == -1) // Both are undefined, so signal no change in order ...
                    return 0;
                    return 1; // o2 has defined order, but o1 doesn't, so put o2 first
                }

                if (order2 == -1) // o1 has defined order, but o2 doesn't, so put o1 first
                return -1;

                // Both are defined, so compare the order ...
                return order1 - order2;

            }
            return 0;
        }
    }

} // XmlChoiceImpl
