package com.ufpor.app.shared.ifcclient.property;

import com.ufpor.app.shared.ifcclient.IfcClientNamedUnit;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientObjective;

/**
 * Created by Ehsan Barekati on 11/22/14.
 */
public abstract class IfcClientPhysicalSimpleQuantity extends IfcClientPhysicalQuantity {
    private IfcClientNamedUnit unit;

    public IfcClientObjective getConstraints() {
        return constraints;
    }

    //this is the IfcResourceConstraintRelationship
    protected IfcClientObjective constraints;

    public void setConstraints(IfcClientObjective constraints) {
        this.constraints = constraints;
    }

    protected IfcClientPhysicalSimpleQuantity(String name) {
        super(name);
    }

    protected IfcClientPhysicalSimpleQuantity() {
    }

    public IfcClientNamedUnit getUnit() {
        return unit;
    }

    public void setUnit(IfcClientNamedUnit unit) {
        this.unit = unit;
    }

    public abstract void setMaxValue(Object value);
    public abstract void setMinValue(Object value);
}
