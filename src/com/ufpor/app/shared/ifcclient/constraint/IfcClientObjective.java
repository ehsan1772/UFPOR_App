package com.ufpor.app.shared.ifcclient.constraint;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/25/14.
 */
public class IfcClientObjective extends IfcClientConstraint {
    private ArrayList<IfcClientConstraint> benchmarkValues;
    private IfcLogicalOperatorEnum logicalAggregator;
    private IfcObjectiveEnum objectiveQualifier;
    private String userDefinedQualifier;

    public IfcClientObjective() {
        benchmarkValues = new ArrayList<IfcClientConstraint>();
        logicalAggregator = IfcLogicalOperatorEnum.LOGICALAND;
    }

    public ArrayList<IfcClientConstraint> getBenchmarkValues() {
        return benchmarkValues;
    }

    public void setBenchmarkValues(ArrayList<IfcClientConstraint> benchmarkValues) {
        this.benchmarkValues = benchmarkValues;
    }

    public IfcLogicalOperatorEnum getLogicalAggregator() {
        return logicalAggregator;
    }

    public void setLogicalAggregator(IfcLogicalOperatorEnum logicalAggregator) {
        this.logicalAggregator = logicalAggregator;
    }

    public IfcObjectiveEnum getObjectiveQualifier() {
        return objectiveQualifier;
    }

    public void setObjectiveQualifier(IfcObjectiveEnum objectiveQualifier) {
        this.objectiveQualifier = objectiveQualifier;
    }

    public String getUserDefinedQualifier() {
        return userDefinedQualifier;
    }

    public void setUserDefinedQualifier(String userDefinedQualifier) {
        this.userDefinedQualifier = userDefinedQualifier;
    }

    public enum IfcLogicalOperatorEnum {
        LOGICALAND,
        LOGICALOR,
        LOGICALXOR,
        LOGICALNOTAND,
        LOGICALNOTOR
    }

    public enum IfcObjectiveEnum {
        CODECOMPLIANCE,
        CODEWAIVER,
        DESIGNINTENT,
        EXTERNAL,
        HEALTHANDSAFETY,
        MERGECONFLICT,
        MODELVIEW,
        PARAMETER,
        REQUIREMENT,
        SPECIFICATION,
        TRIGGERCONDITION,
        USERDEFINED,
        NOTDEFINED
    }
}
