package com.ufpor.app.shared.ifc.ifcKernel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcRelAssociates<T> extends IfcRelationship {
    public final static String TAG = IfcRelAssociates.class.getSimpleName();
    private static Logger logger = Logger.getLogger(TAG);

    public boolean isTypeSupported(Class clazz) {
        if (clazz == IfcObjectDefinition.class || clazz == IfcPropertyDefinition.class)  {
            return  true;
        }
        logger.log(Level.SEVERE, "IfcDefinitionSelect provides the option to either select an object or type object IfcObjectDefinition, or a property set template or property set, IfcPropertyDefinition.");
        return false;
    }
}
