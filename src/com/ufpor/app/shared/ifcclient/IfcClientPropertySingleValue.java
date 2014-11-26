package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientObjective;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientPropertySingleValue extends IfcClientSimpleProperty {
    private IfcClientValue nominalValue;
    private IfcClientUnit unit;


    //constraints
    private IfcClientObjective constraint;
    private IfcClientMetric maxConstraint;
    private IfcClientMetric minConstraint;
    private IfcClientMetric booleanConstraint;

    public IfcClientPropertySingleValue(IfcClientUnit unit) {
        this.unit = unit;
        initializeConstraint();
    }

    public IfcClientPropertySingleValue() {
        initializeConstraint();
    }

    public IfcClientPropertySingleValue(String name) {
        initializeConstraint();
        setName(name);
    }

    public IfcClientObjective getConstraint() {
        return constraint;
    }

    public void setConstraint(IfcClientObjective constraint) {
        this.constraint = constraint;
    }

    public IfcClientMetric getBooleanConstraint() {
        return booleanConstraint;
    }

    public void setBooleanConstraint(IfcClientMetric booleanConstraint) {
        this.booleanConstraint = booleanConstraint;
        if (!constraint.getBenchmarkValues().contains(booleanConstraint)) {
            constraint.getBenchmarkValues().add(booleanConstraint);
        }

    }

    public IfcClientMetric getMinConstraint() {
        return minConstraint;
    }

    public void setMinConstraint(IfcClientMetric minConstraint) {
        this.minConstraint = minConstraint;
    }

    public IfcClientMetric getMaxConstraint() {
        return maxConstraint;
    }

    public void setMaxConstraint(IfcClientMetric maxConstraint) {
        this.maxConstraint = maxConstraint;
    }

    private void initializeConstraint() {
        constraint = new IfcClientObjective();
    }

    public IfcClientValue getNominalValue() {

        return nominalValue;
    }

    public void setNominalValue(IfcClientValue nominalValue) {
        this.nominalValue = nominalValue;
    }

    public IfcClientUnit getUnit() {
        return unit;
    }

    public void setUnit(IfcClientUnit unit) {
        this.unit = unit;
    }


}
