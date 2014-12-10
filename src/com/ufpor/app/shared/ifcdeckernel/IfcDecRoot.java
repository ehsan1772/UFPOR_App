package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.datastore.PostLoad;
import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PrePut;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.ufpor.app.server.ifcphysical.IfcFileObject;

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
public abstract class IfcDecRoot extends GAEObject implements Serializable, IfcFileObject {
    @Persistent
    private IfcDecGloballyUniqueId globalId;
    @Persistent
    private IfcDecOwnerHistory ownerHistory;

    @Persistent
    private String descriptionText;
    @Persistent
    private String nameText;
    private int ifcPhysicalNumber;

    protected IfcDecRoot(String GUID, User user) {
        super(user);
        globalId = new IfcDecGloballyUniqueId(GUID);
    }

    protected IfcDecRoot() {
    }

    public String getName() {
        return nameText;
    }

    public void setName(String name) {
        this.nameText = name;
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

    public String getDescription() {
        return descriptionText;
    }

    public void setDescription(String description) {
        this.descriptionText = description;
    }

    @PostLoad(kinds = {"IfcDecProject"})
    public void prepareDataForClient(PostLoadContext context) {

    }

    @PrePut(kinds = {"IfcDecProject"})
    public void prepareDataForStoreIfcDecContext(PutContext context) {

    }

    @Override
    public int getNumber() {
        return ifcPhysicalNumber;
    }

    @Override
    public void setNumber(int number) {
        ifcPhysicalNumber = number;
    }

}
