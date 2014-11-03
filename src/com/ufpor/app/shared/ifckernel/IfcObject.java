package com.ufpor.app.shared.ifckernel;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcObject extends IfcObjectDefinition {
    public IfcLabel getObjectType() {
        return objectType;
    }

    public void setObjectType(IfcLabel objectType) {
        this.objectType = objectType;
    }

    protected IfcLabel objectType;
}
