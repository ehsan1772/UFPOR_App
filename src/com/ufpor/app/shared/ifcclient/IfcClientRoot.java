package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.client.LoginInfo;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */


public abstract class IfcClientRoot extends GAEObject implements Serializable {

    private IfcClientGloballyUniqueId globalId;

    private IfcClientOwnerHistory ownerHistory;

    private String name;

    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
