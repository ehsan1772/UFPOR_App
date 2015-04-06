package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.IfcFileObject;

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

//    @Persistent
//    protected Set<String> childSpacesKeys;

    @Persistent(defaultFetchGroup = "true")
    protected IfcDecRelAggregates<IfcDecObjectDefinition, IfcDecObjectDefinition> childSpaces;

    protected IfcDecObjectDefinition(String GUID, User user) {
        super(GUID, user);
        //Getting GUID
        String guid = GuidCompressor.getNewIfcGloballyUniqueId();
        childSpaces = new IfcDecRelAggregates<IfcDecObjectDefinition, IfcDecObjectDefinition>(guid, user, this);
    }

    protected IfcDecObjectDefinition() {
        //creating the space hierarchy

    }

    public void addChildSpace(IfcDecObjectDefinition child) {
        childSpaces.add(child);
    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        ArrayList<IfcFileObject> objects = new ArrayList<>();
        objects.add(childSpaces);
        return objects;
    }

    @Override
    public void prepareDataForClient(PostLoadContext context) {
        super.prepareDataForClient(context);

    }

    @Override
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);

    }
}
