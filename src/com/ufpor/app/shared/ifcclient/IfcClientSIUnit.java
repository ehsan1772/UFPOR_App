package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientSIUnit extends IfcClientNamedUnit {
    private IfcClientSIPrefix prefix;
    private IfcClientSIUnitName name;

    public IfcClientSIUnit(IfcClientUnitEnum unitType, IfcClientDimensionalExponents dimensions, IfcClientSIUnitName name) {
        super(unitType, dimensions);
        this.name = name;
    }


    public enum IfcClientSIPrefix {
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

    public enum IfcClientSIUnitName {
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
