package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecText;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecActorRole implements Serializable, IfcFileObject {
    private int number;
    private IfcDecRoleEnum role;
    private IfcDecLabel userDefinedRole;
    private IfcDecText description;

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

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        return null;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return null;
    }
}
