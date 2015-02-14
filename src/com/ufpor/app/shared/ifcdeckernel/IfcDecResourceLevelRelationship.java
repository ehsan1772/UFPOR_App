package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.server.ifcphysical.IfcFileObject;

/**
 * Created by Ehsan Barekati on 12/16/14.
 */
public abstract class IfcDecResourceLevelRelationship<T extends IfcFileObject> extends IfcFileObjectRelationship {
    String name;
    String description;

    public IfcDecResourceLevelRelationship(T owner) {
        super(owner);
    }

    protected IfcDecResourceLevelRelationship() {
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
