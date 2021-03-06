/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.modelgenerator.xml.modelextension;

/**
 * @since 8.0
 */
public class FileExtensionReplaceAction extends BaseExtensionReplaceAction {

    @Override
    public ExtensionManager getExtensionManager() {
        return new XMLFileExtensionManager();
    }

    @Override
    public String getExtensionName() {
        return "XMLFileConnectorExtensions"; //$NON-NLS-1$
    }
}
