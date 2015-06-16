package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.users.User;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import java.util.*;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecRelationship<T extends IfcDecRoot, E extends IfcDecRoot> extends IfcDecRoot {
    public abstract E getOwner();
    public abstract List<T> getList();

    public IfcDecRelationship(String GUID, User user, E owner) {
        super(GUID, user);
        initialize(owner);
    }

    public IfcDecRelationship(String GUID, User user) {
        super(GUID, user);
        initialize(null);
    }

    public IfcDecRelationship() {
        initialize(null);
    }

    protected abstract void initialize(E owner);

    public abstract void add(T item);
    public abstract void addAll(List<T> item);

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        ArrayList<IfcFileObject> results = new ArrayList<>();
        results.addAll(getList());
        return  results;
    }

}
