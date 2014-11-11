package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.measure.IfcClientAreaMeasure;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientProject extends IfcClientContext {
    protected final static IfcClientSIUnit AREA_UNIT = getAreaUnit();
    protected IfcClientPropertySet generalProperties;

    public IfcClientProject() {
        isDefinedBy = new ArrayList<IfcClientPropertySetDefinition>();
        //creating the property set
        generalProperties = new IfcClientPropertySet();

        //creating the properties

    }

    public static IfcClientSIUnit getAreaUnit() {
        IfcClientDimensionalExponents dimension = new IfcClientDimensionalExponents();
        dimension.setLengthExponent(2);

        IfcClientSIUnit unit = new IfcClientSIUnit(IfcClientNamedUnit.IfcClientUnitEnum.AREAUNIT,
                dimension,
                IfcClientSIUnit.IfcClientSIUnitName.SQUARE_METRE
        );

        return unit;
    }

    public void setTotalGrossArea(double area) {
        IfcClientValue areaMeasure = new IfcClientAreaMeasure(area);

        IfcClientPropertySingleValue property = new IfcClientPropertySingleValue(AREA_UNIT);
        property.setNominalValue(areaMeasure);

        generalProperties.getProperties().add(property);

    }
}
