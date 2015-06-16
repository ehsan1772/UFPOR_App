package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObjectDefinition;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecRelNests extends IfcDecRelDecomposes {
    public final static String TAG = IfcDecRelNests.class.getSimpleName();
    private static Logger logger = Logger.getLogger(TAG);
    private IfcDecObjectDefinition relatingObject;
    private List<IfcDecObjectDefinition> relatedObjects;

    @Override
    public IfcDecRoot getOwner() {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }

    public IfcDecRelNests() {
        relatedObjects = new ArrayList<IfcDecObjectDefinition>();
    }

    @Override
    protected void initialize(IfcDecRoot owner) {

    }

    @Override
    public void add(IfcDecRoot item) {
        getList().add(item);
    }

    @Override
    public void addAll(List item) {
        getList().addAll(item);
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

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return null;
    }

}
