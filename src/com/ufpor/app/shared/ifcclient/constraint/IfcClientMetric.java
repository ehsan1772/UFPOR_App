package com.ufpor.app.shared.ifcclient.constraint;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientMetric extends IfcClientConstraint {
    private IfcBenchmarkEnum benchMark;
    private String valueSource;
    private IfcClientMetricValueSelect dataValue;

    public IfcClientMetric(String name, IfcConstraintEnum constraintGrade) {
        super(name, constraintGrade);
    }

    protected IfcClientMetric() {
    }

    public IfcBenchmarkEnum getBenchMark() {
        return benchMark;
    }

    public void setBenchMark(IfcBenchmarkEnum benchMark) {
        this.benchMark = benchMark;
    }

    public String getValueSource() {
        return valueSource;
    }

    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    public IfcClientMetricValueSelect getDataValue() {
        return dataValue;
    }

    public void setDataValue(IfcClientMetricValueSelect dataValue) {
        this.dataValue = dataValue;
    }
}
