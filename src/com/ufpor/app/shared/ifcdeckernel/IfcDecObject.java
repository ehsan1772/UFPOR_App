package com.ufpor.app.shared.ifcdeckernel;


import com.google.appengine.api.users.User;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecObject extends IfcDecObjectDefinition {
    protected IfcDecLabel objectType;

    public IfcDecLabel getObjectType() {
        return objectType;
    }

    public void setObjectType(IfcDecLabel objectType) {
        this.objectType = objectType;
    }

    protected IfcDecObject(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecObject() {
    }
}
