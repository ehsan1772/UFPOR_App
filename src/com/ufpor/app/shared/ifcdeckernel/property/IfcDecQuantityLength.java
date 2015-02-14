package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcclient.constraint.IfcBenchmarkEnum;
import com.ufpor.app.shared.ifcclient.constraint.IfcConstraintEnum;
import com.ufpor.app.shared.ifcclient.property.IfcClientPhysicalQuantity;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityLength;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecMetric;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecObjective;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/23/14.
 */
public class IfcDecQuantityLength extends IfcDecPhysicalSimpleQuantity {
    private double lengthValue;
    private String formula;


    public IfcDecQuantityLength(String name, double length) {
        super(name);
        lengthValue = length;
    }

    public IfcDecQuantityLength() {
    }

    @Override
    public void setMaxValue(Object value) {
        IfcDecMetric maxMetric = new IfcDecMetric("MAX_VALUE", IfcConstraintEnum.HARD);
        maxMetric.setBenchMark(IfcBenchmarkEnum.LESSTHANOREQUALTO);
        maxMetric.setDataValue(new IfcDecReal((Double) value));
        constraints.getBenchmarkValues().add(maxMetric);
    }

    @Override
    public void setMinValue(Object value) {
        IfcDecMetric minMetric = new IfcDecMetric("MIN_VALUE", IfcConstraintEnum.HARD);
        minMetric.setBenchMark(IfcBenchmarkEnum.GREATERTHANOREQUALTO);
        minMetric.setDataValue(new IfcDecReal((Double) value));
        constraints.getBenchmarkValues().add(minMetric);
    }

    @Override
    public void onPostLoad() {
        getConstraints().onPostLoad();
    }

    @Override
    public String getIfcString() {
        String name = getName();
        String description = (getDescription() != null && !getDescription().isEmpty()) ? getDescription() : "*";
        String unit = "*";
        String lengthValue = String.valueOf(getLengthValue());
        String formula = (getFormula() != null && !getFormula().isEmpty()) ? getFormula() : "*";
        return String.format(Constants.IFCQUANTITYLENGTH, name, description, unit, lengthValue, formula);
    }

    public static IfcDecQuantityLength getInstance(IfcClientQuantityLength client) {

        IfcDecQuantityLength result = new IfcDecQuantityLength(client.getName(), client.getLength());
        result.setFormula(client.getFormula());
        result.setDescription(client.getDescription());
        if (client.getUnit() != null) {
            result.setUnit(IfcDecSIUnit.getInstance((com.ufpor.app.shared.ifcclient.IfcClientSIUnit) client.getUnit()));
        }

        result.setConstraints(IfcDecObjective.getInstance(client.getConstraints()));

        return result;
    }

    public static IfcClientQuantityLength getClientInstance(IfcDecQuantityLength server) {
        IfcClientQuantityLength result = new IfcClientQuantityLength();

        result.setDescription(server.getDescription());
        result.setFormula(server.getFormula());
        if (server.getUnit() != null) {
            result.setUnit(IfcDecSIUnit.getClientInstance((IfcDecSIUnit) server.getUnit()));
        }

        result.setConstraints(IfcDecObjective.getClientInstance(server.getConstraints()));

        return result;
    }

    public double getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(double lengthValue) {
        this.lengthValue = lengthValue;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        ArrayList<IfcFileObject> result = new ArrayList<>();
        result.add(constraints);
        return result;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return getIfcString();
    }


    //public enum Type {GrossFloorArea, NetFloorArea, GrossWallArea, NetWallArea, GrossCeilingArea, NetCeilingArea}
}
