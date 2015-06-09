package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.shared.ifcdeckernel.IfcDecContext;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObjectDefinition;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;

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
    private E owner;
    private ArrayList<T> list;

     public IfcDecRelDeclares() {

    }

    @Override
    protected void initialize(E owner) {

    }

    public IfcDecRelDeclares(E relatingObject) {
        owner = relatingObject;
    }

    public IfcDecRelDeclares(E relatingObject, ArrayList<T> relatedObjects) {
        owner = relatingObject;
        list = relatedObjects;

    }

    @Override
    public E getOwner() {
        return owner;
    }

    @Override
    public List<T> getList() {
        return list;
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
