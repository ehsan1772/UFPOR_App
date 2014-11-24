package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.constraint.IfcClientBenchmarkEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientConstraintEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcclient.property.IfcClientPhysicalQuantity;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/23/14.
 */
public class IfcClientElementQuantity extends IfcClientQuantitySet {
    private ArrayList<IfcClientPhysicalQuantity> quantities;
    private String methodOfMeasurement;

    public IfcClientElementQuantity(ArrayList<IfcClientPhysicalQuantity> quantities) {
        this.quantities = quantities;
    }

    public IfcClientElementQuantity() {
    }

    public ArrayList<IfcClientPhysicalQuantity> getQuantities() {
        if (quantities == null) {
            quantities = new ArrayList<IfcClientPhysicalQuantity>();
        }
        return quantities;
    }

    public void setQuantities(ArrayList<IfcClientPhysicalQuantity> quantities) {
        this.quantities = quantities;
    }

    public String getMethodOfMeasurement() {
        return methodOfMeasurement;
    }

    public void setMethodOfMeasurement(String methodOfMeasurement) {
        this.methodOfMeasurement = methodOfMeasurement;
    }

    /**
     * TODO This relationship represents IfcResourceConstraintRelationship
     *
     * @param maxValue
     */
    public void setMax(double maxValue, ConstraintType type) {
        IfcClientMetric max = new IfcClientMetric(new IfcClientLabel(type.name()), IfcClientConstraintEnum.HARD);
        constraints.add(max);
        max.setBenchMark(IfcClientBenchmarkEnum.LESSTHANOREQUALTO);
        max.setDataValue(new IfcClientReal(maxValue));
    }

    /**
     * TODO This relationship represents IfcResourceConstraintRelationship
     *
     * @param minValue
     */
    public void setMin(double minValue, ConstraintType type) {
        IfcClientMetric min = new IfcClientMetric(new IfcClientLabel(type.name()), IfcClientConstraintEnum.HARD);
        constraints.add(min);
        min.setBenchMark(IfcClientBenchmarkEnum.GREATERTHANOREQUALTO);
        min.setDataValue(new IfcClientReal(minValue));
    }

    public enum ConstraintType {GrossFloorArea_Max, GrossFloorArea_Min, NetFloorArea_Max, NetFloorArea_Min, GrossWallArea_Max, GrossWallArea_Min, NetWallArea_Max, NetWallArea_Min, GrossCeilingArea_Max, GrossCeilingArea_Min, NetCeilingArea_Max, NetCeilingArea_Min}
}
