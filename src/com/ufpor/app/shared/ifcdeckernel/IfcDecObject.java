package com.ufpor.app.shared.ifcdeckernel;


import com.google.appengine.api.users.User;

import javax.jdo.annotations.*;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecObject extends IfcDecObjectDefinition {
    @NotPersistent
    protected IfcDecLabel objectType;
    @Persistent
    private String objectTypeString;

    protected IfcDecObject(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecObject() {
    }


    public IfcDecLabel getObjectType() {
        return objectType;
    }

    public void setDecObjectType(IfcDecLabel objectType) {
        this.objectType = objectType;
        this.objectTypeString = objectType.getValue();
    }

    public void setDecObjectType(String objectType) {
        this.objectTypeString = objectType;
        this.objectType = new IfcDecLabel(objectType);
    }
}
