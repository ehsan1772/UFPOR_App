package com.ufpor.app.shared.ifcdeckernel.property;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/23/14.
 */
public abstract class IfcDecPhysicalQuantity implements Serializable {
    private String name;
    private String description;

    protected IfcDecPhysicalQuantity() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected IfcDecPhysicalQuantity(String name) {

        this.name = name;
    }

    public abstract String getIfcString();
}
