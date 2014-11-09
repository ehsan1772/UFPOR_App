package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientPerson implements Serializable {
    private IfcClientIdentifier identification;
    private IfcClientLabel familyName;
    private IfcClientLabel givenName;
    private List<IfcClientLabel> middleNames;
    private List<IfcClientLabel> prefixTitles;
    private List<IfcClientLabel> suffixTitles;
    private List<IfcClientActorRole> roles;
    private IfcClientAddress address;

    public IfcClientPerson() {
    }

    public IfcClientIdentifier getIdentification() {
        return identification;
    }

    public void setIdentification(IfcClientIdentifier identification) {
        this.identification = identification;
    }

    public IfcClientLabel getFamilyName() {
        return familyName;
    }

    public void setFamilyName(IfcClientLabel familyName) {
        this.familyName = familyName;
    }

    public IfcClientLabel getGivenName() {
        return givenName;
    }

    public void setGivenName(IfcClientLabel givenName) {
        this.givenName = givenName;
    }

    public List<IfcClientLabel> getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(List<IfcClientLabel> middleNames) {
        this.middleNames = middleNames;
    }

    public List<IfcClientLabel> getPrefixTitles() {
        return prefixTitles;
    }

    public void setPrefixTitles(List<IfcClientLabel> prefixTitles) {
        this.prefixTitles = prefixTitles;
    }

    public List<IfcClientLabel> getSuffixTitles() {
        return suffixTitles;
    }

    public void setSuffixTitles(List<IfcClientLabel> suffixTitles) {
        this.suffixTitles = suffixTitles;
    }

    public List<IfcClientActorRole> getRoles() {
        return roles;
    }

    public void setRoles(List<IfcClientActorRole> roles) {
        this.roles = roles;
    }

    public IfcClientAddress getAddress() {
        return address;
    }

    public void setAddress(IfcClientAddress address) {
        this.address = address;
    }
}
