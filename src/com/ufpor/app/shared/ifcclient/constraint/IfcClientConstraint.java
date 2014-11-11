package com.ufpor.app.shared.ifcclient.constraint;

import com.ufpor.app.shared.ifcclient.IfcClientDateTime;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.IfcClientText;
import com.ufpor.app.shared.ifcclient.actor.IfcClientActorSelect;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientConstraint {
    private IfcClientLabel name;
    private IfcClientText description;
    private IfcClientConstraintEnum constraintGrade;
    private IfcClientLabel constraintSource;
    private IfcClientActorSelect creatingActor;
    private IfcClientDateTime creatingTime;
    private IfcClientLabel userDefinedGrade;

    public IfcClientConstraint(IfcClientLabel name, IfcClientConstraintEnum constraintGrade) {
        this.name = name;
        this.constraintGrade = constraintGrade;
    }
}
