package com.ufpor.app.shared.ifcclient.constraint;

import java.util.HashSet;

/**
 * Created by Ehsan Barekati on 11/25/14.
 */
public class IfcClientObjective extends IfcClientConstraint {
    private HashSet<IfcClientConstraint> benchmarkValues;
    private IfcLogicalOperatorEnum logicalAggregator;
    private IfcObjectiveEnum objectiveQualifier;
    private String userDefinedQualifier;

    public IfcClientObjective() {
        benchmarkValues = new HashSet<IfcClientConstraint>();
        logicalAggregator = IfcLogicalOperatorEnum.LOGICALAND;
        objectiveQualifier = IfcObjectiveEnum.REQUIREMENT;
        setConstraintGrade(IfcConstraintEnum.HARD);
    }

    public HashSet<IfcClientConstraint> getBenchmarkValues() {
        return benchmarkValues;
    }

    public void setBenchmarkValues(HashSet<IfcClientConstraint> benchmarkValues) {
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
