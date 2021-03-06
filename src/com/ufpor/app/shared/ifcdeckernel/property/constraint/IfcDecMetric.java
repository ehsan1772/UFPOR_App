package com.ufpor.app.shared.ifcdeckernel.property.constraint;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcclient.IfcClientDateTime;
import com.ufpor.app.shared.ifcclient.IfcClientInteger;
import com.ufpor.app.shared.ifcclient.IfcClientReal;
import com.ufpor.app.shared.ifcclient.IfcClientText;
import com.ufpor.app.shared.ifcclient.constraint.IfcBenchmarkEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientConstraint;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;
import com.ufpor.app.shared.ifcdeckernel.property.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/18/14.
 */
public class IfcDecMetric extends IfcDecConstraint implements Serializable {
    private IfcBenchmarkEnum benchMark;
    private String valueSource;
    private IfcDecMetricValueSelect dataValue;

    public IfcDecMetric(String name, IfcConstraintEnum constraintGrade) {
        super(name, constraintGrade);
    }

    public IfcBenchmarkEnum getBenchMark() {
        return benchMark;
    }

    public void setBenchMark(IfcBenchmarkEnum benchMark) {
        this.benchMark = benchMark;
    }

    public String getValueSource() {
        return valueSource;
    }

    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    public IfcDecMetricValueSelect getDataValue() {
        return dataValue;
    }

    public void setDataValue(IfcDecMetricValueSelect dataValue) {
        this.dataValue = dataValue;
    }

    public static IfcDecMetric getInstance(IfcClientMetric client) {
        IfcDecMetric constraint = new IfcDecMetric(client.getName(), client.getConstraintGrade());
        constraint.setDescription(client.getDescription());
        constraint.setConstraintSource(client.getConstraintSource());
        constraint.setCreatingTime(IfcDecDateTime.getInstance(client.getCreatingTime()));
        //TODO add actor from the client to the dec
        constraint.setBenchMark(client.getBenchMark());
        constraint.setValueSource(client.getValueSource());

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

    public static IfcClientConstraint getClientInstance(IfcDecMetric server) {
        IfcClientMetric client = new IfcClientMetric(server.getName(), server.getConstraintGrade());
        client.setDescription(server.getDescription());
        client.setConstraintSource(server.getConstraintSource());
        client.setCreatingTime(IfcDecDateTime.getInstance(server.getCreatingTime()));
        client.setBenchMark(server.getBenchMark());
        client.setValueSource(server.getValueSource());

        if (client.getDataValue() instanceof IfcDecReal) {
            client.setDataValue(new IfcClientReal(((IfcClientReal) client.getDataValue()).getValue()));
        }

        if (client.getDataValue() instanceof IfcDecText) {
            client.setDataValue(new IfcClientText(((IfcClientText) client.getDataValue()).getValue()));
        }

        if (client.getDataValue() instanceof IfcDecInteger) {
            client.setDataValue(new IfcClientInteger(((IfcClientInteger) client.getDataValue()).getValue()));
        }

        return client;

    }

    @Override
    public String getIfcString() {
        // String guid = GuidCompressor.getNewIfcGloballyUniqueId();
        String name = getName();
        String description = getDescription();
        String grade = getConstraintGrade().name();
        String source = getValueSource();
        String actor = "*";
        //String creationTime = getCreatingTime().getValue();
        String creationTime = "*";
        String userDefined = getUserDefinedGrade() != null ? getUserDefinedGrade() : "*";

        String benchMark = getBenchMark().name();
        String valueSource = getValueSource();

        String value = ((IfcDecValue) getDataValue()).getIfcString();
        String referencePath = "*";

        String result = String.format(Constants.IFCMETRIC, name, description, grade, source, actor, creationTime, userDefined, benchMark, valueSource, value, referencePath);
        return result;
      //  return String.format(Constants.IFCMETRIC, name, description, grade, source, actor, creationTime, userDefined, benchMark, valueSource, value, referencePath);
    }

    @Override
    public String getIfcStringConstraintRelationship(String constraintNumber, String arrayObjects) {
        String name = getName();
        String description = getDescription();

        return String.format(Constants.IFCRESOURCECONSTRAINTRELATIONSHIP, name, description, constraintNumber, arrayObjects);


    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        return null;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return getIfcString();
    }
}
