package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObjectDefinition;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpatialElement;

import javax.jdo.annotations.Inheritance;
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
public class IfcDecRelAggregates<T extends IfcDecRoot, E extends IfcDecRoot> extends IfcDecRelDecomposes<T, E> {
    public final static String TAG = IfcDecRelAggregates.class.getSimpleName();
    private static transient Logger logger = Logger.getLogger(TAG);
    private E relatingObject;
    private List<T> relatedObjects;

    @Persistent(defaultFetchGroup = "true")
    private List<IfcDecSpace> childSpaces;

    @Persistent(defaultFetchGroup = "true")
    private IfcDecSpace parent;

    public IfcDecRelAggregates() {
        super();
        // relatedObjects = new ArrayList<IfcDecObjectDefinition>();
    }

    public IfcDecRelAggregates(String GUID, User user, E owner) {
        super(GUID, user, owner);
        this.relatingObject = relatingObject;
        relatedObjects = new ArrayList<>();
    }

    @Override
    protected void initialize(E owner) {
        relatedObjects = new ArrayList<>();
        relatingObject = owner;
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
        relatedObjects = new ArrayList<>();
        relatingObject = (E) parent;

        for (IfcDecSpace child : childSpaces) {
            relatedObjects.add((T) child);
        }
    }

    @Override
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);
        childSpaces = new ArrayList<>();

        if (relatingObject instanceof IfcDecSpace) {
            parent = (IfcDecSpace) relatingObject;
        }

        for (T child : relatedObjects) {
            if (child instanceof IfcDecSpace) {
                childSpaces.add((IfcDecSpace) child);
            }
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

        IfcDecRoot rr = getList().get(0);

        String relatedObjects = fileManager.getNumberString((List<IfcFileObject>) (List<?>) getList());

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
