package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecRelDecomposes2<T extends IfcDecRoot, E extends IfcDecRoot> extends IfcDecRelationship2<T,E> {
    public IfcDecRelDecomposes2(String GUID, User user, E owner) {
        super(GUID, user, owner);
    }

    public IfcDecRelDecomposes2(String GUID, User user) {
        super(GUID, user);
    }

    public IfcDecRelDecomposes2() {
        super();
    }
}
