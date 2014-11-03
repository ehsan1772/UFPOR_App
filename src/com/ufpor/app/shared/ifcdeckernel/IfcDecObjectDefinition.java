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
public abstract class IfcDecObjectDefinition extends IfcDecRoot {
    protected IfcDecObjectDefinition(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecObjectDefinition() {
    }
}
