package com.ufpor.app.shared.ifcclient.measure;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientAreaMeasure implements IfcClientMeasureValue {
    public IfcClientAreaMeasure(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    private double value;

    private IfcClientAreaMeasure() {
    }
}
