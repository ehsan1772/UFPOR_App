package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;


public class IfcDecGloballyUniqueId implements Serializable {

    private String value;

    public IfcDecGloballyUniqueId(String value) {
        this.value = value;
    }

    private IfcDecGloballyUniqueId() {
    }

    public String getValue() {
        return value;
    }
}
