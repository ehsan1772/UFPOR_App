package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.users.User;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifckernel.IfcObjectDefinition;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecRelAggregates<T extends IfcDecRoot, E extends IfcDecRoot> extends IfcDecRelDecomposes<T,E> {
    public final static String TAG = IfcDecRelAggregates.class.getSimpleName();
    private static transient Logger logger = Logger.getLogger(TAG);
//    private IfcDecObjectDefinition relatingObject;
//    private List<IfcDecObjectDefinition> relatedObjects;

    public IfcDecRelAggregates() {
        super();
       // relatedObjects = new ArrayList<IfcDecObjectDefinition>();
    }

    public IfcDecRelAggregates(String GUID, User user, E owner) {
        super(GUID, user, owner);
//        this.relatingObject = relatingObject;
//        relatedObjects = new ArrayList<IfcDecObjectDefinition>();
    }

//    public IfcDecRelAggregates(IfcDecObjectDefinition relatingObject, List<IfcDecObjectDefinition> relatedObjects) {
//        this.relatingObject = relatingObject;
//        if (!relatedObjects.contains(relatingObject)) {
//            this.relatedObjects = relatedObjects;
//        } else {
//            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
//        }
//
//    }
//
//    public boolean addRelatedObject(IfcDecObjectDefinition relatedObject) {
//        if (!relatedObjects.contains(relatingObject)) {
//            relatedObjects.add(relatedObject);
//            return true;
//        } else {
//            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
//            return false;
//        }
//
//    }

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

        String relatedObjects = fileManager.getNumberString((List<IfcFileObject>)(List<?>) getList());

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
