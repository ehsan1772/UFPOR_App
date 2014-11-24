package com.ufpor.app.shared.ifcdeckernel.property.constraint;

import com.ufpor.app.server.Guid;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.shared.ifcclient.IfcClientInteger;
import com.ufpor.app.shared.ifcclient.IfcClientReal;
import com.ufpor.app.shared.ifcclient.IfcClientText;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcdeckernel.IfcDecLabel;
import com.ufpor.app.shared.ifcdeckernel.property.*;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/18/14.
 */
public class IfcDecMetric extends IfcDecConstraint implements Serializable {
    private IfcDecBenchmarkEnum benchMark;
    private IfcDecLabel valueSource;
    private IfcDecMetricValueSelect dataValue;

    public IfcDecBenchmarkEnum getBenchMark() {
        return benchMark;
    }

    public void setBenchMark(IfcDecBenchmarkEnum benchMark) {
        this.benchMark = benchMark;
    }

    public IfcDecLabel getValueSource() {
        return valueSource;
    }

    public void setValueSource(IfcDecLabel valueSource) {
        this.valueSource = valueSource;
    }

    public IfcDecMetricValueSelect getDataValue() {
        return dataValue;
    }

    public void setDataValue(IfcDecMetricValueSelect dataValue) {
        this.dataValue = dataValue;
    }

    public static IfcDecMetric getInstance(IfcClientMetric client) {
        IfcDecMetric constraint = new IfcDecMetric();
        constraint.setDescription(IfcDecText.getInstance(client.getDescription()));
        constraint.setName(IfcDecLabel.getInstance(client.getName()));
        constraint.setConstraintGrade(IfcDecConstraintEnum.getInstance(client.getConstraintGrade()));
        constraint.setConstraintSource(IfcDecLabel.getInstance(client.getConstraintSource()));
        constraint.setCreatingTime(IfcDectDateTime.getInstance(client.getCreatingTime()));
        //TODO add actor from the client to the dec
        constraint.setBenchMark(IfcDecBenchmarkEnum.getInstance(client.getBenchMark()));
        constraint.setValueSource(IfcDecLabel.getInstance(client.getValueSource()));

        if (client.getDataValue() instanceof IfcClientReal) {
            constraint.setDataValue(IfcDecReal.getInstance((IfcClientReal) client.getDataValue()));
        }

        if (client.getDataValue() instanceof IfcClientText) {
            constraint.setDataValue(IfcDecText.getInstance((IfcClientText) client.getDataValue()));
        }

        if (client.getDataValue() instanceof IfcClientInteger) {
            constraint.setDataValue(IfcDecInteger.getInstance((IfcClientInteger) client.getDataValue()));
        }

        return constraint;

    }

    @Override
    public String getIfcString() {
        // String guid = GuidCompressor.getNewIfcGloballyUniqueId();
        String name = Constants.getStringFromLabel(getName());
        String description = Constants.getStringFromText(getDescription());
        String grade = getConstraintGrade().name();
        String source = Constants.getStringFromLabel(getValueSource());
        String actor = "*";
        //String creationTime = getCreatingTime().getValue();
        String creationTime = "*";
        String userDefined = getUserDefinedGrade() != null ? getUserDefinedGrade() : "*";

        String benchMark = getBenchMark().name();
        String valueSource = Constants.getStringFromLabel(getValueSource());

        String value = ((IfcDecValue) getDataValue()).getIfcString();
        String referencePath = "*";

        return String.format(Constants.IFCMETRIC, name, description, grade, source, actor, creationTime, userDefined, benchMark, valueSource, value, referencePath);
    }

    @Override
    public String getIfcStringConstraintRelationship(String constraintNumber, String arrayObjects) {
        String name = Constants.getStringFromLabel(getName());
        String description = Constants.getStringFromText(getDescription());

        return String.format(Constants.IFCRESOURCECONSTRAINTRELATIONSHIP, name, description, constraintNumber, arrayObjects);


    }


}
