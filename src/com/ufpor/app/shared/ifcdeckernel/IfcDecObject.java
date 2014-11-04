package com.ufpor.app.shared.ifcdeckernel;


import com.google.appengine.api.users.User;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecObject extends IfcDecObjectDefinition {
    //TODO Blob values are not indexed and cannot be used in query filters or sort orders. It's not good! I may want to use it in a query
    @Persistent(serialized="true")
    protected IfcDecLabel objectType;

    public IfcDecLabel getObjectType() {
        return objectType;
    }

    public void setDecObjectType(IfcDecLabel objectType) {
        this.objectType = objectType;
    }

    protected IfcDecObject(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecObject() {
    }
}
