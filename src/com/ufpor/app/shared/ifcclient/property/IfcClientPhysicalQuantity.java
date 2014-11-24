package com.ufpor.app.shared.ifcclient.property;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/22/14.
 */
public abstract class IfcClientPhysicalQuantity implements Serializable {
    private String name;
    private String description;

    protected IfcClientPhysicalQuantity(String name) {
        this.name = name;
    }

    protected IfcClientPhysicalQuantity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
