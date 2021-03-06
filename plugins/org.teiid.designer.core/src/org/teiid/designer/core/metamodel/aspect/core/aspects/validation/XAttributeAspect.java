/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.metamodel.aspect.core.aspects.validation;

import org.teiid.designer.core.metamodel.aspect.MetamodelEntity;
import org.teiid.designer.core.validation.ValidationRuleSet;


/**
 * SchemaAspect
 *
 * @since 8.0
 */
public class XAttributeAspect extends CoreEntityAspect {

    /**
     * Construct an instance of ModelImportAspect.
     * @param entity
     */
    public XAttributeAspect(MetamodelEntity entity) {
        super(entity);
    }

	/**
	 * Get all the validation rules for ModelImport.
	 */
	@Override
    public ValidationRuleSet getValidationRules() {
        addRule(XATTRIBUTE_NAME_RULE);
        addRule(XATTRIBUTE_FEATURE_RULE);
        addRule(XATTRIBUTE_MAX_OCCURS_RULE);
        addRule(XATTRIBUTE_DEFAULT_VALUE_DATATYPE_RULE);
		return super.getValidationRules();		
	}
}
