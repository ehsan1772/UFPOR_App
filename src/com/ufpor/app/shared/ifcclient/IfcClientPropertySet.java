package com.ufpor.app.shared.ifcclient;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientPropertySet extends IfcClientPropertySetDefinition {
    public ArrayList<IfcClientProperty> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<IfcClientProperty> properties) {
        this.properties = properties;
    }

    protected ArrayList<IfcClientProperty> properties;
}
