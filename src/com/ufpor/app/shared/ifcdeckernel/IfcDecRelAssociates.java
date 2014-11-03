package com.ufpor.app.shared.ifcdeckernel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcDecRelAssociates<T> extends IfcDecRelationship {
    public final static String TAG = IfcDecRelAssociates.class.getSimpleName();
    private transient static Logger logger = Logger.getLogger(TAG);

    public boolean isTypeSupported(Class clazz) {
        if (clazz == IfcDecObjectDefinition.class || clazz == IfcDecPropertyDefinition.class)  {
            return  true;
        }
        logger.log(Level.SEVERE, "IfcDefinitionSelect provides the option to either select an object or type object IfcObjectDefinition, or a property set template or property set, IfcPropertyDefinition.");
        return false;
    }
}
