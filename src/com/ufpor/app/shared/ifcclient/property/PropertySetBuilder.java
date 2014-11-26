package com.ufpor.app.shared.ifcclient.property;

import com.ufpor.app.shared.ifcclient.IfcClientPropertySet;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
public abstract class PropertySetBuilder {
    private IfcClientPropertySet properties;

    protected PropertySetBuilder() {
        properties = new IfcClientPropertySet();
        properties.setName(getPropertSetName());
        properties.setDescription(getPropertSetDescription());
    }

    public IfcClientPropertySet getProperties() {

        return properties;
    }

    public void setProperties(IfcClientPropertySet properties) {
        this.properties = properties;
    }

    public abstract String getPropertSetName();
    public abstract String getPropertSetDescription();
}
