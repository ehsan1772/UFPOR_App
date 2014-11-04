package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public class IfcDecPersonAndOrganization implements Serializable {

    public IfcDecPersonAndOrganization(IfcDecPerson thePerson, IfcDecOrganization theOrganization) {
        this.thePerson = thePerson;
        this.theOrganization = theOrganization;
    }

    private IfcDecPerson thePerson;
    private IfcDecOrganization theOrganization;
    private List<IfcDecActorRole> roles;

    private IfcDecPersonAndOrganization() {
    }
}
