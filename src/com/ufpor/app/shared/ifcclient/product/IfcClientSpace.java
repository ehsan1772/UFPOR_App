package com.ufpor.app.shared.ifcclient.product;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifcclient.IfcClientLengthMeasure;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */

public class IfcClientSpace extends IfcClientSpatialStructureElement {

    protected IfcClientSpaceTypeEnum predefinedType;

    protected IfcClientLengthMeasure elevationWithFlooring;

    public IfcClientSpace(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcClientSpace() {
        super();
    }

    public IfcClientSpaceTypeEnum getPredefinedType() {
        return predefinedType;
    }

    public void setPredefinedType(IfcClientSpaceTypeEnum predefinedType) {
        this.predefinedType = predefinedType;
    }

    public IfcClientLengthMeasure getElevationWithFlooring() {
        return elevationWithFlooring;
    }

    public void setElevationWithFlooring(IfcClientLengthMeasure elevationWithFlooring) {
        this.elevationWithFlooring = elevationWithFlooring;
    }
}
