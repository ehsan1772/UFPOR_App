package com.ufpor.app.shared.ifcclient;


import com.ufpor.app.client.LoginInfo;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */


public abstract class IfcClientObject extends IfcClientObjectDefinition {
    protected IfcClientLabel objectType;

    public IfcClientLabel getObjectType() {
        return objectType;
    }

    public void setObjectType(IfcClientLabel objectType) {
        this.objectType = objectType;
    }

    protected IfcClientObject(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcClientObject() {
    }
}
