package com.ufpor.app.shared.ifckernel;

import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcOrganization {
    private IfcIdentifier idendification;
    private IfcLabel name;
    private List<IfcActorRole> roles;
    private List<IfcAddress> addressed;

    public IfcOrganization(IfcLabel name) {

        this.name = name;
    }

    public IfcIdentifier getIdendification() {
        return idendification;
    }

    public void setIdendification(IfcIdentifier idendification) {
        this.idendification = idendification;
    }

    public IfcLabel getName() {
        return name;
    }

    public List<IfcActorRole> getRoles() {
        return roles;
    }

    public void setRoles(List<IfcActorRole> roles) {
        this.roles = roles;
    }

    public List<IfcAddress> getAddressed() {
        return addressed;
    }

    public void setAddressed(List<IfcAddress> addressed) {
        this.addressed = addressed;
    }
}
