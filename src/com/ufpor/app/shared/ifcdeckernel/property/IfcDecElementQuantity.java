package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.IfcClientElementQuantity;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientConstraint;
import com.ufpor.app.shared.ifcclient.constraint.IfcClientMetric;
import com.ufpor.app.shared.ifcclient.property.IfcClientPhysicalQuantity;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityArea;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecMetric;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/23/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecElementQuantity extends IfcDecQuantitySet {
    public ArrayList<IfcDecPhysicalQuantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(ArrayList<IfcDecPhysicalQuantity> quantities) {
        this.quantities = quantities;
    }

    public String getMethodOfMeasurement() {
        return methodOfMeasurement;
    }

    public void setMethodOfMeasurement(String methodOfMeasurement) {
        this.methodOfMeasurement = methodOfMeasurement;
    }

    @NotPersistent
    private ArrayList<IfcDecPhysicalQuantity> quantities = new ArrayList<IfcDecPhysicalQuantity>();
    @Persistent(serialized = "true")
    private ArrayList<IfcDecQuantityArea> quantities_area = new ArrayList<IfcDecQuantityArea>();
    @Persistent
    private String methodOfMeasurement;

    public static IfcDecElementQuantity getInstance(IfcClientElementQuantity client) {
        IfcDecElementQuantity result = new IfcDecElementQuantity();
        for (IfcClientConstraint clientConstraint : client.getConstraints()) {
            if (clientConstraint instanceof IfcClientMetric) {
                result.getConstraints().add(IfcDecMetric.getInstance((IfcClientMetric) clientConstraint));
            }
        }
        result.setDescription(client.getDescription() != null ? client.getDescription().getValue() : null);
        result.setName(client.getName() != null ? client.getName().getValue() : null);
        result.setMethodOfMeasurement(client.getMethodOfMeasurement());
        for (IfcClientPhysicalQuantity quantity : client.getQuantities()) {
            if (quantity instanceof IfcClientQuantityArea) {
                result.getQuantities().add(IfcDecQuantityArea.getInstance((IfcClientQuantityArea) quantity));
            }
        }
        return result;

    }

    public void onPrePut() {
        quantities_area = new ArrayList<IfcDecQuantityArea>();
        for ( IfcDecPhysicalQuantity q : getQuantities()) {
            quantities_area.add((IfcDecQuantityArea) q);
        }
    }

    public void onPostLoad() {
        for (IfcDecQuantityArea area : quantities_area) {
            quantities.add(area);
        }
    }

}









