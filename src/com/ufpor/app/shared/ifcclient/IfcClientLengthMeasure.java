package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
public class IfcClientLengthMeasure implements Serializable {
    private double value;

    protected IfcClientLengthMeasure() {
    }

    public IfcClientLengthMeasure(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
