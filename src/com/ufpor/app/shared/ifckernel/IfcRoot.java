package com.ufpor.app.shared.ifckernel;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcRoot {
    private IfcGloballyUniqueId globalId;
    private IfcOwnerHistory ownerHistory;
    private IfcLabel name;
    private IfcText description;

    protected IfcRoot() {
        //TODO GUID is acquired by calling the GUID service and then passing it to the constructors to create
        //Ifc objects
        globalId = new IfcGloballyUniqueId("fix this");
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
