package com.ufpor.app.shared.ifcclient.property;

/**
 * Created by Ehsan Barekati on 11/22/14.
 */
public class IfcClientQuantityArea extends IfcClientPhysicalSimpleQuantity {
    private double areaValue;
    private String formula;

    public IfcClientQuantityArea() {
    }

    public IfcClientQuantityArea(Type name, double area) {
        super(name.name());
        areaValue = area;
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
