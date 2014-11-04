package com.ufpor.app.shared.ifcclient.decproduct;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifckernel.IfcElementCompositionEnum;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */


public abstract class IfcDecSpatialStructureElement extends IfcDecSpatialElement {

    protected IfcElementCompositionEnum compositionType;

    protected IfcDecSpatialStructureElement(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcDecSpatialStructureElement() {

    }

    public IfcElementCompositionEnum getCompositionType() {
        return compositionType;
    }

    public void setCompositionType(IfcElementCompositionEnum compositionType) {
        this.compositionType = compositionType;
    }
}
