package com.ufpor.app.shared.ifcdeckernel.decproduct;

import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifcdeckernel.IfcDecElementCompositionEnum;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecSpatialStructureElement extends IfcDecSpatialElement {

    @Persistent
    protected IfcDecElementCompositionEnum compositionType;

    protected IfcDecSpatialStructureElement(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecSpatialStructureElement() {

    }

    public IfcDecElementCompositionEnum getCompositionType() {
        return compositionType;
    }

    public void setCompositionType(IfcDecElementCompositionEnum compositionType) {
        this.compositionType = compositionType;
    }
}
