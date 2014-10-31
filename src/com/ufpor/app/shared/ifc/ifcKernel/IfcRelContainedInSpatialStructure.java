package com.ufpor.app.shared.ifc.ifcKernel;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcRelContainedInSpatialStructure extends IfcRelConnects {
    public final static String TAG = IfcRelContainedInSpatialStructure.class.getSimpleName();
    protected final static String ERROR1 = "The relationship object shall not be used to include other spatial structure elements into a spatial structure element. The hierarchy of the spatial structure is defined using IfcRelAggregates";
    private static Logger logger = Logger.getLogger(TAG);
    private IfcSpatialElement relatingStructure;
    private Set<IfcProduct> relatedElements;

    public IfcRelContainedInSpatialStructure() {
       relatedElements = new HashSet<IfcProduct>();
    }

    public IfcRelContainedInSpatialStructure(IfcSpatialElement relatingObject) {
        this.relatingStructure = relatingObject;
        relatedElements = new HashSet<IfcProduct>();
    }

    public IfcRelContainedInSpatialStructure(IfcSpatialElement relatingObject, Set<IfcProduct> relatedObjects) {
        this.relatingStructure = relatingObject;
        for (IfcProduct product : relatedObjects) {
            if(!(product instanceof IfcSpatialElement)) {
                relatedElements.add(product);
            } else {
                logger.log(Level.SEVERE, ERROR1 + ((IfcSpatialElement) product).longName);
            }
        }

    }

    public boolean addRelatedObject(IfcProduct relatedObject) {
        if (!(relatedObject instanceof IfcSpatialElement)) {
            relatedElements.add(relatedObject);
            return true;
        } else {
            logger.log(Level.SEVERE, ERROR1);
            return false;
        }

    }

}
