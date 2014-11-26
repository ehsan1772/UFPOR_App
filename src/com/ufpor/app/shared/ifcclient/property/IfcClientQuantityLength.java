package com.ufpor.app.shared.ifcclient.property;

import com.ufpor.app.shared.ifcclient.IfcClientReal;
import com.ufpor.app.shared.ifcclient.constraint.IfcBenchmarkEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientObjective;
import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;

/**
 * Created by Ehsan Barekati on 11/22/14.
 */
public class IfcClientQuantityLength extends IfcClientPhysicalSimpleQuantity {
    private double length;
    private String formula;

    public IfcClientQuantityLength() {
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

    public IfcClientQuantityLength(Type name) {
        super(name.name());
        constraints = new IfcClientObjective();

    }

    public IfcClientQuantityLength(Type name, double length) {
        super(name.name());
        this.length = length;
        constraints = new IfcClientObjective();
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public enum Type {Height, FinishCeilingHeight, FinishFloorHeight, GrossPerimeter, NetPerimeter}
}
