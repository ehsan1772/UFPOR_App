package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public abstract class IfcClientNamedUnit implements IfcClientUnit, Serializable {

    private IfcUnitEnum unitType;
    private IfcClientDimensionalExponents dimensions;

    protected IfcClientNamedUnit(IfcUnitEnum unitType, IfcClientDimensionalExponents dimensions) {
        this.unitType = unitType;
        this.dimensions = dimensions;
    }


    protected IfcClientNamedUnit() {
    }

    public IfcClientDimensionalExponents getDimensions() {
        return dimensions;
    }

    public void setDimensions(IfcClientDimensionalExponents dimensions) {
        this.dimensions = dimensions;
    }

    public IfcUnitEnum getUnitType() {
        return unitType;
    }

    public void setUnitType(IfcUnitEnum unitType) {
        this.unitType = unitType;
    }

    public enum IfcUnitEnum {
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


}
