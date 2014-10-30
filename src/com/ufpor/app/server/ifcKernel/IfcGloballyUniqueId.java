package com.ufpor.app.server.ifcKernel;

import java.util.UUID;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcGloballyUniqueId {
    private String value;

    //TODO this is not following IFC standards. fix it!
    public IfcGloballyUniqueId() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }
}
