package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.constraint.IfcClientBenchmarkEnum;
import com.ufpor.app.shared.ifcdeckernel.IfcDecIdentifier;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecBenchmarkEnum;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecConstraint;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecMetric;

import javax.jdo.annotations.*;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecProperty extends IfcDecPropertyAbstraction {
    @Persistent
    private IfcDecIdentifier name;
    @Persistent
    private IfcDecText description;
    private String ifcString;

    public ArrayList<IfcDecConstraint> getConstraints() {
        return constraints;
    }

    @Persistent(serialized = "true")
    private ArrayList<IfcDecConstraint> constraints = new ArrayList<IfcDecConstraint>();

    public IfcDecText getDescription() {
        return description;
    }

    public void setDescription(IfcDecText description) {
        this.description = description;
    }

    public IfcDecIdentifier getName() {
        return name;
    }

    public void setName(IfcDecIdentifier name) {
        this.name = name;
    }

    /**
     * TODO This relationship represents IfcResourceConstraintRelationship
     *
     * @param minValue
     */
    public void setMinArea(double minValue) {
        IfcDecMetric min = new IfcDecMetric();
        constraints.add(min);
        min.setBenchMark(IfcDecBenchmarkEnum.get(IfcClientBenchmarkEnum.GREATERTHANOREQUALTO));
        min.setDataValue(new IfcDecReal(minValue));
    }

    /**
     * TODO This relationship represents IfcResourceConstraintRelationship
     *
     * @param maxValue
     */
    public void setMaxArea(double maxValue) {
        IfcDecMetric max = new IfcDecMetric();
        constraints.add(max);
        max.setBenchMark(IfcDecBenchmarkEnum.get(IfcClientBenchmarkEnum.LESSTHANOREQUALTO));
        max.setDataValue(new IfcDecReal(maxValue));
    }

    public void addConstraint(IfcDecConstraint constraint) {
        constraints.add(constraint);
    }

    public abstract String getIfcString();
}
