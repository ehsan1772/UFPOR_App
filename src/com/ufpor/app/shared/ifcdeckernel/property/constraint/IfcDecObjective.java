package com.ufpor.app.shared.ifcdeckernel.property.constraint;

import com.ufpor.app.shared.ifcclient.constraint.IfcClientConstraint;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientObjective;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/25/14.
 */
public class IfcDecObjective extends IfcDecConstraint {
    private ArrayList<IfcDecConstraint> benchmarkValues = new ArrayList<IfcDecConstraint>();
    private IfcClientObjective.IfcLogicalOperatorEnum logicalAggregator;
    private IfcClientObjective.IfcObjectiveEnum objectiveQualifier;
    private String userDefinedQualifier;

    public ArrayList<IfcDecConstraint> getBenchmarkValues() {
        return benchmarkValues;
    }

    public void addConstraint(IfcDecConstraint constraint) {
        benchmarkValues.add(constraint);
    }

    public void setBenchmarkValues(ArrayList<IfcDecConstraint> benchmarkValues) {
        this.benchmarkValues = benchmarkValues;
    }

    public IfcClientObjective.IfcLogicalOperatorEnum getLogicalAggregator() {
        return logicalAggregator;
    }

    public void setLogicalAggregator(IfcClientObjective.IfcLogicalOperatorEnum logicalAggregator) {
        this.logicalAggregator = logicalAggregator;
    }

    public IfcClientObjective.IfcObjectiveEnum getObjectiveQualifier() {
        return objectiveQualifier;
    }

    public void setObjectiveQualifier(IfcClientObjective.IfcObjectiveEnum objectiveQualifier) {
        this.objectiveQualifier = objectiveQualifier;
    }

    public String getUserDefinedQualifier() {
        return userDefinedQualifier;
    }

    public void setUserDefinedQualifier(String userDefinedQualifier) {
        this.userDefinedQualifier = userDefinedQualifier;
    }

    @Override
    public String getIfcString() {
        return null;
    }

    @Override
    public String getIfcStringConstraintRelationship(String constraintNumber, String arrayObjects) {
        return null;
    }

    public static IfcDecObjective getInstance(IfcClientObjective client) {
        IfcDecObjective result = new IfcDecObjective();
        result.setDescription(client.getDescription());
        result.setName(client.getName());
        result.setConstraintGrade(client.getConstraintGrade());
        result.setLogicalAggregator(client.getLogicalAggregator());
        result.setObjectiveQualifier(client.getObjectiveQualifier());

        for(IfcClientConstraint clientConst : client.getBenchmarkValues()) {
            if (clientConst instanceof IfcClientMetric) {
                result.addConstraint(IfcDecMetric.getInstance((IfcClientMetric) clientConst));
            }
        }

        result.setUserDefinedQualifier(client.getUserDefinedQualifier());
        result.setConstraintSource(client.getConstraintSource());
        //result.setCreatingActor(client.getCreatingActor());
        return result;
    }
}
