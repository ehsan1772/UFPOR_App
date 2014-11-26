package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.shared.ifcclient.IfcClientReal;
import com.ufpor.app.shared.ifcclient.constraint.IfcBenchmarkEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityArea;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecMetric;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecObjective;

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
    public void setMaxValue(Object value) {
        IfcDecMetric maxMetric = new IfcDecMetric("MAX_VALUE", IfcConstraintEnum.HARD);
        maxMetric.setBenchMark(IfcBenchmarkEnum.LESSTHANOREQUALTO);
        maxMetric.setDataValue(new IfcDecReal((Double) value));
        constraints.getBenchmarkValues().add(maxMetric);
    }

    @Override
    public void setMinValue(Object value) {
        IfcDecMetric minMetric = new IfcDecMetric("MIN_VALUE", IfcConstraintEnum.HARD);
        minMetric.setBenchMark(IfcBenchmarkEnum.GREATERTHANOREQUALTO);
        minMetric.setDataValue(new IfcDecReal((Double) value));
        constraints.getBenchmarkValues().add(minMetric);
    }

    @Override
    public void onPostLoad() {
        getConstraints().onPostLoad();
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

        result.setConstraints(IfcDecObjective.getInstance(client.getConstraints()));

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
