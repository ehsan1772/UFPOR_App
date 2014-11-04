package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public class IfcDecOrganization implements Serializable{

    private IfcDecIdentifier idendification;
    private IfcDecLabel name;
    private List<IfcDecActorRole> roles;
    private List<IfcDecAddress> addressed;

    public IfcDecOrganization(IfcDecLabel name) {

        this.name = name;
    }

    private IfcDecOrganization() {
    }

    public IfcDecIdentifier getIdendification() {
        return idendification;
    }

    public void setIdendification(IfcDecIdentifier idendification) {
        this.idendification = idendification;
    }

    public IfcDecLabel getName() {
        return name;
    }

    public List<IfcDecActorRole> getRoles() {
        return roles;
    }

    public void setRoles(List<IfcDecActorRole> roles) {
        this.roles = roles;
    }

    public List<IfcDecAddress> getAddressed() {
        return addressed;
    }

    public void setAddressed(List<IfcDecAddress> addressed) {
        this.addressed = addressed;
    }
}
