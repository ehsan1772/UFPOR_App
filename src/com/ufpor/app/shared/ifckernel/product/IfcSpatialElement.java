package com.ufpor.app.shared.ifckernel.product;

import com.ufpor.app.shared.ifckernel.IfcLabel;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */

public abstract class IfcSpatialElement extends IfcProduct {
    protected IfcLabel longName;

    public IfcLabel getLongName() {
        return longName;
    }

    public void setLongName(IfcLabel longName) {
        this.longName = longName;
    }
}
