package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.client.LoginInfo;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public abstract class IfcClientObjectDefinition extends IfcClientRoot {
    protected IfcClientObjectDefinition(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcClientObjectDefinition() {
    }
}
