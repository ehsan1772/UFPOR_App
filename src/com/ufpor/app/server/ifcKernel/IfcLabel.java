package com.ufpor.app.server.ifcKernel;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcLabel {
    public IfcLabel(String value) {
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
