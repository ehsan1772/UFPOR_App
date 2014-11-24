package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.IfcClientReal;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
public class IfcDecReal implements IfcDecSimpleValue, Serializable {
    private double value;

    public IfcDecReal() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public IfcDecReal(double value) {

        this.value = value;
    }

    public static IfcDecReal getInstance(IfcClientReal client) {
        return new IfcDecReal(client.getValue());
    }

    @Override
    public String getIfcString() {
        return String.valueOf(value);
    }
}
