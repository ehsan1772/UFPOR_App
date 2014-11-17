package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.shared.ifcdeckernel.property.IfcDecDefinitionSelect;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinitionSelect;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecContext extends IfcDecObjectDefinition {
    @Persistent(serialized="true")
    protected ArrayList<IfcDecPropertySetDefinitionSelect> isDefinedBy;
    @Persistent
    protected ArrayList<IfcDecDefinitionSelect> declares;
    @Persistent
    protected IfcDecLabel objectType;
    @Persistent
    protected IfcDecLabel longName;
    @Persistent
    protected IfcDecLabel phase;

    public ArrayList<IfcDecPropertySetDefinitionSelect> getIsDefinedBy() {
        return isDefinedBy;
    }

    public void setIsDefinedBy(ArrayList<IfcDecPropertySetDefinitionSelect> isDefinedBy) {
        this.isDefinedBy = isDefinedBy;
    }

    public ArrayList<IfcDecDefinitionSelect> getDeclares() {
        return declares;
    }

    public void setDeclares(ArrayList<IfcDecDefinitionSelect> declares) {
        this.declares = declares;
    }

    public IfcDecLabel getObjectType() {
        return objectType;
    }

    public void setObjectType(IfcDecLabel objectType) {
        this.objectType = objectType;
    }

    public IfcDecLabel getLongName() {
        return longName;
    }

    public void setLongName(IfcDecLabel longName) {
        this.longName = longName;
    }

    public IfcDecLabel getPhase() {
        return phase;
    }

    public void setPhase(IfcDecLabel phase) {
        this.phase = phase;
    }

    public void addDefinedBy(IfcDecPropertySetDefinitionSelect definedBy) {
        if (isDefinedBy == null) {
            isDefinedBy = new ArrayList<IfcDecPropertySetDefinitionSelect>();
        }
        isDefinedBy.add(definedBy);
    }

}
