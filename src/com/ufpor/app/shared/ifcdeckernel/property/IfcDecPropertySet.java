package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.shared.ifcclient.IfcClientProperty;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySet;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySingleValue;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecPropertySet extends IfcDecPropertySetDefinition {
    @NotPersistent
    protected ArrayList<IfcDecProperty> properties;

    @Persistent
    protected ArrayList<IfcDecPropertySingleValue> properties_SingleValue;

    public IfcDecPropertySet() {
        properties = new ArrayList<IfcDecProperty>();
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

    public ArrayList<IfcDecProperty> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<IfcDecProperty> properties) {
        this.properties = properties;
    }

    public void onPrePut() {
        if (properties != null) {
            properties_SingleValue = new ArrayList<IfcDecPropertySingleValue>();
            for (IfcDecProperty property : properties) {
                IfcDecPropertySingleValue singleValue = (IfcDecPropertySingleValue) property;
                singleValue.onPrePut();
                properties_SingleValue.add(singleValue);
            }
        }

    }

    public void onPostLoad() {
        if (properties_SingleValue != null) {
            properties = new ArrayList<IfcDecProperty>();
            for (IfcDecPropertySingleValue singleValue : properties_SingleValue) {
                singleValue.onPostLoad();
                properties.add(singleValue);
            }
        }

    }

    public String getIfcString(String properties) {
        String guid = GuidCompressor.getNewIfcGloballyUniqueId();
        String ownerHistory = "*";
        String name = (getName() == null || getName().isEmpty()) ? "*" : getName();
        String description = (getDescription() == null || getDescription().isEmpty()) ? "*" : getDescription();
        String hasProperties = properties;

        return String.format(Constants.IFCPROPERTYSET, guid, ownerHistory, name, description, hasProperties);
    }
}
