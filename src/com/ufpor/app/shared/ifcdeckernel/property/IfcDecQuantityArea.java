package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityArea;

/**
 * Created by Ehsan Barekati on 11/23/14.
 */
public class IfcDecQuantityArea extends IfcDecPhysicalSimpleQuantity {
    private double areaValue;
    private String formula;


    public IfcDecQuantityArea(Type name, double area) {
        super(name.name());
        areaValue = area;
    }

    public IfcDecQuantityArea() {
    }

    @Override
    public String getIfcString() {
        String name = getName();
        String description = (getDescription() != null && !getDescription().isEmpty()) ? getDescription() : "*";
        String unit = "*";
        String areaValue = String.valueOf(getAreaValue());
        String formula = (getFormula() != null && !getFormula().isEmpty()) ? getFormula() : "*";
        return String.format(Constants.IFCQUANTITYAREA, name, description, unit, areaValue, formula);
    }

    public static IfcDecQuantityArea getInstance(IfcClientQuantityArea client) {
        Type type = null;
        for (Type t : Type.values()) {
            if (client.getName().equals(t.name())) {
                type = t;
                break;
            }
        }

        IfcDecQuantityArea result = new IfcDecQuantityArea(type, client.getAreaValue());
        result.setFormula(client.getFormula());
        result.setDescription(client.getDescription());
        if (client.getUnit() != null) {
            result.setUnit(IfcDecSIUnit.getInstance((com.ufpor.app.shared.ifcclient.IfcClientSIUnit) client.getUnit()));
        }

        return result;
    }

    public double getAreaValue() {
        return areaValue;
    }

    public void setAreaValue(double areaValue) {
        this.areaValue = areaValue;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }


    public enum Type {GrossFloorArea, NetFloorArea, GrossWallArea, NetWallArea, GrossCeilingArea, NetCeilingArea}
}
