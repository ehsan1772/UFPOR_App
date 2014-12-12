package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcclient.IfcClientDimensionalExponents;
import com.ufpor.app.shared.ifcclient.IfcClientNamedUnit;
import com.ufpor.app.shared.ifcclient.IfcClientSIUnit;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */

public class IfcDecSIUnit extends IfcDecNamedUnit implements Serializable {
    private IfcClientSIUnit.IfcSIPrefix prefix;

    private IfcClientSIUnit.IfcSIUnitName name;
    private int number;

    public IfcClientSIUnit.IfcSIPrefix getPrefix() {
        return prefix;
    }

    public IfcClientSIUnit.IfcSIUnitName getName() {
        return name;
    }

    public IfcDecSIUnit(IfcClientNamedUnit.IfcUnitEnum unitType, IfcDecDimensionalExponents dimensions, IfcClientSIUnit.IfcSIUnitName name) {
        super(unitType, dimensions);
        this.name = name;
    }

    public IfcDecSIUnit() {
    }

    @Override
    public String getIfcString() {

        String unitType = "*";
        String prefix =  "." + getUnitType().name();
        String name = getPrefix() == null ? "$" : "." + getPrefix().name();
        String dimensions =  "." + getName().name();

        String ifcUnit = String.format(Constants.IFCSIUNIT,
                unitType,
                prefix,
                name,
                dimensions);
        return ifcUnit;
    }


//    public enum IfcDecSIPrefix {
//        EXA,
//        PETA,
//        TERA,
//        GIGA,
//        MEGA,
//        KILO,
//        HECTO,
//        DECA,
//        DECI,
//        CENTI,
//        MILLI,
//        MICRO,
//        NANO,
//        PICO,
//        FEMTO,
//        ATTO;
//
//        public IfcDecSIPrefix getInstance(IfcClientSIUnit.IfcClientSIPrefix client) {
//            for(IfcDecSIPrefix val : IfcDecSIPrefix.values()) {
//                if (val.name().equals(client.name())) {
//                    return val;
//                }
//            }
//            return null;
//        }
//    }
//
//    public enum IfcDecSIUnitName {
//        AMPERE,
//        BECQUEREL,
//        CANDELA,
//        COULOMB,
//        CUBIC_METRE,
//        DEGREE_CELSIUS,
//        FARAD,
//        GRAM,
//        GRAY,
//        HENRY,
//        HERTZ,
//        JOULE,
//        KELVIN,
//        LUMEN,
//        LUX,
//        METRE,
//        MOLE,
//        NEWTON,
//        OHM,
//        PASCAL,
//        RADIAN,
//        SECOND,
//        SIEMENS,
//        SIEVERT,
//        SQUARE_METRE,
//        STERADIAN,
//        TESLA,
//        VOLT,
//        WATT,
//        WEBER;
//
//        public static IfcDecSIUnitName getInstance(IfcClientSIUnit.IfcClientSIUnitName client) {
//            for(IfcDecSIUnitName val : IfcDecSIUnitName.values()) {
//                if (val.name().equals(client.name())) {
//                    return val;
//                }
//            }
//            return null;
//        }
//    }

    public static IfcDecSIUnit getInstance(IfcClientSIUnit client) {
        IfcClientSIUnit.IfcSIPrefix prefix;
        IfcClientSIUnit.IfcSIUnitName unit;
        IfcDecDimensionalExponents ex = null;

        if (client.getDimensions() != null) {
            ex = IfcDecDimensionalExponents.getInstance(client.getDimensions());
        }
        return new IfcDecSIUnit(client.getUnitType(), ex, client.getName());
    }

    public static IfcClientSIUnit getClientInstance(IfcDecSIUnit server) {
        IfcClientDimensionalExponents ex = null;

        if (server.getDimensions() != null) {
            ex = IfcDecDimensionalExponents.getClientInstance(server.getDimensions());
        }
        return new IfcClientSIUnit(server.getUnitType(), ex,server.getName());
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        return null;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        String unitType = "*";
        String prefix =  "." + getUnitType().name();
        String name = getPrefix() == null ? "$" : "." + getPrefix().name();
        String dimensions =  "." + getName().name();

        String ifcUnit = String.format(Constants.IFCSIUNIT,
                unitType,
                prefix,
                name,
                dimensions);
        return ifcUnit;
    }
}
