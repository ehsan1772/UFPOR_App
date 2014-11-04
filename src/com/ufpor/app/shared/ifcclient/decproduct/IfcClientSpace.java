package com.ufpor.app.shared.ifcclient.decproduct;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifcclient.IfcDecLengthMeasure;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */

public class IfcClientSpace extends IfcDecSpatialStructureElement {

    protected IfcDecSpaceTypeEnum predefinedType;

    protected IfcDecLengthMeasure elevationWithFlooring;

    public IfcClientSpace(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcClientSpace() {
        super();
    }

    public IfcDecSpaceTypeEnum getPredefinedType() {
        return predefinedType;
    }

    public void setPredefinedType(IfcDecSpaceTypeEnum predefinedType) {
        this.predefinedType = predefinedType;
    }

    public IfcDecLengthMeasure getElevationWithFlooring() {
        return elevationWithFlooring;
    }

    public void setElevationWithFlooring(IfcDecLengthMeasure elevationWithFlooring) {
        this.elevationWithFlooring = elevationWithFlooring;
    }
}
