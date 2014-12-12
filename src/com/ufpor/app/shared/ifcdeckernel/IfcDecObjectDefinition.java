package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

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
    protected IfcDecObjectDefinition(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecObjectDefinition() {
    }

}
