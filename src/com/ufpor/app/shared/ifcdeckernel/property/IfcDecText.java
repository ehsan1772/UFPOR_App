package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.IfcClientText;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
public class IfcDecText implements IfcDecSimpleValue, Serializable {
    private String value;

    public IfcDecText() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public IfcDecText(String value) {

        this.value = value;
    }

    public static IfcDecText getInstance(IfcClientText client) {
        if (client != null) {
            return new IfcDecText(client.getValue());
        }
        return null;
    }
}
