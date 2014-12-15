package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientSIUnit extends IfcClientNamedUnit {
    private IfcSIPrefix prefix;
    private IfcSIUnitName name;

    public IfcClientSIUnit(IfcUnitEnum unitType, IfcClientDimensionalExponents dimensions, IfcSIUnitName name) {
        super(unitType, dimensions);
        this.name = name;
    }

    public IfcClientSIUnit() {
    }

    public IfcSIPrefix getPrefix() {
        return prefix;
    }

    public void setPrefix(IfcSIPrefix prefix) {
        this.prefix = prefix;
    }

    public IfcSIUnitName getName() {
        return name;
    }

    public void setName(IfcSIUnitName name) {
        this.name = name;
    }


    public enum IfcSIPrefix {
        EXA,
        PETA,
        TERA,
        GIGA,
        MEGA,
        KILO,
        HECTO,
        DECA,
        DECI,
        CENTI,
        MILLI,
        MICRO,
        NANO,
        PICO,
        FEMTO,
        ATTO
    }

    public enum IfcSIUnitName {
        AMPERE,
        BECQUEREL,
        CANDELA,
        COULOMB,
        CUBIC_METRE,
        DEGREE_CELSIUS,
        FARAD,
        GRAM,
        GRAY,
        HENRY,
        HERTZ,
        JOULE,
        KELVIN,
        LUMEN,
        LUX,
        METRE,
        MOLE,
        NEWTON,
        OHM,
        PASCAL,
        RADIAN,
        SECOND,
        SIEMENS,
        SIEVERT,
        SQUARE_METRE,
        STERADIAN,
        TESLA,
        VOLT,
        WATT,
        WEBER

    }
}
