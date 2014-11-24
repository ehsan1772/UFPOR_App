package com.ufpor.app.shared.ifcclient.type;

import com.ufpor.app.shared.ifcclient.IfcClientElementQuantity;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityArea;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityLength;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
public class SpaceTypeQuantityBuilder {
    IfcClientElementQuantity quantity;

    public SpaceTypeQuantityBuilder() {
        quantity = new IfcClientElementQuantity();
    }

    public SpaceTypeQuantityBuilder(IfcClientElementQuantity quantity) {
        this.quantity = quantity;
    }

    public void addHeight(double height) {
        quantity.getQuantities().add(new IfcClientQuantityLength(IfcClientQuantityLength.Type.Height, height));
    }

    public void addFinishCeilingHeight(double height) {
        quantity.getQuantities().add(new IfcClientQuantityLength(IfcClientQuantityLength.Type.FinishCeilingHeight, height));
    }

    public void addFinishFloorHeight(double height) {
        quantity.getQuantities().add(new IfcClientQuantityLength(IfcClientQuantityLength.Type.FinishFloorHeight, height));
    }

    public void addGrossPerimeter(double perimeter) {
        quantity.getQuantities().add(new IfcClientQuantityLength(IfcClientQuantityLength.Type.GrossPerimeter, perimeter));
    }

    public void addNetPerimeter(double perimeter) {
        quantity.getQuantities().add(new IfcClientQuantityLength(IfcClientQuantityLength.Type.NetPerimeter, perimeter));
    }

    public void addGrossFloorArea(double area) {
        quantity.getQuantities().add(new IfcClientQuantityArea(IfcClientQuantityArea.Type.GrossFloorArea, area));
    }

    public void addNetFloorArea(double area) {
        quantity.getQuantities().add(new IfcClientQuantityArea(IfcClientQuantityArea.Type.NetFloorArea, area));
    }

    public void addGrossWallArea(double area) {
        quantity.getQuantities().add(new IfcClientQuantityArea(IfcClientQuantityArea.Type.GrossWallArea, area));
    }

    public void addNetWallArea(double area) {
        quantity.getQuantities().add(new IfcClientQuantityArea(IfcClientQuantityArea.Type.NetWallArea, area));
    }

    public void addGrossCeilingArea(double area) {
        quantity.getQuantities().add(new IfcClientQuantityArea(IfcClientQuantityArea.Type.GrossCeilingArea, area));
    }

    public void addNetCeilingArea(double area) {
        quantity.getQuantities().add(new IfcClientQuantityArea(IfcClientQuantityArea.Type.NetCeilingArea, area));
    }
}
