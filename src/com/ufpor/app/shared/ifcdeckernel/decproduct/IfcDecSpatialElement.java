package com.ufpor.app.shared.ifcdeckernel.decproduct;

import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifckernel.IfcLabel;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecSpatialElement extends IfcDecProduct {
    @Persistent
    protected IfcLabel longName;

    protected IfcDecSpatialElement(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecSpatialElement() {
    }

    public IfcLabel getLongName() {
        return longName;
    }

    public void setLongName(IfcLabel longName) {
        this.longName = longName;
    }
}
