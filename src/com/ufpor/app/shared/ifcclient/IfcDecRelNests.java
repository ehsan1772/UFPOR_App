package com.ufpor.app.shared.ifcclient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecRelNests extends IfcDecRelDecomposes {
    public final static String TAG = IfcDecRelNests.class.getSimpleName();
    private transient static Logger logger = Logger.getLogger(TAG);
    private IfcDecObjectDefinition relatingObject;
    private List<IfcDecObjectDefinition> relatedObjects;

    public IfcDecRelNests() {
        relatedObjects = new ArrayList<IfcDecObjectDefinition>();
    }

    public IfcDecRelNests(IfcDecObjectDefinition relatingObject) {
        this.relatingObject = relatingObject;
        relatedObjects = new ArrayList<IfcDecObjectDefinition>();
    }

    public IfcDecRelNests(IfcDecObjectDefinition relatingObject, List<IfcDecObjectDefinition> relatedObjects) {
        this.relatingObject = relatingObject;
        if (!relatedObjects.contains(relatingObject)) {
            this.relatedObjects = relatedObjects;
        } else {
            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
        }

    }

    public boolean addRelatedObject(IfcDecObjectDefinition relatedObject) {
        if (!relatedObjects.contains(relatingObject)) {
            relatedObjects.add(relatedObject);
            return true;
        } else {
            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
            return false;
        }

    }

}
