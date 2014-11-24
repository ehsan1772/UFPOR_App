package com.ufpor.app.shared.ifcdeckernel.type;

import com.ufpor.app.shared.ifcclient.type.IfcClientSpatialZoneType;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecSpatialZoneType extends IfcDecSpatialElementType {
    @Persistent
    private IfcClientSpatialZoneType.IfcSpatialZoneType predefinedType;
    @Persistent
    private String longName;

    public IfcClientSpatialZoneType.IfcSpatialZoneType getPredefinedType() {
        return predefinedType;
    }

    public void setPredefinedType(IfcClientSpatialZoneType.IfcSpatialZoneType predefinedType) {
        this.predefinedType = predefinedType;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
