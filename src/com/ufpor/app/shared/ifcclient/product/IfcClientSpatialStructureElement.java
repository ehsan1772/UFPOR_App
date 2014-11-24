package com.ufpor.app.shared.ifcclient.product;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifckernel.IfcElementCompositionEnum;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */


public abstract class IfcClientSpatialStructureElement extends IfcClientSpatialElement {

    protected IfcElementCompositionEnum compositionType;

    protected IfcClientSpatialStructureElement(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcClientSpatialStructureElement() {

    }

    public IfcElementCompositionEnum getCompositionType() {
        return compositionType;
    }

    public void setCompositionType(IfcElementCompositionEnum compositionType) {
        this.compositionType = compositionType;
    }
}
