package com.ufpor.app.shared.ifcclient;


import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public class IfcClientOwnerHistory implements Serializable {
    private IfcClientPersonAndOrganization owningUser;
    private IfcClientApplication owningApplication;
    private IfcClientStateEnum state;
    private IfcClientChangeActionEnum changeAction;
    private IfcClientTimeStamp lastModifiedDate;
    private IfcClientPersonAndOrganization lastModifyingUser;
    private IfcClientApplication lastModifyingApplicaiton;
    private IfcClientTimeStamp creationDate;

    public IfcClientOwnerHistory(IfcClientPersonAndOrganization owningUser, IfcClientApplication owningApplication) {
        this.owningUser = owningUser;
        this.owningApplication = owningApplication;
        this.creationDate = new IfcClientTimeStamp(System.currentTimeMillis());
    }

    public IfcClientOwnerHistory() {
    }

    public IfcClientPersonAndOrganization getOwningUser() {

        return owningUser;
    }

    public IfcClientApplication getOwningApplication() {
        return owningApplication;
    }

    public IfcClientStateEnum getState() {
        return state;
    }

    public void setState(IfcClientStateEnum state) {
        this.state = state;
    }

    public IfcClientChangeActionEnum getChangeAction() {
        return changeAction;
    }

    public void setChangeAction(IfcClientChangeActionEnum changeAction) {
        this.changeAction = changeAction;
    }

    public IfcClientTimeStamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(IfcClientTimeStamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public IfcClientPersonAndOrganization getLastModifyingUser() {
        return lastModifyingUser;
    }

    public void setLastModifyingUser(IfcClientPersonAndOrganization lastModifyingUser) {
        this.lastModifyingUser = lastModifyingUser;
    }

    public IfcClientApplication getLastModifyingApplicaiton() {
        return lastModifyingApplicaiton;
    }

    public void setLastModifyingApplicaiton(IfcClientApplication lastModifyingApplicaiton) {
        this.lastModifyingApplicaiton = lastModifyingApplicaiton;
    }

    public IfcClientTimeStamp getCreationDate() {
        return creationDate;
    }
}
