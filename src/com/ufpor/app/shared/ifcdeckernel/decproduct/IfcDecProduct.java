package com.ufpor.app.shared.ifcdeckernel.decproduct;

import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObject;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecProduct extends IfcDecObject {
    @Persistent
    protected IfcDecObjectPlacement objectPlacement;
    @Persistent
    protected IfcDecProductionRepresentation representation;

    protected IfcDecProduct(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecProduct() {
    }

    public IfcDecProductionRepresentation getRepresentation() {
        return representation;
    }

    public void setRepresentation(IfcDecProductionRepresentation representation) {
        this.representation = representation;
    }

    public IfcDecObjectPlacement getObjectPlacement() {

        return objectPlacement;
    }

    public void setObjectPlacement(IfcDecObjectPlacement objectPlacement) {
        this.objectPlacement = objectPlacement;
    }
}
