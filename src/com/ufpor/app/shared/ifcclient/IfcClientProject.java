package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.decproduct.IfcClientSpatialElement;
import com.ufpor.app.shared.ifcclient.measure.IfcClientAreaMeasure;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientProject extends IfcClientContext {
    protected final static IfcClientSIUnit AREA_UNIT = getAreaUnit();
    // TODO this property represents IfcRelDefinesByProperties
    protected IfcClientPropertySet generalProperties;

    /**
     * This is the root of the spatial structure and is either a building, a site
     * or a spatial zone. seems like a project can have more than one spatial root
     * if they are independent (for example two different sites)
     * //TODO this relationship should be represented by IfcRelAggregates
     */
    protected ArrayList<IfcClientSpatialElement> spatialStructureRoot;
    private IfcClientPropertySingleValue areaProperties;

    public IfcClientProject() {
        isDefinedBy = new ArrayList<IfcClientPropertySetDefinition>();
        //creating the property set
        generalProperties = new IfcClientPropertySet();

        spatialStructureRoot = new ArrayList<IfcClientSpatialElement>();

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

    /**
     *
     * @param area
     */
    public void setTotalGrossArea(double area) {
        IfcClientValue areaMeasure = new IfcClientAreaMeasure(area);

        IfcClientPropertySingleValue property = new IfcClientPropertySingleValue(AREA_UNIT);
        property.setNominalValue(areaMeasure);

        areaProperties = property;
        generalProperties.getProperties().add(property);
    }


    public void setMinArea(double minValue) {
        areaProperties.setMinArea(minValue);
    }

    public void setMaxArea(double maxValue) {
        areaProperties.setMaxArea(maxValue);
    }

    public void addSpatialStructureRoot(IfcClientSpatialElement root) {
        spatialStructureRoot.add(root);
    }
}
