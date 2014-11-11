package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public abstract class IfcClientNamedUnit implements IfcClientUnit, Serializable {

    protected IfcClientNamedUnit(IfcClientUnitEnum unitType, IfcClientDimensionalExponents dimensions) {
        this.unitType = unitType;
        this.dimensions = dimensions;
    }

    protected IfcClientNamedUnit() {
    }

    public enum IfcClientUnitEnum {
        ABSORBEDDOSEUNIT,
        AMOUNTOFSUBSTANCEUNIT,
        AREAUNIT,
        DOSEEQUIVALENTUNIT,
        ELECTRICCAPACITANCEUNIT,
        ELECTRICCHARGEUNIT,
        ELECTRICCONDUCTANCEUNIT,
        ELECTRICCURRENTUNIT,
        ELECTRICRESISTANCEUNIT,
        ELECTRICVOLTAGEUNIT,
        ENERGYUNIT,
        FORCEUNIT,
        FREQUENCYUNIT,
        ILLUMINANCEUNIT,
        INDUCTANCEUNIT,
        LENGTHUNIT,
        LUMINOUSFLUXUNIT,
        LUMINOUSINTENSITYUNIT,
        MAGNETICFLUXDENSITYUNIT,
        MAGNETICFLUXUNIT,
        MASSUNIT,
        PLANEANGLEUNIT,
        POWERUNIT,
        PRESSUREUNIT,
        RADIOACTIVITYUNIT,
        SOLIDANGLEUNIT,
        THERMODYNAMICTEMPERATUREUNIT,
        TIMEUNIT,
        VOLUMEUNIT,
        USERDEFINED
    }


    public IfcClientDimensionalExponents getDimensions() {
        return dimensions;
    }

    public void setDimensions(IfcClientDimensionalExponents dimensions) {
        this.dimensions = dimensions;
    }

    public IfcClientUnitEnum getUnitType() {
        return unitType;
    }

    public void setUnitType(IfcClientUnitEnum unitType) {
        this.unitType = unitType;
    }

    private IfcClientUnitEnum unitType;
    private IfcClientDimensionalExponents dimensions;


}
