package com.ufpor.app.shared.ifcdeckernel.property;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
public class IfcDecText implements IfcDecSimpleValue {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public IfcDecText(String value) {

        this.value = value;
    }
}
