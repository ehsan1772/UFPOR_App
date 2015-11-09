package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifckernel.IfcObjectDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecRelAggregates extends IfcDecRelDecomposes {
    public final static String TAG = IfcDecRelAggregates.class.getSimpleName();
    private static transient Logger logger = Logger.getLogger(TAG);
    private IfcDecObjectDefinition relatingObject;
    private List<IfcDecObjectDefinition> relatedObjects;

    public IfcDecRelAggregates() {
        relatedObjects = new ArrayList<IfcDecObjectDefinition>();
    }

    public IfcDecRelAggregates(IfcDecObjectDefinition relatingObject) {
        this.relatingObject = relatingObject;
        relatedObjects = new ArrayList<IfcDecObjectDefinition>();
    }

    public IfcDecRelAggregates(IfcDecObjectDefinition relatingObject, List<IfcDecObjectDefinition> relatedObjects) {
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

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        return null;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        //IfcRoot
        String globalId = getGlobalId().getValue();
        String ownerHistory = "*";
        String name = getName();
        String description = getDescription();

        //IfcRelAggregates
        String relatingObject = String.valueOf(fileManager.getNumber(getOwner()));
        String relatedObjects = fileManager.getNumberString(getList());

        String result = String.format(Constants.IFCRELAGGREGATES,
                globalId,
                ownerHistory,
                name,
                description,
                relatingObject,
                relatedObjects);

        return result;
    }
}