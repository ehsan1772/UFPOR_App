package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientInteger extends IfcClientSimpleValue {
    private int value;

    public IfcClientInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
