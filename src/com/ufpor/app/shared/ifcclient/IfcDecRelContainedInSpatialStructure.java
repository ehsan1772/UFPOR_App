package com.ufpor.app.shared.ifcclient;


import com.ufpor.app.shared.ifcclient.decproduct.IfcDecProduct;
import com.ufpor.app.shared.ifcclient.decproduct.IfcDecSpatialElement;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecRelContainedInSpatialStructure extends IfcDecRelConnects {
    public final static String TAG = IfcDecRelContainedInSpatialStructure.class.getSimpleName();
    protected final static String ERROR1 = "The relationship object shall not be used to include other spatial structure elements into a spatial structure element. The hierarchy of the spatial structure is defined using IfcRelAggregates";
    private transient static Logger logger = Logger.getLogger(TAG);
    private IfcDecSpatialElement relatingStructure;
    private Set<IfcDecProduct> relatedElements;

    public IfcDecRelContainedInSpatialStructure() {
       relatedElements = new HashSet<IfcDecProduct>();
    }

    public IfcDecRelContainedInSpatialStructure(IfcDecSpatialElement relatingObject) {
        this.relatingStructure = relatingObject;
        relatedElements = new HashSet<IfcDecProduct>();
    }

    public IfcDecRelContainedInSpatialStructure(IfcDecSpatialElement relatingObject, Set<IfcDecProduct> relatedObjects) {
        this.relatingStructure = relatingObject;
        for (IfcDecProduct product : relatedObjects) {
            if(!(product instanceof IfcDecSpatialElement)) {
                relatedElements.add(product);
            } else {
                logger.log(Level.SEVERE, ERROR1 + ((IfcDecSpatialElement) product).getLongName());
            }
        }

    }

    public boolean addRelatedObject(IfcDecProduct relatedObject) {
        if (!(relatedObject instanceof IfcDecSpatialElement)) {
            relatedElements.add(relatedObject);
            return true;
        } else {
            logger.log(Level.SEVERE, ERROR1);
            return false;
        }

    }

}
