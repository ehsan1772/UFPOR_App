package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.relationship.IfcDecRelAggregates;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecObjectDefinition extends IfcDecRoot implements IfcDecDefinitionSelect {
    //this property represents the inverse: HasContext	:	SET [0:1] OF IfcRelDeclares FOR RelatedDefinitions; relationship
    @Persistent
    protected ArrayList<Key> hasContext;

    @Persistent(defaultFetchGroup = "true")
    protected IfcDecRelAggregates<IfcDecRoot, IfcDecRoot> childSpaces;


    protected IfcDecObjectDefinition(String GUID, User user) {
        super(GUID, user);
    }

    public IfcDecRelAggregates<IfcDecRoot, IfcDecRoot> getChildSpaces() {
        return childSpaces;
    }

    public void setChildSpaces(IfcDecRelAggregates<IfcDecRoot, IfcDecRoot>  childSpaces) {
        this.childSpaces = childSpaces;
    }


    public void addChildSpace(IfcDecSpace child) {
        if (childSpaces == null) {
            //Getting GUID
            String guid = GuidCompressor.getNewIfcGloballyUniqueId();
            childSpaces = new IfcDecRelAggregates(guid, user, this);
        }
        childSpaces.add(child);
        prepareDataForStoreIfcDecContext(null);
    }

    protected IfcDecObjectDefinition() {
        //creating the space hierarchy

    }

    @Override
    public void prepareDataForClient(PostLoadContext context) {
        super.prepareDataForClient(context);

        if (childSpaces != null) {
            childSpaces.prepareDataForClient(context);
        }

    }

    @Override
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);

        if (childSpaces != null) {
            childSpaces.prepareDataForStoreIfcDecContext(context);
        }

    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        ArrayList<IfcFileObject> list = super.getRelatedObjects();
        if (childSpaces != null) {
            list.add(childSpaces);
        }
        return list;
    }
}
