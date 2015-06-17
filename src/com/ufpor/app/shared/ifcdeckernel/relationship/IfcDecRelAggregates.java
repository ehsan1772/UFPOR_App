package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObjectDefinition;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecRelAggregates<T extends IfcDecRoot, E extends IfcDecRoot> extends IfcDecRelDecomposes<T, E> {
    public final static String TAG = IfcDecRelAggregates.class.getSimpleName();
    private static transient Logger logger = Logger.getLogger(TAG);

    @NotPersistent
    private E relatingObject;
    @NotPersistent
    private List<T> relatedObjects;

    @Persistent(defaultFetchGroup = "true")
    private ArrayList<IfcDecSpace> childSpaces;

    @Persistent(serialized = "true")
    private Class parentClass;

    @Persistent
    private String parentKey;

    @Persistent(defaultFetchGroup = "true", mappedBy = "childSpaces")
    private IfcDecObjectDefinition parent;

    public IfcDecRelAggregates() {
        super();
        relatedObjects = new ArrayList<>();
        childSpaces = new ArrayList<>();
    }

    public IfcDecRelAggregates(String GUID, User user, E owner) {
        super(GUID, user, owner);
        relatedObjects = new ArrayList<>();
        relatingObject = owner;
        childSpaces = new ArrayList<>();
        parentKey = owner.getKey();
        parentClass = owner.getClass();
    }

    @Override
    protected void initialize(E owner) {
//        relatedObjects = new ArrayList<>();
//        relatingObject = owner;
    }

    @Override
    public void add(T item) {
        relatedObjects.add(item);
    }

    @Override
    public void addAll(List<T> item) {
        getList().addAll(item);
    }

    @Override
    public E getOwner() {
        return relatingObject;
    }

    @Override
    public List<T> getList() {

        return relatedObjects;
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


    @Override
    public void prepareDataForClient(PostLoadContext context) {
        super.prepareDataForClient(context);

        if (childSpaces != null) {
            for (IfcDecSpace child : childSpaces) {
                relatedObjects.add((T) child);
            }
        }

        PersistenceManager pm = EnvironmentServiceImpl.PMF.getPersistenceManager();

        relatingObject = (E) pm.getObjectById(parentClass, parentKey);
    }

    @Override
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);

        if (childSpaces == null) {
            childSpaces = new ArrayList<>();
        }

        for (T child : relatedObjects) {
            if (child instanceof IfcDecSpace && !childSpaces.contains(child)) {
                childSpaces.add((IfcDecSpace) child);
            }
        }
    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        if (getList().size() != 0) {
            return new ArrayList<IfcFileObject>(getList());
        } else {
            return null;
        }
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        //IfcRoot
        String globalId = getGlobalId().getValue();
        String ownerHistory = "*";
        String name = getName();
        String description = getDescription();

        //IfcRelAggregates
        String relatingObjectString = String.valueOf(fileManager.getNumber(relatingObject));

        IfcDecRoot rr = getList().get(0);

        String relatedObjects = fileManager.getNumberString((List<IfcFileObject>) (List<?>) getList());

        String result = String.format(Constants.IFCRELAGGREGATES,
                globalId,
                ownerHistory,
                name,
                description,
                relatingObjectString,
                relatedObjects);

        return result;
    }
}
