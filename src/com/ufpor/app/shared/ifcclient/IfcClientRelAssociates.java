package com.ufpor.app.shared.ifcclient;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcClientRelAssociates<T> extends IfcClientRelationship {
    public final static String TAG = IfcClientRelAssociates.class.getSimpleName();
    private transient static Logger logger = Logger.getLogger(TAG);

    public boolean isTypeSupported(Class clazz) {
        if (clazz == IfcClientObjectDefinition.class || clazz == IfcClientPropertyDefinition.class)  {
            return  true;
        }
        logger.log(Level.SEVERE, "IfcDefinitionSelect provides the option to either select an object or type object IfcObjectDefinition, or a property set template or property set, IfcPropertyDefinition.");
        return false;
    }
}
