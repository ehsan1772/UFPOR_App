package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.IfcClientDateTime;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/18/14.
 */
public class IfcDectDateTime implements Serializable{
    private String value;

    public IfcDectDateTime() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public IfcDectDateTime(String value) {

        this.value = value;
    }

    public static IfcDectDateTime getInstance(IfcClientDateTime creatingTime) {
        if (creatingTime != null) {
            return new IfcDectDateTime(creatingTime.getValue());
        }
        return null;
    }
}
