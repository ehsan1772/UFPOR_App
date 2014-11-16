package com.ufpor.app.shared.ifcdeckernel.property;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
public class IfcDecSIUnit extends IfcDecNamedUnit{
    private IfcDecSIPrefix prefix;
    private IfcDecSIUnitName name;

    public IfcDecSIUnit(IfcDecUnitEnum unitType, IfcDecDimensionalExponents dimensions, IfcDecSIUnitName name) {
        super(unitType, dimensions);
        this.name = name;
    }

    public enum IfcDecSIPrefix {
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

    public enum IfcDecSIUnitName {
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
