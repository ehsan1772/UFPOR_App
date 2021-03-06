package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifckernel.IfcObjectDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientRelDeclares<T> extends IfcClientRelationship {
    public final static String TAG = IfcClientRelDeclares.class.getSimpleName();
    private static Logger logger = Logger.getLogger(TAG);
    private IfcClientObjectDefinition relatingObject;
    private List<T> relatedObjects;


    public IfcClientRelDeclares(Class clazz) {
        if (isTypeSupported(clazz)) {
            relatedObjects = new ArrayList<T>();
        }
    }

    public IfcClientRelDeclares(IfcClientObjectDefinition relatingObject, Class clazz) {
        this.relatingObject = relatingObject;
        relatedObjects = new ArrayList<T>();
    }

    public IfcClientRelDeclares(IfcClientObjectDefinition relatingObject, List<T> relatedObjects, Class clazz) {
        this.relatingObject = relatingObject;
        if (!relatedObjects.contains(relatingObject)) {
            this.relatedObjects = relatedObjects;
        } else {
            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
        }

    }

    public boolean addRelatedObject(T relatedObject) {
        if (!relatedObjects.contains(relatingObject)) {
            relatedObjects.add(relatedObject);
            return true;
        } else {
            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
            return false;
        }

    }

    private boolean isTypeSupported(Class clazz) {
        if (clazz == IfcObjectDefinition.class || clazz == IfcClientPropertyDefinition.class)  {
            return  true;
        }
        logger.log(Level.SEVERE, "IfcDefinitionSelect provides the option to either select an object or type object IfcObjectDefinition, or a property set template or property set, IfcPropertyDefinition.");
        return false;
    }
}
