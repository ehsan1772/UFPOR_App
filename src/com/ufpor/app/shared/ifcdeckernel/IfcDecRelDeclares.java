package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertyDefinition;
import com.ufpor.app.shared.ifckernel.IfcObjectDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecRelDeclares<T extends IfcDecRoot, E extends IfcDecRoot> extends IfcDecRelationship<T, E> {
    public final static String TAG = IfcDecRelDeclares.class.getSimpleName();
    private static transient Logger logger = Logger.getLogger(TAG);
    private static IfcDecRelDeclares<IfcDecObjectDefinition, IfcDecContext> instance;
//    private com.ufpor.app.shared.ifcdeckernel.IfcDecObjectDefinition relatingObject;
//    private List<T> relatedObjects;


//    public IfcDecRelDeclares(Class clazz) {
//        if (isTypeSupported(clazz)) {
//            relatedObjects = new ArrayList<T>();
//        }
//    }

     public IfcDecRelDeclares() {

    }

    public IfcDecRelDeclares(E relatingObject) {
        owner = relatingObject;
    }

    public IfcDecRelDeclares(E relatingObject, ArrayList<T> relatedObjects) {
        owner = relatingObject;
        list = relatedObjects;

    }

    private IfcDecRelDeclares(String guid, User user) {
        super(guid, user);

    }

    public static IfcDecRelDeclares<IfcDecObjectDefinition, IfcDecContext> getInstance(IfcDecContext owner) {
        IfcDecRelDeclares<IfcDecObjectDefinition,IfcDecContext> instance = new IfcDecRelDeclares<>(GuidCompressor.getNewIfcGloballyUniqueId(), UserServiceFactory.getUserService().getCurrentUser());
        instance.owner = owner;
        return instance;
    }

    public boolean addRelatedObject(T relatedObject) {
        if (relatedObject != owner) {
            list.add(relatedObject);
            return true;
        } else {
            logger.log(Level.SEVERE, "The instance to with the relation points as provided by RelatingObject shall not be contained in the list of RelatedObjects.");
            return false;
        }

    }

//    private boolean isTypeSupported(Class clazz) {
//        if (clazz == IfcObjectDefinition.class || clazz == IfcDecPropertyDefinition.class) {
//            return true;
//        }
//        logger.log(Level.SEVERE, "IfcDefinitionSelect provides the option to either select an object or type object IfcObjectDefinition, or a property set template or property set, IfcPropertyDefinition.");
//        return false;
//    }


    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        //IfcRoot
        String globalId = getGlobalId().getValue();
        String ownerHistory = "*";
        String name = getName();
        String description = getDescription();

        //IfcRelDeclares
        String relatingObject = String.valueOf(fileManager.getNumber(getOwner()));
        String relatedObjects = fileManager.getNumberString(getRelatedObjects());

        String result = String.format(Constants.IFCRELDECLARES,
                globalId,
                ownerHistory,
                name,
                description,
                relatingObject,
                relatedObjects);

        return result;
    }

}
