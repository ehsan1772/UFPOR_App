package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.datastore.PostLoad;
import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PrePut;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecText;

import javax.jdo.annotations.*;
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
    @NotPersistent
    private IfcDecLabel name;
    @NotPersistent
    private IfcDecText description;
    @Persistent
    private String descriptionText;
    @Persistent
    private String nameText;

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

    public void setName(String name) {
        this.nameText = name;
        this.name = new IfcDecLabel(name);
    }

    public void setName(IfcDecLabel name) {
        this.name = name;
        this.nameText = name.getValue();
    }

    public IfcDecText getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = new IfcDecText(description);
        this.descriptionText = description;
    }

    public void setDescription(IfcDecText description) {
        this.description = description;
        this.descriptionText = description.getValue();
    }

    @PostLoad(kinds = {"IfcDecProject"})
    public void prepareDataForClient(PostLoadContext context) {
        description = new IfcDecText(descriptionText);
        name = new IfcDecLabel(nameText);
    }

    @PrePut(kinds = {"IfcDecProject"})
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        if (description != null) {
            descriptionText = description.getValue();
        }

        if (name != null) {
            nameText = name.getValue();
        }
    }

}
