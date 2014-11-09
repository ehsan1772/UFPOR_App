package com.ufpor.app.shared.ifcclient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientRelAggregates extends IfcClientRelDecomposes {
    public final static String TAG = IfcClientRelAggregates.class.getSimpleName();
    private transient static Logger logger = Logger.getLogger(TAG);
    private IfcClientObjectDefinition relatingObject;
    private List<IfcClientObjectDefinition> relatedObjects;

    public IfcClientRelAggregates() {
        relatedObjects = new ArrayList<IfcClientObjectDefinition>();
    }

    public IfcClientRelAggregates(IfcClientObjectDefinition relatingObject) {
        this.relatingObject = relatingObject;
        relatedObjects = new ArrayList<IfcClientObjectDefinition>();
    }

    public IfcClientRelAggregates(IfcClientObjectDefinition relatingObject, List<IfcClientObjectDefinition> relatedObjects) {
        this.relatingObject = relatingObject;
        if (!relatedObjects.contains(relatingObject)) {
            this.relatedObjects = relatedObjects;
        } else {
            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
        }

    }

    public boolean addRelatedObject(IfcClientObjectDefinition relatedObject) {
        if (!relatedObjects.contains(relatingObject)) {
            relatedObjects.add(relatedObject);
            return true;
        } else {
            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
            return false;
        }

    }

}
