package com.ufpor.app.shared.ifcclient.decproduct;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifcclient.IfcDecLabel;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */

public abstract class IfcDecSpatialElement extends IfcDecProduct {
    protected IfcDecLabel longName;

    protected IfcDecSpatialElement(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcDecSpatialElement() {
    }

    public IfcDecLabel getLongName() {
        return longName;
    }

    public void setLongName(IfcDecLabel longName) {
        this.longName = longName;
    }
}
