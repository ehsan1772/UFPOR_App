package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientText extends IfcClientSimpleValue implements Serializable{
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
