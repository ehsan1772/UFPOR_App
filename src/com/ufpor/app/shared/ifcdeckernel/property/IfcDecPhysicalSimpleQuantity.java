package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.constraint.IfcClientObjective;
import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecObjective;

/**
 * Created by Ehsan Barekati on 11/23/14.
 */
public abstract class IfcDecPhysicalSimpleQuantity extends IfcDecPhysicalQuantity {
    //optional
    private IfcDecNamedUnit unit;

    public IfcDecObjective getConstraints() {
        return constraints;
    }

    public void setConstraints(IfcDecObjective constraints) {
        this.constraints = constraints;
    }

    //this is the IfcResourceConstraintRelationship
    protected IfcDecObjective constraints;

    public IfcDecNamedUnit getUnit() {
        return unit;
    }

    public void setUnit(IfcDecNamedUnit unit) {
        this.unit = unit;
    }

    protected IfcDecPhysicalSimpleQuantity(String name) {
        super(name);
        constraints = new IfcDecObjective(name+"_CONSTRAINT", IfcConstraintEnum.HARD, IfcClientObjective.IfcObjectiveEnum.REQUIREMENT);
    }

    protected IfcDecPhysicalSimpleQuantity() {
      //  constraints = new IfcDecObjective();
    }

    public abstract void setMaxValue(Object value);
    public abstract void setMinValue(Object value);
    public abstract void onPostLoad();

}
