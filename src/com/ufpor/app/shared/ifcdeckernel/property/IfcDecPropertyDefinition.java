package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcdeckernel.IfcDecDefinitionSelect;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecConstraint;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecPropertyDefinition extends IfcDecRoot implements IfcDecDefinitionSelect {
    @Persistent(serialized = "true")
    protected ArrayList<IfcDecConstraint> constraints = new ArrayList<IfcDecConstraint>();

    public ArrayList<IfcDecConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(ArrayList<IfcDecConstraint> constraints) {
        this.constraints = constraints;
    }
}
