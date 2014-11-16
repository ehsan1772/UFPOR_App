package com.ufpor.app.shared.ifcdeckernel.property;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
public abstract class IfcDecNamedUnit implements IfcDecUnit {
    private IfcDecUnitEnum unitType;
    private IfcDecDimensionalExponents dimensions;

    public IfcDecNamedUnit(IfcDecUnitEnum unitType, IfcDecDimensionalExponents dimensions) {
        this.unitType = unitType;
        this.dimensions = dimensions;
    }

    public enum IfcDecUnitEnum {
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
