package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.users.User;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecRoot extends GAEObject implements Serializable {
    @Persistent
    private IfcDecGloballyUniqueId globalId;
    @Persistent
    private IfcDecOwnerHistory ownerHistory;
    //TODO Blob values are not indexed and cannot be used in query filters or sort orders. It's not good! I may want to use it in a query
    @Persistent(serialized="true")
    private IfcDecLabel name;
    //TODO Blob values are not indexed and cannot be used in query filters or sort orders. It's not good! I may want to use it in a query
    @Persistent(serialized="true")
    private IfcDecText description;

    protected IfcDecRoot(String GUID, User user) {
        super(user);
        globalId = new IfcDecGloballyUniqueId(GUID);
    }

    protected IfcDecRoot() {
    }

    public IfcDecGloballyUniqueId getGlobalId() {
        return globalId;
    }

    public IfcDecOwnerHistory getOwnerHistory() {
        return ownerHistory;
    }

    public void setOwnerHistory(IfcDecOwnerHistory ownerHistory) {

        this.ownerHistory = ownerHistory;
    }

    public IfcDecLabel getName() {
        return name;
    }

    public void setName(IfcDecLabel name) {
        this.name = name;
    }

    public IfcDecText getDescription() {
        return description;
    }

    public void setDescription(IfcDecText description) {
        this.description = description;
    }
}
