package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public class IfcClientPersonAndOrganization implements Serializable {

    public IfcClientPersonAndOrganization(IfcClientPerson thePerson, IfcClientOrganization theOrganization) {
        this.thePerson = thePerson;
        this.theOrganization = theOrganization;
    }

    private IfcClientPerson thePerson;
    private IfcClientOrganization theOrganization;
    private List<IfcClientActorRole> roles;

    private IfcClientPersonAndOrganization() {
    }
}
