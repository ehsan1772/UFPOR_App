package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.constraint.IfcClientConstraint;
import com.ufpor.app.shared.ifcclient.select.IfcClientDefinitionSelect;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
public abstract class IfcClientPropertyDefinition extends IfcClientRoot implements IfcClientDefinitionSelect {

    protected ArrayList<IfcClientConstraint> constraints = new ArrayList<IfcClientConstraint>();

    public ArrayList<IfcClientConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(ArrayList<IfcClientConstraint> constraints) {
        this.constraints = constraints;
    }
}
