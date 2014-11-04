package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecActorRole implements Serializable {
    public IfcDecActorRole(IfcDecRoleEnum role) {
        this.role = role;
    }

    private IfcDecActorRole() {
    }

    public IfcDecRoleEnum getRole() {

        return role;
    }

    public IfcDecLabel getUserDefinedRole() {

        return userDefinedRole;
    }

    public void setUserDefinedRole(IfcDecLabel userDefinedRole) {
        this.userDefinedRole = userDefinedRole;
    }

    public IfcDecText getDescription() {
        return description;
    }

    public void setDescription(IfcDecText description) {
        this.description = description;
    }

    private IfcDecRoleEnum role;
    private IfcDecLabel userDefinedRole;
    private IfcDecText description;
}
