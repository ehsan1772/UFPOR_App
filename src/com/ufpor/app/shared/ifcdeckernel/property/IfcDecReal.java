package com.ufpor.app.shared.ifcdeckernel.property;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
public class IfcDecReal implements IfcDecSimpleValue {
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public IfcDecReal(double value) {

        this.value = value;
    }
}
