package com.ufpor.app.shared.ifcclient.decproduct;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifcclient.IfcDecObject;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */


public abstract class IfcDecProduct extends IfcDecObject {

    protected IfcDecObjectPlacement objectPlacement;

    protected IfcDecProductionRepresentation representation;

    protected IfcDecProduct(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcDecProduct() {
    }

    public IfcDecProductionRepresentation getRepresentation() {
        return representation;
    }

    public void setRepresentation(IfcDecProductionRepresentation representation) {
        this.representation = representation;
    }

    public IfcDecObjectPlacement getObjectPlacement() {

        return objectPlacement;
    }

    public void setObjectPlacement(IfcDecObjectPlacement objectPlacement) {
        this.objectPlacement = objectPlacement;
    }
}
