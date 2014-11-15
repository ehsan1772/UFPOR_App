package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientText implements IfcClientSimpleValue {
    public IfcClientText(String value) {
        this.value = value;
    }

    private IfcClientText() {
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
