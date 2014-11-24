package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.IfcClientInteger;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
public class IfcDecInteger implements IfcDecSimpleValue, Serializable {
    private int value;

    public IfcDecInteger() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public IfcDecInteger(int value) {

        this.value = value;
    }

    @Override
    public String getIfcString() {
        return String.valueOf(value);
    }

    public static IfcDecInteger getInstance(IfcClientInteger client) {
        return new IfcDecInteger(client.getValue());
    }
}
