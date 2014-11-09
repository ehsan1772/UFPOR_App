package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientActorRole implements Serializable {
    public IfcClientActorRole(IfcClientRoleEnum role) {
        this.role = role;
    }

    private IfcClientActorRole() {
    }

    public IfcClientRoleEnum getRole() {

        return role;
    }

    public IfcClientLabel getUserDefinedRole() {

        return userDefinedRole;
    }

    public void setUserDefinedRole(IfcClientLabel userDefinedRole) {
        this.userDefinedRole = userDefinedRole;
    }

    public IfcClientText getDescription() {
        return description;
    }

    public void setDescription(IfcClientText description) {
        this.description = description;
    }

    private IfcClientRoleEnum role;
    private IfcClientLabel userDefinedRole;
    private IfcClientText description;
}
