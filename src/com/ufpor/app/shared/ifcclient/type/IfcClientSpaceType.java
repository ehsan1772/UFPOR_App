package com.ufpor.app.shared.ifcclient.type;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
public class IfcClientSpaceType extends IfcClientSpatialStructureElementType {
    private IfcSpaceTypeEnum predefinedType;
    private String longName;

    public IfcSpaceTypeEnum getPredefinedType() {
        return predefinedType;
    }

    public void setPredefinedType(IfcSpaceTypeEnum predefinedType) {
        this.predefinedType = predefinedType;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public enum IfcSpaceTypeEnum {
        SPACE,
        PARKING,
        GFA,
        INTERNAL,
        EXTERNAL,
        USERDEFINED,
        NOTDEFINED
    }


}
