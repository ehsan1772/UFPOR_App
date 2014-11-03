package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.datastore.Key;
import com.ufpor.app.shared.ifckernel.IfcPerson;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
public class IfcDecPersonAndOrganization implements Serializable {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
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
