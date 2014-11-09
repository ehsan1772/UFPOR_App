package com.ufpor.app.shared.ifcclient.decproduct;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */

public abstract class IfcClientSpatialElement extends IfcClientProduct {
    protected IfcClientLabel longName;

    protected IfcClientSpatialElement(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcClientSpatialElement() {
    }

    public IfcClientLabel getLongName() {
        return longName;
    }

    public void setLongName(IfcClientLabel longName) {
        this.longName = longName;
    }
}
