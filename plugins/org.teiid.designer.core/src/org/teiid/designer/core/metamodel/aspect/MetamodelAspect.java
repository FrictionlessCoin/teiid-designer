/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.metamodel.aspect;

/**
 * MetamodelAspect
 *
 * @since 8.0
 */
public interface MetamodelAspect {
    /**
     * Return the MetamodelEntity for this Metamodel Aspect
     * @return MetamodelEntity 
     */
    MetamodelEntity getMetamodelEntity();  
    
    /**
     * Return the String Aspect ID
     * @return String Aspect ID
     */
    String getID();
}
