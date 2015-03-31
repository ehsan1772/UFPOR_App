package com.ufpor.app.shared.ifcclient.property;

import com.ufpor.app.shared.ifcclient.IfcClientGenericSimpleValue;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySingleValue;
import com.ufpor.app.shared.ifcclient.IfcClientText;
import com.ufpor.app.shared.ifcclient.constraint.IfcBenchmarkEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
public class SpaceCommonPropertySetBuilder extends PropertySetBuilder {
    IfcClientPropertySingleValue referenceIdValue = new IfcClientPropertySingleValue("ReferenceId");
    IfcClientPropertySingleValue isExternalProperty = new IfcClientPropertySingleValue("IsExternal");
    IfcClientPropertySingleValue grossPlannedAreaProperty = new IfcClientPropertySingleValue("GrossPlannedArea");
    IfcClientPropertySingleValue isHandicaAccessibleProperty = new IfcClientPropertySingleValue("HandicapAccessible");
    IfcClientPropertySingleValue isPublicallyAccessibleProperty = new IfcClientPropertySingleValue("PubliclyAccessible");
    IfcClientPropertySingleValue netPlannedAreaProperty = new IfcClientPropertySingleValue("NetPlannedArea");
    IfcClientPropertySingleValue isPublicProperty = new IfcClientPropertySingleValue("IsPublic");

    @Override
    public String getPropertSetName() {
        return "Pset_SpaceCommon";
    }

    @Override
    public String getPropertSetDescription() {
        return "Properties common to the definition of all occurrences of IfcSpace.";
    }

    public SpaceCommonPropertySetBuilder() {
        getProperties().getProperties().add(referenceIdValue);
        getProperties().getProperties().add(isExternalProperty);
        getProperties().getProperties().add(isPublicProperty);
        getProperties().getProperties().add(grossPlannedAreaProperty);
        getProperties().getProperties().add(netPlannedAreaProperty);
        getProperties().getProperties().add(isPublicallyAccessibleProperty);
        getProperties().getProperties().add(isHandicaAccessibleProperty);
    }

    public void setReferenceId(String id) {
        referenceIdValue.setNominalValue(new IfcClientText(id));
    }


    public void setIsExternal(boolean value) {
        isExternalProperty.setNominalValue(new IfcClientText(value ? "TRUE" : "FALSE"));

    }

    public void setIsExternalConstraint(boolean value) {
        IfcClientMetric constraint = new IfcClientMetric(isExternalProperty.getName(), IfcConstraintEnum.HARD);
        constraint.setDataValue(new IfcClientGenericSimpleValue<Boolean>(value));
        isExternalProperty.setBooleanConstraint(constraint);
    }

    public void setIsPublic(boolean value) {
        isPublicProperty.setNominalValue(new IfcClientText(value ? "TRUE" : "FALSE"));

    }

    public void setIsPublicConstraint(boolean value) {
        IfcClientMetric constraint = new IfcClientMetric(isPublicProperty.getName() + "_BOOLEAN", IfcConstraintEnum.HARD);
        constraint.setDataValue(new IfcClientGenericSimpleValue<Boolean>(value));
        isExternalProperty.setBooleanConstraint(constraint);
    }

    public void setGrossPlannedArea(double value) {
        grossPlannedAreaProperty.setNominalValue(new IfcClientText(String.valueOf(value)));

    }

    public void setGrossPlannedAreaMax(double value) {
        IfcClientMetric constraint = new IfcClientMetric(grossPlannedAreaProperty.getName() + "_MAX", IfcConstraintEnum.HARD);
        constraint.setBenchMark(IfcBenchmarkEnum.LESSTHANOREQUALTO);
        constraint.setDataValue(new IfcClientGenericSimpleValue<Double>(value));
        grossPlannedAreaProperty.setBooleanConstraint(constraint);
    }

    public void setGrossPlannedAreaMin(double value) {
        IfcClientMetric constraint = new IfcClientMetric(grossPlannedAreaProperty.getName() + "_MIN", IfcConstraintEnum.HARD);
        constraint.setBenchMark(IfcBenchmarkEnum.GREATERTHANOREQUALTO);
        constraint.setDataValue(new IfcClientGenericSimpleValue<Double>(value));
        grossPlannedAreaProperty.setBooleanConstraint(constraint);
    }

    public void setNetPlannedArea(double value) {
        netPlannedAreaProperty.setNominalValue(new IfcClientText(String.valueOf(value)));

    }

    public void setNetPlannedAreaMax(double value) {
        IfcClientMetric constraint = new IfcClientMetric(netPlannedAreaProperty.getName() + "_MAX", IfcConstraintEnum.HARD);
        constraint.setBenchMark(IfcBenchmarkEnum.LESSTHANOREQUALTO);
        constraint.setDataValue(new IfcClientGenericSimpleValue<Double>(value));
        netPlannedAreaProperty.setBooleanConstraint(constraint);
    }

    public void setNetPlannedAreaMin(double value) {
        IfcClientMetric constraint = new IfcClientMetric(netPlannedAreaProperty.getName() + "_MIN", IfcConstraintEnum.HARD);
        constraint.setBenchMark(IfcBenchmarkEnum.GREATERTHANOREQUALTO);
        constraint.setDataValue(new IfcClientGenericSimpleValue<Double>(value));
        netPlannedAreaProperty.setBooleanConstraint(constraint);
    }

    public void setIsPubliclyAccessible(boolean value) {
        isPublicallyAccessibleProperty.setNominalValue(new IfcClientText(value ? "TRUE" : "FALSE"));

    }

    public void setIsPubliclyAccessibleConstraint(boolean value) {
        IfcClientMetric constraint = new IfcClientMetric(isPublicallyAccessibleProperty.getName() + "_BOOLEAN", IfcConstraintEnum.HARD);
        constraint.setDataValue(new IfcClientGenericSimpleValue<Boolean>(value));
        isPublicallyAccessibleProperty.setBooleanConstraint(constraint);
    }

    public void setIsHandicapAccessible(boolean value) {
        isHandicaAccessibleProperty.setNominalValue(new IfcClientText(value ? "TRUE" : "FALSE"));

    }

    public void setIsHandicaAccessiblePropertyConstraint(boolean value) {
        IfcClientMetric constraint = new IfcClientMetric(isHandicaAccessibleProperty.getName() + "_BOOLEAN", IfcConstraintEnum.HARD);
        constraint.setDataValue(new IfcClientGenericSimpleValue<Boolean>(value));
        isHandicaAccessibleProperty.setBooleanConstraint(constraint);
    }
}
