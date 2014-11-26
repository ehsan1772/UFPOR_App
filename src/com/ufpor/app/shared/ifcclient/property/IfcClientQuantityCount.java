package com.ufpor.app.shared.ifcclient.property;

/**
 * Created by Ehsan Barekati on 11/22/14.
 */
public class IfcClientQuantityCount extends IfcClientPhysicalSimpleQuantity {
    private int countMeasure;
    private String formula;

    public IfcClientQuantityCount() {
    }

    @Override
    public void setMaxValue(Object value) {

    }

    @Override
    public void setMinValue(Object value) {

    }

    public IfcClientQuantityCount(Type name, int count) {
        super(name.name());
        countMeasure = count;
    }

    public int getCountMeasure() {
        return countMeasure;
    }

    public void setCountMeasure(int countMeasure) {
        this.countMeasure = countMeasure;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public enum Type {Count}
}
