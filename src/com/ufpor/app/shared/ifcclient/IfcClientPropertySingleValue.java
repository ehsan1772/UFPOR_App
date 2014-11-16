package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientPropertySingleValue extends IfcClientSimpleProperty {
    private IfcClientValue nominalValue;
    private IfcClientUnit unit;

    public IfcClientPropertySingleValue(IfcClientUnit unit) {
        this.unit = unit;
    }

    public IfcClientPropertySingleValue() {
    }

    public IfcClientValue getNominalValue() {

        return nominalValue;
    }

    public void setNominalValue(IfcClientValue nominalValue) {
        this.nominalValue = nominalValue;
    }

    public IfcClientUnit getUnit() {
        return unit;
    }

    public void setUnit(IfcClientUnit unit) {
        this.unit = unit;
    }
}
