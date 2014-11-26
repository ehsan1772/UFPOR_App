package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcdeckernel.property.*;

import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecContext extends IfcDecObjectDefinition {
    @NotPersistent
    protected ArrayList<IfcDecPropertySetDefinitionSelect> isDefinedBy;

    @Persistent
    protected ArrayList<IfcDecPropertySet> isDefinedBy_PropertySet;

    @Persistent
    protected ArrayList<IfcDecElementQuantity> isDefinedBy_QuantitySet;
    @NotPersistent
    protected ArrayList<IfcDecObjectDefinition> declaresObject;
    @Persistent
    protected ArrayList<IfcDecPropertyDefinition> declaresProperty;
    @NotPersistent
    protected IfcDecLabel objectType;
    @NotPersistent
    protected IfcDecLabel longName;
    @NotPersistent
    protected IfcDecLabel phase;
    @Persistent
    protected String phaseString;
    @Persistent
    protected String longNameString;
    @Persistent
    protected String objectTypeString;
    @Persistent(serialized = "true")
    protected IfcDecUnitAssignment unitsInContext;

    @Persistent
    private Set<com.google.appengine.api.datastore.Key> spaceTypes;

    public void addSpaceType(Key key) {
        if (spaceTypes == null) {
            spaceTypes = new HashSet<Key>();
        }
        spaceTypes.add(key);
    }

    public void removeAllTheSpaces() {
        spaceTypes.removeAll(spaceTypes);
    }

    public IfcDecContext(String guid, User user) {
        super(guid, user);
    }

    protected IfcDecContext() {
    }

    public static int getInstance2(IfcClientProject proj) {
        int i = 0;
        return i;
    }

    public Set<Key> getSpaceTypes() {
        return spaceTypes;
    }

    public void setSpaceTypes(Set<Key> spaceTypes) {
        this.spaceTypes = spaceTypes;
    }

    public IfcDecUnitAssignment getUnitsInContext() {
        return unitsInContext;
    }

    public void setUnitsInContext(IfcDecUnitAssignment unitsInContext) {
        this.unitsInContext = unitsInContext;
    }

    public ArrayList<IfcDecPropertySetDefinitionSelect> getIsDefinedBy() {

        return isDefinedBy;
    }

    public void setIsDefinedBy(ArrayList<IfcDecPropertySetDefinitionSelect> isDefinedBy) {
        this.isDefinedBy = new ArrayList<IfcDecPropertySetDefinitionSelect>();
        for (IfcDecPropertySetDefinitionSelect item : isDefinedBy) {
            if (item instanceof IfcDecPropertySetDefinition) {
                this.isDefinedBy.add((IfcDecPropertySetDefinition) item);
            }
        }
    }

    public ArrayList<IfcDecDefinitionSelect> getDeclares() {
        ArrayList<IfcDecDefinitionSelect> declares = new ArrayList<IfcDecDefinitionSelect>();
        declares.addAll(declaresObject);
        for (IfcDecPropertyDefinition prop : declaresProperty) {
            declares.add((IfcDecDefinitionSelect) prop);
        }
        //TODO why this doesn't work?
        //declares.addAll(declaresProperty);
        return declares;
    }

    public void setDeclares(ArrayList<IfcDecDefinitionSelect> declares) {
        this.declaresObject = new ArrayList<IfcDecObjectDefinition>();
        this.declaresProperty = new ArrayList<IfcDecPropertyDefinition>();
        for (IfcDecDefinitionSelect declare : declares) {
            addDeclare(declare);
        }
    }

    public void addDeclare(IfcDecDefinitionSelect declare) {
        if (declare instanceof IfcDecObjectDefinition) {
            declaresObject.add((IfcDecObjectDefinition) declare);
        }

        if (declare instanceof IfcDecPropertyDefinition) {
            declaresProperty.add((IfcDecPropertyDefinition) declare);
        }
    }

    public IfcDecLabel getObjectType() {
        return objectType;
    }

    public void setObjectType(IfcDecLabel objectType) {
        this.objectType = objectType;
        objectTypeString = objectType.getValue();
    }

    public IfcDecLabel getLongName() {
        return longName;
    }

    public void setLongName(IfcDecLabel longName) {
        this.longName = longName;
        longNameString = longName.getValue();
    }

    public IfcDecLabel getPhase() {
        return phase;
    }

    public void setPhase(IfcDecLabel phase) {
        this.phase = phase;
        phaseString = phase.getValue();
    }

    public void addDefinedBy(IfcDecPropertySetDefinitionSelect definedBy) {
        if (this.isDefinedBy == null) {
            this.isDefinedBy = new ArrayList<IfcDecPropertySetDefinitionSelect>();
        }

        this.isDefinedBy.add((IfcDecPropertySetDefinition) definedBy);

    }

    @PrePut(kinds = {"IfcDecProject"})
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);
        isDefinedBy_PropertySet = new ArrayList<IfcDecPropertySet>();
        isDefinedBy_QuantitySet = new ArrayList<IfcDecElementQuantity>();
        for (IfcDecPropertySetDefinitionSelect element : isDefinedBy) {
            if (element instanceof IfcDecPropertySet) {
                ((IfcDecPropertySet) element).onPrePut();
                isDefinedBy_PropertySet.add((IfcDecPropertySet) element);
            }

            if (element instanceof IfcDecElementQuantity) {
                ((IfcDecElementQuantity) element).onPrePut();
                isDefinedBy_QuantitySet.add((IfcDecElementQuantity) element);
            }
        }

    }

    @PostLoad(kinds = {"IfcDecProject"})
    public void prepareDataForClientIfcDecContext(PostLoadContext context) {
        super.prepareDataForClient(context);
        isDefinedBy = new ArrayList<IfcDecPropertySetDefinitionSelect>();
        for (IfcDecPropertySet set : isDefinedBy_PropertySet) {
            set.onPostLoad();
            isDefinedBy.add(set);
        }
        for (IfcDecElementQuantity set : isDefinedBy_QuantitySet) {
            set.onPostLoad();
            isDefinedBy.add(set);
        }
        objectType = new IfcDecLabel(objectTypeString);
        longName = new IfcDecLabel(longNameString);
        phase = new IfcDecLabel(phaseString);
    }

}
