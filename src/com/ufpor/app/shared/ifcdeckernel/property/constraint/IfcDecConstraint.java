package com.ufpor.app.shared.ifcdeckernel.property.constraint;

import com.ufpor.app.shared.ifcdeckernel.IfcDecLabel;
import com.ufpor.app.shared.ifcdeckernel.actor.IfcDecActorSelect;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecText;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDectDateTime;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/18/14.
 */
public class IfcDecConstraint implements Serializable {
    private IfcDecLabel name;
    private IfcDecText description;
    private IfcDecConstraintEnum constraintGrade;
    private IfcDecLabel constraintSource;
    //TODO figure out why it fails if this field is not transient
    private transient IfcDecActorSelect creatingActor;
    private IfcDectDateTime creatingTime;

    public IfcDecLabel getName() {
        return name;
    }

    public void setName(IfcDecLabel name) {
        this.name = name;
    }

    public IfcDecText getDescription() {
        return description;
    }

    public void setDescription(IfcDecText description) {
        this.description = description;
    }

    public IfcDecConstraintEnum getConstraintGrade() {
        return constraintGrade;
    }

    public void setConstraintGrade(IfcDecConstraintEnum constraintGrade) {
        this.constraintGrade = constraintGrade;
    }

    public IfcDecLabel getConstraintSource() {
        return constraintSource;
    }

    public void setConstraintSource(IfcDecLabel constraintSource) {
        this.constraintSource = constraintSource;
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
}
