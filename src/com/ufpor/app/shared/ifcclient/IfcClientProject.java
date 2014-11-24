package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.decproduct.IfcClientSpatialElement;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityArea;
import com.ufpor.app.shared.ifcclient.select.IfcClientPropertySetDefinitionSelect;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientProject extends IfcClientContext {
    protected final static IfcClientSIUnit AREA_UNIT = getAreaUnit();
    /**
     * this is how we can add properties such as total area. those properties might be associated with constraints
     */
    // TODO this property represents IfcRelDefinesByProperties
    protected IfcClientElementQuantity generalQuantities;

    /**
     * This is the root of the spatial structure and is either a building, a site
     * or a spatial zone. seems like a project can have more than one spatial root
     * if they are independent (for example two different sites)
     * //TODO this relationship should be represented by IfcRelAggregates
     */
    protected ArrayList<IfcClientSpatialElement> spatialStructureRoot;
    private IfcClientQuantityArea netAreaQuantity;

    public IfcClientProject() {
        isDefinedBy = new ArrayList<IfcClientPropertySetDefinitionSelect>();
        //creating the property set
        generalQuantities = new IfcClientElementQuantity();

        isDefinedBy.add(generalQuantities);

        spatialStructureRoot = new ArrayList<IfcClientSpatialElement>();


        // to create and add the property to the project
        setTotalGrossArea(0);

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
//        IfcClientValue areaMeasure = new IfcClientAreaMeasure(area);
//
//        IfcClientPropertySingleValue property = new IfcClientPropertySingleValue(AREA_UNIT);
//        property.setNominalValue(areaMeasure);
//        property.setName(new IfcClientIdentifier());
        netAreaQuantity = new IfcClientQuantityArea(IfcClientQuantityArea.Type.NetFloorArea, area);
      //  IfcClientElementQuantity property = new IfcClientElementQuantity();
        generalQuantities.getQuantities().add(netAreaQuantity);

      //  areaProperties = property;

    }


    public void setMinArea(double minValue) {
        generalQuantities.setMin(minValue, IfcClientElementQuantity.ConstraintType.GrossFloorArea_Min);
    }

    public void setMaxArea(double maxValue) {
        generalQuantities.setMax(maxValue, IfcClientElementQuantity.ConstraintType.GrossFloorArea_Max);
    }

    public void addSpatialStructureRoot(IfcClientSpatialElement root) {
        spatialStructureRoot.add(root);
    }
}
