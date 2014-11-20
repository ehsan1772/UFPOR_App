package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.shared.ifcdeckernel.property.IfcDecUnit;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Ehsan Barekati on 11/20/14.
 */
public class IfcDecUnitAssignment implements Serializable {
    private HashSet<IfcDecUnit> units;

    public IfcDecUnitAssignment() {
        units = new HashSet<IfcDecUnit>();
    }

    public HashSet<IfcDecUnit> getUnits() {
        return units;
    }

    public void setUnits(HashSet<IfcDecUnit> units) {
        this.units = units;
    }

    public void addUnit(IfcDecUnit unit) {
        units.add(unit);
    }

}
