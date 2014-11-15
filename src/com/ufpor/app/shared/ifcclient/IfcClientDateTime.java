package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientDateTime implements Serializable {
    public IfcClientDateTime() {
    }

    public String getValue() {
        return value;
    }

    public IfcClientDateTime(String value) {

        this.value = value;
    }

    private String value;
}
