package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.constraint.IfcClientBenchmarkEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientConstraint;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientConstraintEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientProperty extends IfcClientPropertyAbstraction {
    private static final String MIN_CLIENT = "min_area";
    private static final String MAX_CLIENT = "max_area";
    private IfcClientIdentifier name;
    private IfcClientText description;
    private ArrayList<IfcClientConstraint> constraints = new ArrayList<IfcClientConstraint>();

    public IfcClientIdentifier getName() {
        return name;
    }

    public void setName(IfcClientIdentifier name) {
        this.name = name;
    }

    public IfcClientText getDescription() {
        return description;
    }

    public void setDescription(IfcClientText description) {
        this.description = description;
    }

    public void addConstraint(IfcClientConstraint constraint) {
        constraints.add(constraint);
    }

    /**
     * TODO This relationship represents IfcResourceConstraintRelationship
     *
     * @param minValue
     */
    public void setMinArea(double minValue) {
        IfcClientMetric min = new IfcClientMetric(new IfcClientLabel(MIN_CLIENT), IfcClientConstraintEnum.HARD);
        min.setBenchMark(IfcClientBenchmarkEnum.GREATERTHANOREQUALTO);
        min.setDataValue(new IfcClientReal(minValue));
        constraints.add(min);
    }

    /**
     * TODO This relationship represents IfcResourceConstraintRelationship
     *
     * @param maxValue
     */
    public void setMaxArea(double maxValue) {
        IfcClientMetric max = new IfcClientMetric(new IfcClientLabel(MAX_CLIENT), IfcClientConstraintEnum.HARD);
        max.setBenchMark(IfcClientBenchmarkEnum.LESSTHANOREQUALTO);
        max.setDataValue(new IfcClientReal(maxValue));
        constraints.add(max);
    }

}
