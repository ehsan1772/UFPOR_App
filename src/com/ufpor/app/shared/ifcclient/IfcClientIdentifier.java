package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientIdentifier implements Serializable {
    public IfcClientIdentifier(String value) {
        this.value = value;
    }

    private IfcClientIdentifier() {
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
