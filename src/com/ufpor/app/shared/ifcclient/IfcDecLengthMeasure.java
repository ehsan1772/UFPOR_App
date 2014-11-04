package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
public class IfcDecLengthMeasure implements Serializable {
    private double value;

    protected IfcDecLengthMeasure() {
    }

    public IfcDecLengthMeasure(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
