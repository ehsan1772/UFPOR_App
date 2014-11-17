package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.IfcClientProperty;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySet;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySingleValue;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecPropertySet extends IfcDecPropertySetDefinition {
    protected ArrayList<IfcDecProperty> properties;

    public IfcDecPropertySet() {
        properties = new ArrayList<IfcDecProperty>();
    }

    public ArrayList<IfcDecProperty> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<IfcDecProperty> properties) {
        this.properties = properties;
    }

    public static IfcDecPropertySet getInstance(IfcClientPropertySet client) {
        ArrayList<IfcDecProperty> properties = new ArrayList<IfcDecProperty>();
        for (IfcClientProperty prop : client.getProperties()) {
            if (prop instanceof IfcClientPropertySingleValue) {
                properties.add(IfcDecPropertySingleValue.getInstance((IfcClientPropertySingleValue) prop));
            }
        }
        IfcDecPropertySet result = new IfcDecPropertySet();
        result.setProperties(properties);
        return result;
    }
}
