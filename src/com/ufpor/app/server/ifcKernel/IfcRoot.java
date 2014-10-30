package com.ufpor.app.server.ifcKernel;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcRoot {
    private IfcGloballyUniqueId globalId;
    private IfcOwnerHistory ownerHistory;
    private IfcLabel name;
    private IfcText description;

    protected IfcRoot() {
        globalId = new IfcGloballyUniqueId();
    }

    public IfcGloballyUniqueId getGlobalId() {
        return globalId;
    }

    public IfcOwnerHistory getOwnerHistory() {
        return ownerHistory;
    }

    public void setOwnerHistory(IfcOwnerHistory ownerHistory) {

        this.ownerHistory = ownerHistory;
    }

    public IfcLabel getName() {
        return name;
    }

    public void setName(IfcLabel name) {
        this.name = name;
    }

    public IfcText getDescription() {
        return description;
    }

    public void setDescription(IfcText description) {
        this.description = description;
    }
}
