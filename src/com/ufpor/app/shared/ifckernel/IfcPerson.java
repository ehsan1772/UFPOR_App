package com.ufpor.app.shared.ifckernel;

import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcPerson  {
    private IfcIdentifier identification;
    private IfcLabel familyName;
    private IfcLabel givenName;
    private List<IfcLabel> middleNames;
    private List<IfcLabel> prefixTitles;
    private List<IfcLabel> suffixTitles;
    private List<IfcActorRole> roles;
    private IfcAddress address;

    public IfcPerson() {
    }

    public IfcIdentifier getIdentification() {
        return identification;
    }

    public void setIdentification(IfcIdentifier identification) {
        this.identification = identification;
    }

    public IfcLabel getFamilyName() {
        return familyName;
    }

    public void setFamilyName(IfcLabel familyName) {
        this.familyName = familyName;
    }

    public IfcLabel getGivenName() {
        return givenName;
    }

    public void setGivenName(IfcLabel givenName) {
        this.givenName = givenName;
    }

    public List<IfcLabel> getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(List<IfcLabel> middleNames) {
        this.middleNames = middleNames;
    }

    public List<IfcLabel> getPrefixTitles() {
        return prefixTitles;
    }

    public void setPrefixTitles(List<IfcLabel> prefixTitles) {
        this.prefixTitles = prefixTitles;
    }

    public List<IfcLabel> getSuffixTitles() {
        return suffixTitles;
    }

    public void setSuffixTitles(List<IfcLabel> suffixTitles) {
        this.suffixTitles = suffixTitles;
    }

    public List<IfcActorRole> getRoles() {
        return roles;
    }

    public void setRoles(List<IfcActorRole> roles) {
        this.roles = roles;
    }

    public IfcAddress getAddress() {
        return address;
    }

    public void setAddress(IfcAddress address) {
        this.address = address;
    }
}
