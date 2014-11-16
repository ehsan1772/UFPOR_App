package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientReal implements IfcClientSimpleValue{
    private double value;

    public IfcClientReal(double value) {
        this.value = value;
    }

    public IfcClientReal() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
