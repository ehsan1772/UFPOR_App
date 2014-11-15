package com.ufpor.app.shared.ifcclient.constraint;

import com.ufpor.app.shared.ifcclient.IfcClientLabel;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientMetric extends IfcClientConstraint {
    public IfcClientBenchmarkEnum getBenchMark() {
        return benchMark;
    }

    public void setBenchMark(IfcClientBenchmarkEnum benchMark) {
        this.benchMark = benchMark;
    }

    public IfcClientLabel getValueSource() {
        return valueSource;
    }

    public void setValueSource(IfcClientLabel valueSource) {
        this.valueSource = valueSource;
    }

    public IfcClientMetricValueSelect getDataValue() {
        return dataValue;
    }

    public void setDataValue(IfcClientMetricValueSelect dataValue) {
        this.dataValue = dataValue;
    }

    private IfcClientBenchmarkEnum benchMark;
    private IfcClientLabel valueSource;
    private IfcClientMetricValueSelect dataValue;
    public IfcClientMetric(IfcClientLabel name, IfcClientConstraintEnum constraintGrade) {
        super(name, constraintGrade);
    }

    protected IfcClientMetric() {
    }
}
