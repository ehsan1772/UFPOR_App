package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.ifcphysical.IfcFileObject;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/23/14.
 */
public abstract class IfcDecPhysicalQuantity implements Serializable, IfcFileObject {
    private String name;
    private String description;
    private int number;

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

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public abstract String getIfcString();
}
