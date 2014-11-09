package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public class IfcClientOrganization implements Serializable{

    private IfcClientIdentifier idendification;
    private IfcClientLabel name;
    private List<IfcClientActorRole> roles;
    private List<IfcClientAddress> addressed;

    public IfcClientOrganization(IfcClientLabel name) {

        this.name = name;
    }

    private IfcClientOrganization() {
    }

    public IfcClientIdentifier getIdendification() {
        return idendification;
    }

    public void setIdendification(IfcClientIdentifier idendification) {
        this.idendification = idendification;
    }

    public IfcClientLabel getName() {
        return name;
    }

    public List<IfcClientActorRole> getRoles() {
        return roles;
    }

    public void setRoles(List<IfcClientActorRole> roles) {
        this.roles = roles;
    }

    public List<IfcClientAddress> getAddressed() {
        return addressed;
    }

    public void setAddressed(List<IfcClientAddress> addressed) {
        this.addressed = addressed;
    }
}
