package com.ufpor.app.shared.ifcdeckernel.decproduct;

import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifcdeckernel.IfcDecLengthMeasure;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecSpace extends IfcDecSpatialStructureElement {
    @Persistent
    protected IfcDecSpaceTypeEnum predefinedType;
    @Persistent
    protected IfcDecLengthMeasure elevationWithFlooring;

    public IfcDecSpace(String GUID, User user) {
        super(GUID, user);
    }

    protected IfcDecSpace() {
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
