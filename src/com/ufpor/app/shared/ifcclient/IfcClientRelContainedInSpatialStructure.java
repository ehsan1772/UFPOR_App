package com.ufpor.app.shared.ifcclient;


import com.ufpor.app.shared.ifcclient.product.IfcClientProduct;
import com.ufpor.app.shared.ifcclient.product.IfcClientSpatialElement;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientRelContainedInSpatialStructure extends IfcClientRelConnects {
    public final static String TAG = IfcClientRelContainedInSpatialStructure.class.getSimpleName();
    protected final static String ERROR1 = "The relationship object shall not be used to include other spatial structure elements into a spatial structure element. The hierarchy of the spatial structure is defined using IfcRelAggregates";
    private transient static Logger logger = Logger.getLogger(TAG);
    private IfcClientSpatialElement relatingStructure;
    private Set<IfcClientProduct> relatedElements;

    public IfcClientRelContainedInSpatialStructure() {
       relatedElements = new HashSet<IfcClientProduct>();
    }

    public IfcClientRelContainedInSpatialStructure(IfcClientSpatialElement relatingObject) {
        this.relatingStructure = relatingObject;
        relatedElements = new HashSet<IfcClientProduct>();
    }

    public IfcClientRelContainedInSpatialStructure(IfcClientSpatialElement relatingObject, Set<IfcClientProduct> relatedObjects) {
        this.relatingStructure = relatingObject;
        for (IfcClientProduct product : relatedObjects) {
            if(!(product instanceof IfcClientSpatialElement)) {
                relatedElements.add(product);
            } else {
                logger.log(Level.SEVERE, ERROR1 + ((IfcClientSpatialElement) product).getLongName());
            }
        }

    }

    public boolean addRelatedObject(IfcClientProduct relatedObject) {
        if (!(relatedObject instanceof IfcClientSpatialElement)) {
            relatedElements.add(relatedObject);
            return true;
        } else {
            logger.log(Level.SEVERE, ERROR1);
            return false;
        }

    }

}
