package com.ufpor.app.shared.ifcdeckernel.property.constraint;

import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;
import com.ufpor.app.shared.ifcdeckernel.actor.IfcDecActorSelect;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDectDateTime;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/18/14.
 */
public abstract class IfcDecConstraint implements Serializable {
    private String name;
    private String description;
    private IfcConstraintEnum constraintGrade;
    private String constraintSource;
    //TODO figure out why it fails if this field is not transient
    private transient IfcDecActorSelect creatingActor;
    private IfcDectDateTime creatingTime;
    private String userDefinedGrade;

    public IfcDecConstraint(String name, IfcConstraintEnum constraintGrade) {
        this.name = name;
        this.constraintGrade = constraintGrade;
    }

    protected IfcDecConstraint() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConstraintSource() {
        return constraintSource;
    }

    public void setConstraintSource(String constraintSource) {
        this.constraintSource = constraintSource;
    }

    public String getUserDefinedGrade() {
        return userDefinedGrade;
    }

    public void setUserDefinedGrade(String userDefinedGrade) {
        this.userDefinedGrade = userDefinedGrade;
    }


    public IfcConstraintEnum getConstraintGrade() {
        return constraintGrade;
    }

    public void setConstraintGrade(IfcConstraintEnum constraintGrade) {
        this.constraintGrade = constraintGrade;
    }

    public IfcDecActorSelect getCreatingActor() {
        return creatingActor;
    }

    public void setCreatingActor(IfcDecActorSelect creatingActor) {
        this.creatingActor = creatingActor;
    }

    public IfcDectDateTime getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(IfcDectDateTime creatingTime) {
        this.creatingTime = creatingTime;
    }

    public abstract String getIfcString();
    public abstract String getIfcStringConstraintRelationship(String constraintNumber, String arrayObjects);

}
