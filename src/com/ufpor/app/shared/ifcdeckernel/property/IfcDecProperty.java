package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcdeckernel.IfcDecIdentifier;
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
public abstract class IfcDecProperty extends IfcDecPropertyAbstraction {
    @Persistent
    private String name;
    @Persistent
    private IfcDecText description;
    private String ifcString;

    public ArrayList<IfcDecConstraint> getConstraints() {
        return constraints;
    }

    @Persistent(serialized = "true")
    private ArrayList<IfcDecConstraint> constraints = new ArrayList<IfcDecConstraint>();

    public IfcDecText getDescription() {
        return description;
    }

    public void setDescription(IfcDecText description) {
        this.description = description;
    }

    public IfcDecIdentifier getName() {
        return new IfcDecIdentifier(name);
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addConstraint(IfcDecConstraint constraint) {
        constraints.add(constraint);
    }

    public abstract String getIfcString();
}
