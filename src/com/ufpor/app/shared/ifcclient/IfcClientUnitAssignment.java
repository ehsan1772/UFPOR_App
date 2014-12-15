package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Ehsan Barekati on 11/20/14.
 */
public class IfcClientUnitAssignment implements Serializable {
    private HashSet<IfcClientUnit> units;

    public IfcClientUnitAssignment() {
        units = new HashSet<IfcClientUnit>();
    }

    public HashSet<IfcClientUnit> getUnits() {
        return units;
    }

    public void setUnits(HashSet<IfcClientUnit> units) {
        this.units = units;
    }

    public void addUnit(IfcClientUnit unit) {
        for (IfcClientUnit u : units) {
            if (u instanceof IfcClientSIUnit &&
                    unit instanceof IfcClientSIUnit &&
                    ((IfcClientSIUnit) u).getName().equals(((IfcClientSIUnit) unit).getName())) {
                units.remove(u);

            }
        }
        units.add(unit);
    }
}
