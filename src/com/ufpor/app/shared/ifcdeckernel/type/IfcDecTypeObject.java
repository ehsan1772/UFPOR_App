package com.ufpor.app.shared.ifcdeckernel.type;

import com.google.appengine.api.datastore.PostLoad;
import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PrePut;
import com.google.appengine.api.datastore.PutContext;
import com.ufpor.app.shared.ifcdeckernel.IfcDecIdentifier;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObjectDefinition;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecElementQuantity;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySet;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinition;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinitionSelect;

import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class IfcDecTypeObject extends IfcDecObjectDefinition {
    @Persistent
    protected IfcDecIdentifier applicableOccurance;
    @NotPersistent
    protected List<IfcDecPropertySetDefinition> hasProperties;

    public IfcDecIdentifier getApplicableOccurance() {
        return applicableOccurance;
    }

    public IfcDecTypeObject() {
        hasProperties = new ArrayList<IfcDecPropertySetDefinition>();
        hasProperties_PropertySet = new ArrayList<IfcDecPropertySet>();
        hasProperties_QuantitySet = new ArrayList<IfcDecElementQuantity>();
    }

    public void addPropert(IfcDecPropertySetDefinition property) {
        hasProperties.add(property);
    }

    public void setApplicableOccurance(IfcDecIdentifier applicableOccurance) {
        this.applicableOccurance = applicableOccurance;
    }

    public List<IfcDecPropertySetDefinition> getHasProperties() {
        return hasProperties;
    }

    public void setHasProperties(List<IfcDecPropertySetDefinition> hasProperties) {
        this.hasProperties = hasProperties;
    }

    public ArrayList<IfcDecPropertySet> getHasProperties_PropertySet() {
        return hasProperties_PropertySet;
    }

    public void setHasProperties_PropertySet(ArrayList<IfcDecPropertySet> hasProperties_PropertySet) {
        this.hasProperties_PropertySet = hasProperties_PropertySet;
    }

    public ArrayList<IfcDecElementQuantity> getHasProperties_QuantitySet() {
        return hasProperties_QuantitySet;
    }

    public void setHasProperties_QuantitySet(ArrayList<IfcDecElementQuantity> hasProperties_QuantitySet) {
        this.hasProperties_QuantitySet = hasProperties_QuantitySet;
    }

    public ArrayList<IfcDecObject> getRelatedObjects() {
        return relatedObjects;
    }

    public void setRelatedObjects(ArrayList<IfcDecObject> relatedObjects) {
        this.relatedObjects = relatedObjects;
    }

    @Persistent
    protected ArrayList<IfcDecPropertySet> hasProperties_PropertySet;

    @Persistent
    protected ArrayList<IfcDecElementQuantity> hasProperties_QuantitySet;
    //this property represents IfcRelDefinesByType relationship between a type and an object
    //this is how we can connect a SpaceType to a space
    //TODO move it to the object
    private ArrayList<IfcDecObject> relatedObjects;

    @PrePut(kinds = {"IfcDecProject"})
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);
        hasProperties_PropertySet = new ArrayList<IfcDecPropertySet>();
        hasProperties_QuantitySet = new ArrayList<IfcDecElementQuantity>();
        for (IfcDecPropertySetDefinitionSelect element : hasProperties) {
            if (element instanceof IfcDecPropertySet) {
                ((IfcDecPropertySet) element).onPrePut();
                hasProperties.add((IfcDecPropertySet) element);
            }

            if (element instanceof IfcDecElementQuantity) {
                ((IfcDecElementQuantity) element).onPrePut();
                hasProperties_QuantitySet.add((IfcDecElementQuantity) element);
            }
        }

    }

    @PostLoad(kinds = {"IfcDecProject"})
    public void prepareDataForClientIfcDecContext(PostLoadContext context) {
        super.prepareDataForClient(context);
        hasProperties = new ArrayList<IfcDecPropertySetDefinition>();
        for (IfcDecPropertySet set : hasProperties_PropertySet) {
            set.onPostLoad();
            hasProperties.add(set);
        }
        for (IfcDecElementQuantity set : hasProperties_QuantitySet) {
            set.onPostLoad();
            hasProperties.add(set);
        }
    }
}
