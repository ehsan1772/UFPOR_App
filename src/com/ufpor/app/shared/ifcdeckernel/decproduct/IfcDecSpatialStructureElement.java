package com.ufpor.app.shared.ifcdeckernel.decproduct;

import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifckernel.IfcElementCompositionEnum;

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
    protected IfcElementCompositionEnum compositionType;

    protected IfcDecSpatialStructureElement(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecSpatialStructureElement() {

    }

    public IfcElementCompositionEnum getCompositionType() {
        return compositionType;
    }

    public void setCompositionType(IfcElementCompositionEnum compositionType) {
        this.compositionType = compositionType;
    }
}
