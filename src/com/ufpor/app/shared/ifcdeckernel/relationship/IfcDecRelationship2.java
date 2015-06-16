package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecRelationship2<T extends IfcDecRoot, E extends IfcDecRoot> extends IfcDecRoot {
    public abstract E getOwner();
    public abstract List<T> getList();

    public IfcDecRelationship2(String GUID, User user, E owner) {
        super(GUID, user);
        initialize(owner);
    }

    public IfcDecRelationship2(String GUID, User user) {
        super(GUID, user);
        initialize(null);
    }

    public IfcDecRelationship2() {
        initialize(null);
    }

    protected abstract void initialize(E owner);

    public abstract void add(T item);

}
