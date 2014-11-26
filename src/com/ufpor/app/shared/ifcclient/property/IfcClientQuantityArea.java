package com.ufpor.app.shared.ifcclient.property;

import com.ufpor.app.shared.ifcclient.IfcClientReal;
import com.ufpor.app.shared.ifcclient.constraint.IfcBenchmarkEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientObjective;
import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;

/**
 * Created by Ehsan Barekati on 11/22/14.
 */
public class IfcClientQuantityArea extends IfcClientPhysicalSimpleQuantity {
    private double areaValue;
    private String formula;

    public IfcClientQuantityArea() {
        constraints = new IfcClientObjective();
    }

    @Override
    public void setMaxValue(Object value) {
        IfcClientMetric maxMetric = new IfcClientMetric("MAX_VALUE", IfcConstraintEnum.HARD);
        maxMetric.setBenchMark(IfcBenchmarkEnum.LESSTHANOREQUALTO);
        maxMetric.setDataValue(new IfcClientReal((Double) value));
        constraints.getBenchmarkValues().add(maxMetric);
    }

    @Override
    public void setMinValue(Object value) {
        IfcClientMetric maxMetric = new IfcClientMetric("MIN_VALUE", IfcConstraintEnum.HARD);
        maxMetric.setBenchMark(IfcBenchmarkEnum.GREATERTHANOREQUALTO);
        maxMetric.setDataValue(new IfcClientReal((Double) value));
        constraints.getBenchmarkValues().add(maxMetric);
    }

    public IfcClientQuantityArea(Type name) {
        super(name.name());
        constraints = new IfcClientObjective();
    }

    public IfcClientQuantityArea(Type name, double area) {
        super(name.name());
        areaValue = area;
        constraints = new IfcClientObjective();
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
