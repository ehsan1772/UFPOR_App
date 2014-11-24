package com.ufpor.app.shared.ifcclient.property;

/**
 * Created by Ehsan Barekati on 11/22/14.
 */
public class IfcClientQuantityLength extends IfcClientPhysicalSimpleQuantity {
    private double length;
    private String formula;

    public IfcClientQuantityLength() {
    }

    public IfcClientQuantityLength(Type name, double length) {
        super(name.name());
        this.length = length;
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
