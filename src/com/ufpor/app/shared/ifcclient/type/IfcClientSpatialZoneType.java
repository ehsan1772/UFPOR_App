package com.ufpor.app.shared.ifcclient.type;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
public class IfcClientSpatialZoneType extends IfcClientSpatialElementType {
    public enum IfcSpatialZoneType {	CONSTRUCTION,
        FIRESAFETY,
        LIGHTING,
        OCCUPANCY,
        SECURITY,
        THERMAL,
        TRANSPORT,
        VENTILATION,
        USERDEFINED,
        NOTDEFINED};

    private IfcSpatialZoneType predefinedType;
    private String longName;

    public IfcSpatialZoneType getPredefinedType() {
        return predefinedType;
    }

    public void setPredefinedType(IfcSpatialZoneType predefinedType) {
        this.predefinedType = predefinedType;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
