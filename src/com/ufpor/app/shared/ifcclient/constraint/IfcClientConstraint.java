package com.ufpor.app.shared.ifcclient.constraint;

import com.ufpor.app.shared.ifcclient.IfcClientDateTime;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.actor.IfcClientActorSelect;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientConstraint implements Serializable {
    private String name;
    private String description;
    private IfcConstraintEnum constraintGrade;
    private String constraintSource;
    //TODO figure out why it fails if this field is not transient
    private transient IfcClientActorSelect creatingActor;
    private IfcClientDateTime creatingTime;


    public IfcConstraintEnum getConstraintGrade() {
        return constraintGrade;
    }

    public void setConstraintGrade(IfcConstraintEnum constraintGrade) {
        this.constraintGrade = constraintGrade;
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

    public IfcClientConstraint(String name, IfcConstraintEnum constraintGrade) {
        this.name = name;
        this.constraintGrade = constraintGrade;
    }

    protected IfcClientConstraint() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IfcClientConstraint) {
            return getName().equals(((IfcClientConstraint) obj).getName());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
