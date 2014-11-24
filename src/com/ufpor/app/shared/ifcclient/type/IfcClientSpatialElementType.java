package com.ufpor.app.shared.ifcclient.type;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
public abstract class IfcClientSpatialElementType extends IfcClientTypeProduct {
    private String elementType;

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }
}
