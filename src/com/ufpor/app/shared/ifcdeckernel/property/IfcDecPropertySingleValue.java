package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.*;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecPropertySingleValue extends IfcDecSimpleProperty {
    private IfcDecValue nominalValue;
    private IfcDecUnit unit;

    public IfcDecValue getNominalValue() {
        return nominalValue;
    }

    public void setNominalValue(IfcDecValue nominalValue) {
        this.nominalValue = nominalValue;
    }

    public IfcDecUnit getUnit() {
        return unit;
    }

    public void setUnit(IfcDecUnit unit) {
        this.unit = unit;
    }

    public static IfcDecPropertySingleValue getInstance(IfcClientPropertySingleValue client) {
        IfcDecValue nominalValue = null;
        IfcDecUnit unit = null;

        if (client.getNominalValue() instanceof IfcClientInteger) {
            nominalValue = IfcDecInteger.getInstance((IfcClientInteger) client.getNominalValue());
        }

        if (client.getNominalValue() instanceof IfcClientText) {
            nominalValue = IfcDecText.getInstance((IfcClientText) client.getNominalValue());
        }

        if (client.getNominalValue() instanceof IfcClientReal) {
            nominalValue = IfcDecReal.getInstance((IfcClientReal) client.getNominalValue());
        }

        if (client.getUnit() instanceof IfcClientSIUnit) {
            unit = IfcDecSIUnit.getInstance((IfcClientSIUnit) client.getNominalValue());
        }

        IfcDecPropertySingleValue result = new IfcDecPropertySingleValue();
        result.setNominalValue(nominalValue);
        result.setUnit(unit);

        return result;
    }

}
