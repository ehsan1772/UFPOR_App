package com.ufpor.app.shared.ifcdeckernel.type;

import com.google.appengine.api.users.User;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class IfcDecSpatialElementType extends IfcDecTypeProduct {
    @Persistent
    private String elementType;

    public IfcDecSpatialElementType(String guid, User user) {
        super(guid, user);
    }

    public IfcDecSpatialElementType() {
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }
}
