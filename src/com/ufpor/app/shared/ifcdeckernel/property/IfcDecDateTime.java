package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.IfcClientDateTime;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/18/14.
 */
public class IfcDecDateTime implements Serializable{
    private String value;

    public IfcDecDateTime() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public IfcDecDateTime(String value) {

        this.value = value;
    }

    public static IfcDecDateTime getInstance(IfcClientDateTime creatingTime) {
        if (creatingTime != null) {
            return new IfcDecDateTime(creatingTime.getValue());
        }
        return null;
    }

    public static IfcClientDateTime getInstance(IfcDecDateTime creatingTime) {
        if (creatingTime != null) {
            return new IfcClientDateTime(creatingTime.getValue());
        }
        return null;
    }
}
