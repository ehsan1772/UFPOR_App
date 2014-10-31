package com.ufpor.app.shared.ifc.ifcKernel;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcOwnerHistory {
    private IfcPersonAndOrganization owningUser;
    private IfcApplication owningApplication;
    private IfcStateEnum state;
    private IfcChangeActionEnum changeAction;
    private IfcTimeStamp lastModifiedDate;
    private IfcPersonAndOrganization lastModifyingUser;
    private IfcApplication lastModifyingApplicaiton;
    private IfcTimeStamp creationDate;

    public IfcOwnerHistory(IfcPersonAndOrganization owningUser, IfcApplication owningApplication) {
        this.owningUser = owningUser;
        this.owningApplication = owningApplication;
        this.creationDate = new IfcTimeStamp(System.currentTimeMillis());
    }

    public IfcPersonAndOrganization getOwningUser() {

        return owningUser;
    }

    public IfcApplication getOwningApplication() {
        return owningApplication;
    }

    public IfcStateEnum getState() {
        return state;
    }

    public void setState(IfcStateEnum state) {
        this.state = state;
    }

    public IfcChangeActionEnum getChangeAction() {
        return changeAction;
    }

    public void setChangeAction(IfcChangeActionEnum changeAction) {
        this.changeAction = changeAction;
    }

    public IfcTimeStamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(IfcTimeStamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public IfcPersonAndOrganization getLastModifyingUser() {
        return lastModifyingUser;
    }

    public void setLastModifyingUser(IfcPersonAndOrganization lastModifyingUser) {
        this.lastModifyingUser = lastModifyingUser;
    }

    public IfcApplication getLastModifyingApplicaiton() {
        return lastModifyingApplicaiton;
    }

    public void setLastModifyingApplicaiton(IfcApplication lastModifyingApplicaiton) {
        this.lastModifyingApplicaiton = lastModifyingApplicaiton;
    }

    public IfcTimeStamp getCreationDate() {
        return creationDate;
    }
}
