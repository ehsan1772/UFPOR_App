package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcDecTimeStamp implements Serializable {
    private Long value;

    public IfcDecTimeStamp(Long value) {
        this.value = value;
    }

    private IfcDecTimeStamp() {
    }

    public Long getValue() {
        return value;
    }
}
