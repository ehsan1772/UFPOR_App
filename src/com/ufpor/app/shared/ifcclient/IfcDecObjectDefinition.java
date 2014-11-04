package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.client.LoginInfo;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public abstract class IfcDecObjectDefinition extends IfcDecRoot {
    protected IfcDecObjectDefinition(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcDecObjectDefinition() {
    }
}
