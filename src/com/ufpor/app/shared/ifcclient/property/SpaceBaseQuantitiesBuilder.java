package com.ufpor.app.shared.ifcclient.property;

import com.ufpor.app.shared.ifcclient.IfcClientElementQuantity;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
public class SpaceBaseQuantitiesBuilder {
    //   private ArrayList<IfcClientConstraint> constraints;
    private IfcClientElementQuantity ifcClientElementQuantity = new IfcClientElementQuantity();
    private IfcClientQuantityLength height = new IfcClientQuantityLength();
    private IfcClientQuantityLength finishFloorHeight = new IfcClientQuantityLength(IfcClientQuantityLength.Type.FinishFloorHeight);
    private IfcClientQuantityLength finishCeilingHeight = new IfcClientQuantityLength(IfcClientQuantityLength.Type.FinishCeilingHeight);
    private IfcClientQuantityLength grossPerimeter = new IfcClientQuantityLength(IfcClientQuantityLength.Type.GrossPerimeter);
    private IfcClientQuantityLength netPerimeter = new IfcClientQuantityLength(IfcClientQuantityLength.Type.NetPerimeter);
    private IfcClientQuantityArea grossFloorArea = new IfcClientQuantityArea(IfcClientQuantityArea.Type.NetFloorArea);
    private IfcClientQuantityArea netFloorArea = new IfcClientQuantityArea(IfcClientQuantityArea.Type.NetFloorArea);
    private IfcClientQuantityArea grossWallArea = new IfcClientQuantityArea(IfcClientQuantityArea.Type.GrossWallArea);
    private IfcClientQuantityArea netWallArea = new IfcClientQuantityArea(IfcClientQuantityArea.Type.NetWallArea);
    private IfcClientQuantityArea grossCeilingArea = new IfcClientQuantityArea(IfcClientQuantityArea.Type.GrossCeilingArea);
    private IfcClientQuantityArea netCeilingArea = new IfcClientQuantityArea(IfcClientQuantityArea.Type.NetCeilingArea);

    public SpaceBaseQuantitiesBuilder() {
        ifcClientElementQuantity.addQuantity(height);
        ifcClientElementQuantity.addQuantity(finishCeilingHeight);
        ifcClientElementQuantity.addQuantity(finishFloorHeight);
        ifcClientElementQuantity.addQuantity(grossPerimeter);
        ifcClientElementQuantity.addQuantity(netPerimeter);
        ifcClientElementQuantity.addQuantity(grossFloorArea);
        ifcClientElementQuantity.addQuantity(netFloorArea);
        ifcClientElementQuantity.addQuantity(grossWallArea);
        ifcClientElementQuantity.addQuantity(netWallArea);
        ifcClientElementQuantity.addQuantity(netCeilingArea);
    }

    public IfcClientElementQuantity getIfcClientElementQuantity() {
        return ifcClientElementQuantity;
    }

    public void setMaxHeight(double value) {
        height.setMaxValue(value);
    }

    public void setMaxFinishCeilingHeight(double value) {
        finishCeilingHeight.setMaxValue(value);
    }

    public void setMaxFinishFloorHeight(double value) {
        finishFloorHeight.setMaxValue(value);
    }

    public void setMaxGrossPerimeter(double value) {
        grossPerimeter.setMaxValue(value);
    }

    public void setMaxNetPerimeter(double value) {
        netPerimeter.setMaxValue(value);
    }


    public void setMinHeight(double value) {
        height.setMinValue(value);
    }

    public void setMinFinishCeilingHeight(double value) {
        finishCeilingHeight.setMinValue(value);
    }

    public void setMinFinishFloorHeight(double value) {
        finishFloorHeight.setMinValue(value);
    }

    public void setMinGrossPerimeter(double value) {
        grossPerimeter.setMinValue(value);
    }

    public void setMinNetPerimeter(double value) {
        netPerimeter.setMinValue(value);
    }

    public void setMaxNetFloorArea(double value) {
        netFloorArea.setMaxValue(value);
    }

    public void setMaxGrossFloorArea(double value) {
        grossFloorArea.setMaxValue(value);
    }

    public void setMaxGrossWallArea(double value) {
        grossWallArea.setMaxValue(value);
    }

    public void setMaxNetWallArea(double value) {
        netWallArea.setMaxValue(value);
    }

    public void setMaxGrossCeilingArea(double value) {
        grossCeilingArea.setMaxValue(value);
    }

    public void setMaxNetCeilingArea(double value) {
        netCeilingArea.setMaxValue(value);
    }

    public void setMinNetFloorArea(double value) {
        netFloorArea.setMinValue(value);
    }

    public void setMinGrossFloorArea(double value) {
        grossFloorArea.setMinValue(value);
    }

    public void setMinGrossWallArea(double value) {
        grossWallArea.setMinValue(value);
    }

    public void setMinNetWallArea(double value) {
        netWallArea.setMinValue(value);
    }

    public void setMinGrossCeilingArea(double value) {
        grossCeilingArea.setMinValue(value);
    }

    public void setMinNetCeilingArea(double value) {
        netCeilingArea.setMinValue(value);
    }

}
