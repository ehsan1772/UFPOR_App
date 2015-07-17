package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecGloballyUniqueId;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.type.IfcDecTypeObject;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 7/13/15.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecRelDefinesByType extends IfcDecRelDefines<IfcDecObject> {
    public final static String TAG = IfcDecRelDefinesByType.class.getSimpleName();
    private static transient Logger logger = Logger.getLogger(TAG);

    @NotPersistent
    private IfcDecTypeObject relatingObject;

    @NotPersistent
    private Set<IfcDecRoot> relatedObjects;

    @Persistent(defaultFetchGroup = "true")
    private Set<String> definedSpaces;

    @Persistent(serialized = "true")
    private Class parentClass;

    @Persistent
    private String parentKey;

    public IfcDecRelDefinesByType() {
        super();
        relatedObjects = new HashSet<>();
    }

    public IfcDecRelDefinesByType(String GUID, User user, IfcDecTypeObject owner) {
        super(GUID, user, owner);
        relatedObjects = new HashSet<>();
        relatingObject = owner;
        definedSpaces = new HashSet<>();
        parentKey = owner.getKey();
        parentClass = owner.getClass();
    }


    @Override
    public IfcDecRoot getOwner() {
        return relatingObject;
    }

    @Override
    public List getList() {
        return new ArrayList(relatedObjects);
    }

    @Override
    protected void initialize(IfcDecRoot owner) {

    }

    @Override
    public void add(IfcDecRoot item) {
        relatedObjects.add(item);
    }

    @Override
    public void addAll(List item) {
        relatedObjects.addAll(item);
    }


    @Override
    public void prepareDataForClient(PostLoadContext context) {
        super.prepareDataForClient(context);
        PersistenceManager pm = EnvironmentServiceImpl.PMF.getPersistenceManager();

        if (definedSpaces != null) {
            for (String definedObject : definedSpaces) {
                relatedObjects.add(pm.getObjectById(IfcDecSpace.class, definedObject));
            }
        }

        relatingObject = (IfcDecTypeObject) pm.getObjectById(parentClass, parentKey);
    }

    @Override
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);

        if (definedSpaces == null) {
            definedSpaces = new HashSet<>();
        }

        for (IfcDecRoot defineObject : relatedObjects) {
            if (defineObject instanceof IfcDecSpace && !definedSpaces.contains(defineObject)) {
                definedSpaces.add(defineObject.getKey());
            }
        }
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        IfcDecGloballyUniqueId id = getGlobalId();
        //IfcRoot
        String globalId = getGlobalId().getValue();
        String ownerHistory = "*";
        String name = getName();
        String description = getDescription();

        //IfcRelDefinesByType
        String relatingObjectString = String.valueOf(fileManager.getNumber(relatingObject));

        String relatedObjects = fileManager.getNumberString((List<IfcFileObject>) (List<?>) getList());

        String result = String.format(Constants.IFCRELDEFINESBYTYPE,
                globalId,
                ownerHistory,
                name,
                description,
                relatingObjectString,
                relatedObjects);

        return result;
    }
}
