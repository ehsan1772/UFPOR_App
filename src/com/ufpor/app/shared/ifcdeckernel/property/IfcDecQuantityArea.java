package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcclient.constraint.IfcBenchmarkEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityArea;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecMetric;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecObjective;

import java.util.ArrayList;

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

    public static IfcClientQuantityArea getClientInstance(IfcDecQuantityArea server) {
        IfcClientQuantityArea result = new IfcClientQuantityArea();

        result.setAreaValue(server.getAreaValue());
        result.setDescription(server.getDescription());
        if (server.getUnit() instanceof IfcDecSIUnit) {
            result.setUnit(IfcDecSIUnit.getClientInstance((IfcDecSIUnit) server.getUnit()));
        }

        result.setConstraints(IfcDecObjective.getClientInstance(server.getConstraints()));

        return result;


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

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        ArrayList<IfcFileObject> result = new ArrayList<IfcFileObject>();
        result.add(constraints);
        return result;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return getIfcString();
    }


    public enum Type {GrossFloorArea, NetFloorArea, GrossWallArea, NetWallArea, GrossCeilingArea, NetCeilingArea}
}
