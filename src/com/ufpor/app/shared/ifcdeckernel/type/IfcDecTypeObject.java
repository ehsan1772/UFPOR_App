package com.ufpor.app.shared.ifcdeckernel.type;

import com.google.appengine.api.datastore.PostLoad;
import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PrePut;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObjectDefinition;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecElementQuantity;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySet;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinition;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinitionSelect;
import com.ufpor.app.shared.ifcdeckernel.relationship.IfcDecRelDefinesByType;

import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class IfcDecTypeObject extends IfcDecObjectDefinition {
    @Persistent(defaultFetchGroup = "true")
    protected IfcDecRelDefinesByType definedObjects;

    @Persistent
    protected String applicableOccurance;
    @NotPersistent
    protected List<IfcDecPropertySetDefinition> hasProperties;
    @Persistent
    protected ArrayList<IfcDecPropertySet> hasProperties_PropertySet;
    @Persistent
    protected ArrayList<IfcDecElementQuantity> hasProperties_QuantitySet;
    //this property represents IfcRelDefinesByType relationship between a type and an object
    //this is how we can connect a SpaceType to a space
    //TODO move it to the object
    private ArrayList<IfcDecObject> relatedObjects;

    public IfcDecTypeObject() {
    }

    public IfcDecTypeObject(String guid, User user) {
        super(guid, user);
    }

    public String getApplicableOccurance() {
        return applicableOccurance;
    }

    public IfcDecRelDefinesByType getDefinedObjects() {
        return definedObjects;
    }

    public void setDefinedObjects(IfcDecRelDefinesByType definedObjects) {
        this.definedObjects = definedObjects;
    }


    public void addDefinedObject(IfcDecObject definedObject) {
        if (definedObjects == null) {
            //Getting GUID
            String guid = GuidCompressor.getNewIfcGloballyUniqueId();
            definedObjects = new IfcDecRelDefinesByType(guid, user, this);
        }
        definedObjects.add(definedObject);
         //prepareDataForStoreIfcDecContext(null);
    }

    public void setApplicableOccurance(String applicableOccurance) {
        this.applicableOccurance = applicableOccurance;
    }

    public void addPropert(IfcDecPropertySetDefinition property) {
        if (hasProperties == null) {
            hasProperties = new ArrayList<IfcDecPropertySetDefinition>();
        }
        hasProperties.add(property);
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

    public ArrayList<IfcDecObject> getRelatedTypeObjects() {
        return relatedObjects;
    }

    public void setRelatedObjects(ArrayList<IfcDecObject> relatedObjects) {
        this.relatedObjects = relatedObjects;
    }

    @PrePut(kinds = {"IfcDecProject"})
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);

        if (hasProperties_PropertySet == null) {
            hasProperties_PropertySet = new ArrayList<IfcDecPropertySet>();
        }

        if (hasProperties_QuantitySet == null) {
            hasProperties_QuantitySet = new ArrayList<IfcDecElementQuantity>();
        }

        if (hasProperties != null) {
            for (IfcDecPropertySetDefinitionSelect element : hasProperties) {
                if (element instanceof IfcDecPropertySet && !hasProperties_PropertySet.contains(element)) {
                    ((IfcDecPropertySet) element).onPrePut();
                    hasProperties_PropertySet.add((IfcDecPropertySet) element);
                }

                if (element instanceof IfcDecElementQuantity && !hasProperties_QuantitySet.contains(element)) {
                    ((IfcDecElementQuantity) element).onPrePut();
                    hasProperties_QuantitySet.add((IfcDecElementQuantity) element);
                }
            }
        }

        if (definedObjects != null) {
            definedObjects.prepareDataForStoreIfcDecContext(null);
        }
    }

    @PostLoad(kinds = {"IfcDecProject"})
    public void prepareDataForClientIfcDecContext(PostLoadContext context) {
        super.prepareDataForClient(context);
        hasProperties = new ArrayList<IfcDecPropertySetDefinition>();
        for (IfcDecPropertySet set : hasProperties_PropertySet) {
            set.onPostLoad(this);
            hasProperties.add(set);
        }
        for (IfcDecElementQuantity set : hasProperties_QuantitySet) {
            set.onPostLoad(this);
            hasProperties.add(set);
        }

        if (definedObjects != null) {
            definedObjects.prepareDataForClient(null);
        }
    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        ArrayList<IfcFileObject> list = super.getRelatedObjects();
        if (definedObjects != null) {
            list.add(definedObjects);
        }
        list.addAll(hasProperties);
        return list;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return null;
    }
}
