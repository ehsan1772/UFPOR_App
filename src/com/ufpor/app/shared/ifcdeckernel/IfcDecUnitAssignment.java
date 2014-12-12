package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcclient.IfcClientUnit;
import com.ufpor.app.shared.ifcclient.IfcClientUnitAssignment;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecSIUnit;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecUnit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Ehsan Barekati on 11/20/14.
 */
public class IfcDecUnitAssignment implements Serializable, IfcFileObject {
    private HashSet<IfcDecUnit> units;
    private int number;

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

    public static IfcDecUnitAssignment getInstance(IfcClientUnitAssignment unitsInContext) {
        IfcDecUnitAssignment result = new IfcDecUnitAssignment();
        for (IfcClientUnit unit : unitsInContext.getUnits()) {
            IfcDecUnit decUnit = IfcDecSIUnit.getInstance((com.ufpor.app.shared.ifcclient.IfcClientSIUnit) unit);
            result.addUnit(decUnit);
        }
        return result;
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
        ArrayList<IfcFileObject> results = new ArrayList<>();
        results.addAll(units);
        return results;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return null;
    }
}
