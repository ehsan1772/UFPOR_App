package com.ufpor.app.shared.ifcdeckernel.property;

/**
 * Created by Ehsan Barekati on 11/23/14.
 */
public abstract class IfcDecPhysicalSimpleQuantity extends IfcDecPhysicalQuantity {
    //optional
    private IfcDecNamedUnit unit;

    public IfcDecNamedUnit getUnit() {
        return unit;
    }

    public void setUnit(IfcDecNamedUnit unit) {
        this.unit = unit;
    }

    protected IfcDecPhysicalSimpleQuantity(String name) {
        super(name);

    }

    protected IfcDecPhysicalSimpleQuantity() {
    }
}
