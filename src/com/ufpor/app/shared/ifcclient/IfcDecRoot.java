package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifckernel.IfcText;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */


public abstract class IfcDecRoot extends GAEObject implements Serializable {

    private IfcDecGloballyUniqueId globalId;

    private IfcDecOwnerHistory ownerHistory;

    private IfcDecLabel name;

    private IfcText description;

    public IfcDecRoot(String GUID, LoginInfo user) {
        super(user);
        globalId = new IfcDecGloballyUniqueId(GUID);
    }

    public IfcDecRoot() {
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

    public IfcText getDescription() {
        return description;
    }

    public void setDescription(IfcText description) {
        this.description = description;
    }
}
