package com.ufpor.app.shared.ifcdeckernel;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecText implements Serializable {
    public IfcDecText(String value) {
        this.value = value;
    }

    private IfcDecText() {
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
