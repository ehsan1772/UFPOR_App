package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PutContext;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecProduct;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpatialElement;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpatialStructureElement;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecRelContainedInSpatialStructure<T extends IfcDecProduct, E extends IfcDecSpatialStructureElement> extends IfcDecRelConnects<T, E> {
    public final static String TAG = IfcDecRelContainedInSpatialStructure.class.getSimpleName();
    protected final static String ERROR1 = "The relationship object shall not be used to include other spatial structure elements into a spatial structure element. The hierarchy of the spatial structure is defined using IfcRelAggregates";
    private static transient Logger logger = Logger.getLogger(TAG);
    private IfcDecSpatialStructureElement relatingStructure;
    private List<IfcDecProduct> relatedElements;

    @NotPersistent
    private List<T> list;

    @Persistent(defaultFetchGroup = "true")
    protected ArrayList<IfcDecSpace> children;


    public IfcDecRelContainedInSpatialStructure() {
        relatedElements = new ArrayList<>();
    }

    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public void addAll(List<T> item) {
        list.addAll(item);
    }

    @Override
    protected void initialize(IfcDecSpatialStructureElement owner) {

    }


    public IfcDecRelContainedInSpatialStructure(IfcDecSpatialStructureElement relatingObject) {
        this.relatingStructure = relatingObject;
        relatedElements = new ArrayList<>();
    }

    public IfcDecRelContainedInSpatialStructure(IfcDecSpatialStructureElement relatingObject, List<IfcDecProduct> relatedObjects) {
        this.relatingStructure = relatingObject;
        for (IfcDecProduct product : relatedObjects) {
            if (!(product instanceof IfcDecSpatialElement)) {
                relatedElements.add(product);
            } else {
                logger.log(Level.SEVERE, ERROR1 + ((IfcDecSpatialElement) product).getLongName());
            }
        }

    }


    @Override
    public E getOwner() {
        return (E) relatingStructure;
    }

    @Override
    public List getList() {
        return children;
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

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return null;
    }

    @Override
    public void prepareDataForClient(PostLoadContext context) {
        super.prepareDataForClient(context);
        for (IfcDecSpace space : children) {
            if (space instanceof IfcDecSpace) {
                relatedElements.add(space);
            }
        }
    }

    @Override
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);
        children = new ArrayList<>();
        for (IfcDecProduct product : relatedElements) {
            if (product instanceof IfcDecSpace) {
                children.add((IfcDecSpace) product);
            }
        }
    }

}
