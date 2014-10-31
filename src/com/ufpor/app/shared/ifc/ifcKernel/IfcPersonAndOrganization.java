package com.ufpor.app.shared.ifc.ifcKernel;

import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcPersonAndOrganization {
    public IfcPersonAndOrganization(IfcPerson thePerson, IfcOrganization theOrganization) {
        this.thePerson = thePerson;
        this.theOrganization = theOrganization;
    }

    private IfcPerson thePerson;
    private IfcOrganization theOrganization;
    private List<IfcActorRole> roles;
}
