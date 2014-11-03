package com.ufpor.app.shared.ifcdeckernel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecPerson implements Serializable {
    private IfcDecIdentifier identification;
    private IfcDecLabel familyName;
    private IfcDecLabel givenName;
    private List<IfcDecLabel> middleNames;
    private List<IfcDecLabel> prefixTitles;
    private List<IfcDecLabel> suffixTitles;
    private List<IfcDecActorRole> roles;
    private IfcDecAddress address;

    public IfcDecPerson() {
    }

    public IfcDecIdentifier getIdentification() {
        return identification;
    }

    public void setIdentification(IfcDecIdentifier identification) {
        this.identification = identification;
    }

    public IfcDecLabel getFamilyName() {
        return familyName;
    }

    public void setFamilyName(IfcDecLabel familyName) {
        this.familyName = familyName;
    }

    public IfcDecLabel getGivenName() {
        return givenName;
    }

    public void setGivenName(IfcDecLabel givenName) {
        this.givenName = givenName;
    }

    public List<IfcDecLabel> getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(List<IfcDecLabel> middleNames) {
        this.middleNames = middleNames;
    }

    public List<IfcDecLabel> getPrefixTitles() {
        return prefixTitles;
    }

    public void setPrefixTitles(List<IfcDecLabel> prefixTitles) {
        this.prefixTitles = prefixTitles;
    }

    public List<IfcDecLabel> getSuffixTitles() {
        return suffixTitles;
    }

    public void setSuffixTitles(List<IfcDecLabel> suffixTitles) {
        this.suffixTitles = suffixTitles;
    }

    public List<IfcDecActorRole> getRoles() {
        return roles;
    }

    public void setRoles(List<IfcDecActorRole> roles) {
        this.roles = roles;
    }

    public IfcDecAddress getAddress() {
        return address;
    }

    public void setAddress(IfcDecAddress address) {
        this.address = address;
    }
}
