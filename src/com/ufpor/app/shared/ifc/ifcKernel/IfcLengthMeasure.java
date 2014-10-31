package com.ufpor.app.shared.ifc.ifcKernel;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
public class IfcLengthMeasure {
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public IfcLengthMeasure(double value) {

        this.value = value;
    }
}
