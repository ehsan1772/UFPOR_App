package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;


public class IfcClientGloballyUniqueId implements Serializable {

    private String value;

    public IfcClientGloballyUniqueId(String value) {
        this.value = value;
    }

    private IfcClientGloballyUniqueId() {
    }

    public String getValue() {
        return value;
    }
}
