package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.shared.ifcclient.*;
import com.ufpor.app.shared.ifcdeckernel.IfcDecIdentifier;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecObjective;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecPropertySingleValue extends IfcDecSimpleProperty {
    @Persistent(serialized = "true")
    private IfcDecObjective constraint;
    @NotPersistent
    private IfcDecValue nominalValue;
    @Persistent
    private double nominalValue_Real;
    @Persistent
    private int nominalValue_Integer;
    @Persistent
    private String nominalValue_Text;
    @NotPersistent
    private IfcDecUnit unit;
    @Persistent(serialized = "true")
    private IfcDecSIUnit unit_SIUnit;
    @Persistent
    private String type;

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
            unit = IfcDecSIUnit.getInstance((IfcClientSIUnit) client.getUnit());
        }

        IfcDecPropertySingleValue result = new IfcDecPropertySingleValue();
        result.setNominalValue(nominalValue);
        result.setUnit(unit);
        result.setName(client.getName());

        IfcDecObjective objective = IfcDecObjective.getInstance(client.getConstraint());
        result.setConstraint(objective);


        return result;
    }

    public IfcDecObjective getConstraint() {
        return constraint;
    }

    public void setConstraint(IfcDecObjective constraint) {
        this.constraint = constraint;
    }

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

    public void onPrePut() {
        if (nominalValue instanceof IfcDecInteger) {
            nominalValue_Integer = ((IfcDecInteger) nominalValue).getValue();
            type = "Integer";
        }

        if (nominalValue instanceof IfcDecReal) {
            nominalValue_Real = ((IfcDecReal) nominalValue).getValue();
            type = "Real";
        }

        if (nominalValue instanceof IfcDecText) {
            nominalValue_Text = ((IfcDecText) nominalValue).getValue();
            type = "Text";
        }

        if (unit instanceof IfcDecSIUnit) {
            unit_SIUnit = (IfcDecSIUnit) unit;
        }
    }

    public void onPostLoad() {
        if (nominalValue_Real != 0) {
            nominalValue = new IfcDecInteger(nominalValue_Integer);
        }

        if (nominalValue_Real != 0) {
            nominalValue = new IfcDecReal(nominalValue_Real);
        }

        if (nominalValue_Text != null) {
            nominalValue = new IfcDecText(nominalValue_Text);
        }

        if (unit_SIUnit != null) {
            unit = unit_SIUnit;
        }
        getConstraint().onPostLoad();
    }

    @Override
    public String getIfcString() {
        String name = getName().getValue();
        String description = (getDescription() == null || getDescription().getValue() == null || getDescription().getValue().isEmpty()) ? "*" : getDescription().getValue();
        String nominalValue = (getNominalValue() == null || getNominalValue().getIfcString() == null || getNominalValue().getIfcString().isEmpty()) ? "*" : getNominalValue().getIfcString();
        String unit = (getUnit() == null) ? "*" : Constants.getInstance().getUnit(this.getUnit(), null);

        return String.format(Constants.IFCPROPERTYSINGLEVALUE, "'" + name + "'", "'" + description + "'", nominalValue, unit);
    }
}
