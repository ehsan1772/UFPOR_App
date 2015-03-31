package com.ufpor.app.shared.ifckernel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcRelAggregates extends IfcRelDecomposes {
    public final static String TAG = IfcRelAggregates.class.getSimpleName();
    private static transient Logger logger = Logger.getLogger(TAG);
    private IfcObjectDefinition relatingObject;
    private List<IfcObjectDefinition> relatedObjects;

    public IfcRelAggregates() {
        relatedObjects = new ArrayList<IfcObjectDefinition>();
    }

    public IfcRelAggregates(IfcObjectDefinition relatingObject) {
        this.relatingObject = relatingObject;
        relatedObjects = new ArrayList<IfcObjectDefinition>();
    }

    public IfcRelAggregates(IfcObjectDefinition relatingObject, List<IfcObjectDefinition> relatedObjects) {
        this.relatingObject = relatingObject;
        if (!relatedObjects.contains(relatingObject)) {
            this.relatedObjects = relatedObjects;
        } else {
            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
        }

    }

    public boolean addRelatedObject(IfcObjectDefinition relatedObject) {
        if (!relatedObjects.contains(relatingObject)) {
            relatedObjects.add(relatedObject);
            return true;
        } else {
            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
            return false;
        }

    }


}
