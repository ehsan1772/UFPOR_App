package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifckernel.IfcLabel;
import com.ufpor.app.shared.ifckernel.IfcText;

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
    @Persistent
    private IfcLabel name;
    @Persistent
    private IfcText description;

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

    public IfcLabel getName() {
        return name;
    }

    public void setName(IfcLabel name) {
        this.name = name;
    }

    public IfcText getDescription() {
        return description;
    }

    public void setDescription(IfcText description) {
        this.description = description;
    }
}
