package com.ufpor.app.shared.ifcdeckernel.property.constraint;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcclient.IfcClientObject;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientConstraint;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientObjective;
import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;
import com.ufpor.app.shared.ifcdeckernel.IfcDecResourceConstraintRelationship;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPhysicalSimpleQuantity;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Ehsan Barekati on 11/25/14.
 */
public class IfcDecObjective extends IfcDecConstraint {
    //  private ArrayList<IfcDecConstraint> benchmarkValues = new ArrayList<IfcDecConstraint>();
    private IfcDecResourceConstraintRelationship<IfcFileObject> relatedResourceObjects;
  //  private ArrayList<IfcDecConstraint> benchmarkValues = new ArrayList<IfcDecConstraint>();
    private ArrayList<IfcDecConstraint> benchmarkValues = new ArrayList<>();
    private IfcClientObjective.IfcLogicalOperatorEnum logicalAggregator;
    private IfcClientObjective.IfcObjectiveEnum objectiveQualifier;
    private String userDefinedQualifier;

    protected IfcDecObjective() {
    }

    public IfcDecObjective(String name, IfcConstraintEnum constraintGrade, IfcClientObjective.IfcObjectiveEnum objectiveQualifier) {
        super(name, constraintGrade);
        this.objectiveQualifier = objectiveQualifier;
        relatedResourceObjects = new IfcDecResourceConstraintRelationship<>((IfcFileObject) this);
    }

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

    /**
     *
     ENTITY IfcObjective
     ENTITY IfcConstraint
     Name	:	IfcLabel;
     Description	:	OPTIONAL IfcText;
     ConstraintGrade	:	IfcConstraintEnum;
     ConstraintSource	:	OPTIONAL IfcLabel;
     CreatingActor	:	OPTIONAL IfcActorSelect;
     CreationTime	:	OPTIONAL IfcDateTime;
     UserDefinedGrade	:	OPTIONAL IfcLabel;
     INVERSE
     HasExternalReferences	:	SET OF IfcExternalReferenceRelationship FOR RelatedResourceObjects;
     PropertiesForConstraint	:	SET OF IfcResourceConstraintRelationship FOR RelatingConstraint;
     ENTITY IfcObjective
     BenchmarkValues	:	OPTIONAL LIST [1:?] OF IfcConstraint;
     LogicalAggregator	:	OPTIONAL IfcLogicalOperatorEnum;
     ObjectiveQualifier	:	IfcObjectiveEnum;
     UserDefinedQualifier	:	OPTIONAL IfcLabel;
     END_ENTITY;
     * @param stringList
     * @return
     */
    public String getIfcString(String benchmark) {
        String name = getName();
        String description = (getDescription() != null && !getDescription().isEmpty()) ? getDescription() : "*";
        String grade = getConstraintGrade().name();
        String source = (getConstraintSource() != null && !getConstraintSource().isEmpty()) ? getConstraintSource() : "*";
        String actor = "*";
        String time = "*";
        String userGrade = (getUserDefinedGrade() != null && !getUserDefinedGrade().isEmpty()) ? getUserDefinedGrade() : "*";
        String benchmarkValues = benchmark;
        String logicalAggregation = getLogicalAggregator().name();
        String objectiveQualifier = getObjectiveQualifier().name();
        String userQualifier = (getUserDefinedQualifier() != null && !getUserDefinedQualifier().isEmpty()) ? getUserDefinedQualifier() : "*";

        return String.format(Constants.IFCOBJECTIVE, name,description, grade, source, actor, time, userGrade, benchmarkValues, logicalAggregation, objectiveQualifier, userQualifier);
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
        IfcDecObjective result = new IfcDecObjective(client.getName(), IfcConstraintEnum.HARD, IfcClientObjective.IfcObjectiveEnum.REQUIREMENT);
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

    public void onPostLoad() {
        getBenchmarkValues();
        getUserDefinedQualifier();
        getName();
        getDescription();
        getUserDefinedGrade();
    }

    public static IfcClientObjective getClientInstance(IfcDecObjective server) {
        IfcClientObjective result = new IfcClientObjective();

        result.setConstraintGrade(server.getConstraintGrade());
        result.setDescription(server.getDescription());
        result.setName(server.getName());
        result.setConstraintSource(server.getConstraintSource());
        result.setLogicalAggregator(server.getLogicalAggregator());
        result.setObjectiveQualifier(server.getObjectiveQualifier());
        result.setUserDefinedQualifier(server.getUserDefinedQualifier());

        HashSet<IfcClientConstraint> benchMarks = new HashSet<IfcClientConstraint>();
        for (IfcDecConstraint constraint : server.getBenchmarkValues()) {
            if (constraint instanceof IfcDecMetric) {
                benchMarks.add(IfcDecMetric.getClientInstance((IfcDecMetric) constraint));
            }
        }

        result.setBenchmarkValues(benchMarks);

        return result;
    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        ArrayList<IfcFileObject> result = new ArrayList<>();
        result.add(relatedResourceObjects);
        result.addAll(benchmarkValues);
        return result;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        String name = getName();
        String description = (getDescription() != null && !getDescription().isEmpty()) ? getDescription() : "*";
        String grade = getConstraintGrade().name();
        String source = (getConstraintSource() != null && !getConstraintSource().isEmpty()) ? getConstraintSource() : "*";
        String actor = "*";
        String time = "*";
        String userGrade = (getUserDefinedGrade() != null && !getUserDefinedGrade().isEmpty()) ? getUserDefinedGrade() : "*";
        String benchmarkValues = fileManager.getNumberString(new ArrayList<IfcFileObject>(getBenchmarkValues()));
        String logicalAggregation = getLogicalAggregator().name();
        String objectiveQualifier = getObjectiveQualifier().name();
        String userQualifier = (getUserDefinedQualifier() != null && !getUserDefinedQualifier().isEmpty()) ? getUserDefinedQualifier() : "*";

        return String.format(Constants.IFCOBJECTIVE, name,description, grade, source, actor, time, userGrade, benchmarkValues, logicalAggregation, objectiveQualifier, userQualifier);
    }

    public void addResourceObject(IfcFileObject fileObject) {
        relatedResourceObjects.add(fileObject);
        ArrayList result =  relatedResourceObjects.getList();
        int size = result.size();
        return;
    }
}
