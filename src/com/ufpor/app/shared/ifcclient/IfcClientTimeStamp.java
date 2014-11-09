package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientTimeStamp implements Serializable {
    private Long value;

    public IfcClientTimeStamp(Long value) {
        this.value = value;
    }

    private IfcClientTimeStamp() {
    }

    public Long getValue() {
        return value;
    }
}
