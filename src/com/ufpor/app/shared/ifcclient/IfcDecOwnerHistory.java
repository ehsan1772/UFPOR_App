package com.ufpor.app.shared.ifcclient;


import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public class IfcDecOwnerHistory implements Serializable {
    private IfcDecPersonAndOrganization owningUser;
    private IfcDecApplication owningApplication;
    private IfcDecStateEnum state;
    private IfcDecChangeActionEnum changeAction;
    private IfcDecTimeStamp lastModifiedDate;
    private IfcDecPersonAndOrganization lastModifyingUser;
    private IfcDecApplication lastModifyingApplicaiton;
    private IfcDecTimeStamp creationDate;

    public IfcDecOwnerHistory(IfcDecPersonAndOrganization owningUser, IfcDecApplication owningApplication) {
        this.owningUser = owningUser;
        this.owningApplication = owningApplication;
        this.creationDate = new IfcDecTimeStamp(System.currentTimeMillis());
    }

    public IfcDecOwnerHistory() {
    }

    public IfcDecPersonAndOrganization getOwningUser() {

        return owningUser;
    }

    public IfcDecApplication getOwningApplication() {
        return owningApplication;
    }

    public IfcDecStateEnum getState() {
        return state;
    }

    public void setState(IfcDecStateEnum state) {
        this.state = state;
    }

    public IfcDecChangeActionEnum getChangeAction() {
        return changeAction;
    }

    public void setChangeAction(IfcDecChangeActionEnum changeAction) {
        this.changeAction = changeAction;
    }

    public IfcDecTimeStamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(IfcDecTimeStamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public IfcDecPersonAndOrganization getLastModifyingUser() {
        return lastModifyingUser;
    }

    public void setLastModifyingUser(IfcDecPersonAndOrganization lastModifyingUser) {
        this.lastModifyingUser = lastModifyingUser;
    }

    public IfcDecApplication getLastModifyingApplicaiton() {
        return lastModifyingApplicaiton;
    }

    public void setLastModifyingApplicaiton(IfcDecApplication lastModifyingApplicaiton) {
        this.lastModifyingApplicaiton = lastModifyingApplicaiton;
    }

    public IfcDecTimeStamp getCreationDate() {
        return creationDate;
    }
}
