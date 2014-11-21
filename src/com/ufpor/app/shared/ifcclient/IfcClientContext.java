package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.select.IfcClientDefinitionSelect;
import com.ufpor.app.shared.ifcclient.select.IfcClientPropertySetDefinitionSelect;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public abstract class IfcClientContext extends IfcClientObjectDefinition {
    /*
    Set of property set definitions attached to this context. Those statically or dynamically defined properties contain alphanumeric information content that further defines the context.
     */
    protected ArrayList<IfcClientPropertySetDefinitionSelect> isDefinedBy;
    protected ArrayList<IfcClientDefinitionSelect> declares;
    protected IfcClientLabel objectType;
    protected IfcClientLabel longName;
    protected IfcClientLabel phase;
    protected IfcClientUnitAssignment unitsInContext;

    public IfcClientUnitAssignment getUnitsInContext() {
        if (unitsInContext == null) {
            unitsInContext = new IfcClientUnitAssignment();
        }
        return unitsInContext;
    }

    public void setUnitsInContext(IfcClientUnitAssignment unitsInContext) {
        if (unitsInContext == null) {
            unitsInContext = new IfcClientUnitAssignment();
        }
        this.unitsInContext = unitsInContext;
    }

    public ArrayList<IfcClientPropertySetDefinitionSelect> getIsDefinedBy() {
        return isDefinedBy;
    }

    public void setIsDefinedBy(ArrayList<IfcClientPropertySetDefinitionSelect> isDefinedBy) {
        this.isDefinedBy = isDefinedBy;
    }

    public ArrayList<IfcClientDefinitionSelect> getDeclares() {
        return declares;
    }

    public void setDeclares(ArrayList<IfcClientDefinitionSelect> declares) {
        this.declares = declares;
    }

    public IfcClientLabel getObjectType() {
        return objectType;
    }

    public void setObjectType(IfcClientLabel objectType) {
        this.objectType = objectType;
    }

    public IfcClientLabel getLongName() {
        return longName;
    }

    public void setLongName(IfcClientLabel longName) {
        this.longName = longName;
    }

    public IfcClientLabel getPhase() {
        return phase;
    }

    public void setPhase(IfcClientLabel phase) {
        this.phase = phase;
    }
}
