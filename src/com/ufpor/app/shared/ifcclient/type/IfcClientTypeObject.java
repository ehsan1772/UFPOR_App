package com.ufpor.app.shared.ifcclient.type;

import com.ufpor.app.shared.ifcclient.IfcClientIdentifier;
import com.ufpor.app.shared.ifcclient.IfcClientObject;
import com.ufpor.app.shared.ifcclient.IfcClientObjectDefinition;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySetDefinition;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
public abstract class IfcClientTypeObject extends IfcClientObjectDefinition {
    private IfcClientIdentifier applicationOccurance;
    private ArrayList<IfcClientPropertySetDefinition> properties;
    //this property represents IfcRelDefinesByType relationship between a type and an object
    //this is how we can connect a SpaceType to a space
    //TODO move it to the object

    public ArrayList<IfcClientObject> getRelatedObjects() {
        return relatedObjects;
    }

    public void setRelatedObjects(ArrayList<IfcClientObject> relatedObjects) {
        this.relatedObjects = relatedObjects;
    }

    public ArrayList<IfcClientPropertySetDefinition> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<IfcClientPropertySetDefinition> properties) {
        this.properties = properties;
    }

    public IfcClientIdentifier getApplicationOccurance() {
        return applicationOccurance;
    }

    public void setApplicationOccurance(IfcClientIdentifier applicationOccurance) {
        this.applicationOccurance = applicationOccurance;
    }

    public void addPropertySet(IfcClientPropertySetDefinition set) {
        if (properties == null) {
            properties = new ArrayList<IfcClientPropertySetDefinition>();
        }

        if (!properties.contains(set)) {
            properties.add(set);
        }
    }

    private ArrayList<IfcClientObject> relatedObjects;
}
