package com.ufpor.app.shared.ifcclient.constraint;

import com.ufpor.app.shared.ifcclient.IfcClientDateTime;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.IfcClientText;
import com.ufpor.app.shared.ifcclient.actor.IfcClientActorSelect;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientConstraint implements Serializable {
    private IfcClientLabel name;
    private IfcClientText description;
    private IfcClientConstraintEnum constraintGrade;
    private IfcClientLabel constraintSource;
    private IfcClientActorSelect creatingActor;
    private IfcClientDateTime creatingTime;

    public IfcClientLabel getName() {
        return name;
    }

    public void setName(IfcClientLabel name) {
        this.name = name;
    }

    public IfcClientText getDescription() {
        return description;
    }

    public void setDescription(IfcClientText description) {
        this.description = description;
    }

    public IfcClientConstraintEnum getConstraintGrade() {
        return constraintGrade;
    }

    public void setConstraintGrade(IfcClientConstraintEnum constraintGrade) {
        this.constraintGrade = constraintGrade;
    }

    public IfcClientLabel getConstraintSource() {
        return constraintSource;
    }

    public void setConstraintSource(IfcClientLabel constraintSource) {
        this.constraintSource = constraintSource;
    }

    public IfcClientActorSelect getCreatingActor() {
        return creatingActor;
    }

    public void setCreatingActor(IfcClientActorSelect creatingActor) {
        this.creatingActor = creatingActor;
    }

    public IfcClientDateTime getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(IfcClientDateTime creatingTime) {
        this.creatingTime = creatingTime;
    }

    public IfcClientLabel getUserDefinedGrade() {
        return userDefinedGrade;
    }

    public void setUserDefinedGrade(IfcClientLabel userDefinedGrade) {
        this.userDefinedGrade = userDefinedGrade;
    }

    private IfcClientLabel userDefinedGrade;

    public IfcClientConstraint(IfcClientLabel name, IfcClientConstraintEnum constraintGrade) {
        this.name = name;
        this.constraintGrade = constraintGrade;
    }

    protected IfcClientConstraint() {
    }
}
