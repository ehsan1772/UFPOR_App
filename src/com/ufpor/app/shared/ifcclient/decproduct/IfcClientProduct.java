package com.ufpor.app.shared.ifcclient.decproduct;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifcclient.IfcClientObject;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */


public abstract class IfcClientProduct extends IfcClientObject {

    protected IfcClientObjectPlacement objectPlacement;

    protected IfcClientProductionRepresentation representation;

    protected IfcClientProduct(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcClientProduct() {
    }

    public IfcClientProductionRepresentation getRepresentation() {
        return representation;
    }

    public void setRepresentation(IfcClientProductionRepresentation representation) {
        this.representation = representation;
    }

    public IfcClientObjectPlacement getObjectPlacement() {

        return objectPlacement;
    }

    public void setObjectPlacement(IfcClientObjectPlacement objectPlacement) {
        this.objectPlacement = objectPlacement;
    }
}
