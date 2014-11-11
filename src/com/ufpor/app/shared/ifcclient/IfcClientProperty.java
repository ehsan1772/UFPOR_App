package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientProperty extends IfcClientPropertyAbstraction {
    private IfcClientIdentifier name;
    private IfcClientText description;

    public IfcClientIdentifier getName() {
        return name;
    }

    public void setName(IfcClientIdentifier name) {
        this.name = name;
    }

    public IfcClientText getDescription() {
        return description;
    }

    public void setDescription(IfcClientText description) {
        this.description = description;
    }
}
