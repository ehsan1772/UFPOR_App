package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.users.User;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinition;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinitionSelect;

import javax.jdo.annotations.*;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecContext extends IfcDecObjectDefinition {
    //TODO figure out why I cannot use IfcDecPropertySetDefinitionSelect instead of IfcDecPropertySetDefinition
//    protected ArrayList<IfcDecPropertySetDefinitionSelect> isDefinedBy;
//    protected ArrayList<IfcDecDefinitionSelect> declares;
    @Persistent(serialized = "true")
    protected ArrayList<IfcDecPropertySetDefinition> isDefinedBy;

    @Persistent(serialized = "true")
    protected ArrayList<IfcDecObjectDefinition> declaresObject;
    @Persistent(serialized = "true")
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

    public IfcDecContext(String guid, User user) {
        super(guid, user);
    }


    protected IfcDecContext() {
    }

    public static int getInstance2(IfcClientProject proj) {
        int i = 0;
        return i;
    }

    public ArrayList<IfcDecPropertySetDefinitionSelect> getIsDefinedBy() {
        ArrayList<IfcDecPropertySetDefinitionSelect> result = new ArrayList<IfcDecPropertySetDefinitionSelect>();
        for (IfcDecPropertySetDefinition item : isDefinedBy) {
            result.add(item);
        }
        return result;
    }

    public void setIsDefinedBy(ArrayList<IfcDecPropertySetDefinitionSelect> isDefinedBy) {
        this.isDefinedBy = new ArrayList<IfcDecPropertySetDefinition>();
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
            this.isDefinedBy = new ArrayList<IfcDecPropertySetDefinition>();
        }

        this.isDefinedBy.add((IfcDecPropertySetDefinition) definedBy);

    }

}
