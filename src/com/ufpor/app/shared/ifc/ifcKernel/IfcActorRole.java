package com.ufpor.app.shared.ifc.ifcKernel;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcActorRole {
    public IfcActorRole(IfcRoleEnum role) {
        this.role = role;
    }

    public IfcRoleEnum getRole() {

        return role;
    }

    public IfcLabel getUserDefinedRole() {

        return userDefinedRole;
    }

    public void setUserDefinedRole(IfcLabel userDefinedRole) {
        this.userDefinedRole = userDefinedRole;
    }

    public IfcText getDescription() {
        return description;
    }

    public void setDescription(IfcText description) {
        this.description = description;
    }

    private IfcRoleEnum role;
    private IfcLabel userDefinedRole;
    private IfcText description;
}
