package com.ufpor.app.shared.ifckernel;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcText implements Serializable {
    public IfcText(String value) {
        this.value = value;
    }

    private IfcText() {
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
