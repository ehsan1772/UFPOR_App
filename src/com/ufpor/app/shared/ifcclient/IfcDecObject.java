package com.ufpor.app.shared.ifcclient;


import com.ufpor.app.client.LoginInfo;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */


public abstract class IfcDecObject extends IfcDecObjectDefinition {
    protected IfcDecLabel objectType;

    public IfcDecLabel getObjectType() {
        return objectType;
    }

    public void setObjectType(IfcDecLabel objectType) {
        this.objectType = objectType;
    }

    protected IfcDecObject(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcDecObject() {
    }
}
