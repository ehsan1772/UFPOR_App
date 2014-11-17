package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.client.LoginInfo;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */


public abstract class IfcClientRoot extends GAEObject implements Serializable {

    private IfcClientGloballyUniqueId globalId;

    private IfcClientOwnerHistory ownerHistory;

    private IfcClientLabel name;

    private IfcClientText description;

    public IfcClientRoot(String GUID, LoginInfo user) {
        super(user);
        globalId = new IfcClientGloballyUniqueId(GUID);
    }

    public IfcClientRoot() {
    }

    public IfcClientGloballyUniqueId getGlobalId() {
        return globalId;
    }

    public IfcClientOwnerHistory getOwnerHistory() {
        return ownerHistory;
    }

    public void setOwnerHistory(IfcClientOwnerHistory ownerHistory) {

        this.ownerHistory = ownerHistory;
    }

    public IfcClientLabel getName() {
        return name;
    }

    public void setName(IfcClientLabel name) {
        this.name = name;
    }

    public IfcClientText getDescription() {
        return description;
    }

    public void setDescription(IfcClientText description) {
        this.description = description;
    }
}
