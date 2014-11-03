package com.ufpor.app.shared.ifckernel;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcIdentifier {
    public IfcIdentifier(String value) {
        this.value = value;
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
