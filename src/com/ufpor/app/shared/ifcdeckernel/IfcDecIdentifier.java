package com.ufpor.app.shared.ifcdeckernel;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecIdentifier implements Serializable {
    public IfcDecIdentifier(String value) {
        this.value = value;
    }

    private IfcDecIdentifier() {
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
