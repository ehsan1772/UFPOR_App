package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifcclient.select.IfcClientDefinitionSelect;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public abstract class IfcClientObjectDefinition extends IfcClientRoot implements IfcClientDefinitionSelect {
    protected IfcClientObjectDefinition(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcClientObjectDefinition() {
    }
}
