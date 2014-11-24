package com.ufpor.app.shared.ifcclient.property;

import com.ufpor.app.shared.ifcclient.IfcClientNamedUnit;

/**
 * Created by Ehsan Barekati on 11/22/14.
 */
public abstract class IfcClientPhysicalSimpleQuantity extends IfcClientPhysicalQuantity {
    private IfcClientNamedUnit unit;

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
}
