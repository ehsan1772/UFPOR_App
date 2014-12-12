package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcclient.IfcClientElementQuantity;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySetDefinition;
import com.ufpor.app.shared.ifcclient.property.IfcClientPhysicalQuantity;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityArea;
import com.ufpor.app.shared.ifcclient.property.IfcClientQuantityLength;

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

    public ArrayList<IfcDecQuantityArea> getQuantities_area() {
        return quantities_area;
    }

    public ArrayList<IfcDecQuantityLength> getQuantities_length() {
        return quantities_length;
    }

    @Persistent(serialized = "true")
    private ArrayList<IfcDecQuantityArea> quantities_area;
    @Persistent(serialized = "true")
    private ArrayList<IfcDecQuantityLength> quantities_length;
    @Persistent
    private String methodOfMeasurement;

    public static IfcDecElementQuantity getInstance(IfcClientElementQuantity client) {
        IfcDecElementQuantity result = new IfcDecElementQuantity();
        result.setDescription(client.getDescription() != null ? client.getDescription() : null);
        result.setName(client.getName() != null ? client.getName() : null);
        result.setMethodOfMeasurement(client.getMethodOfMeasurement());
        for (IfcClientPhysicalQuantity quantity : client.getQuantities()) {
            if (quantity instanceof IfcClientQuantityArea) {
                result.getQuantities().add(IfcDecQuantityArea.getInstance((IfcClientQuantityArea) quantity));
            }
            if (quantity instanceof IfcClientQuantityLength) {
                result.getQuantities().add(IfcDecQuantityLength.getInstance((IfcClientQuantityLength) quantity));
            }

        }
        return result;

    }

    public static IfcClientElementQuantity getClientInstance(IfcDecElementQuantity server) {
        IfcClientElementQuantity result = new IfcClientElementQuantity();
        result.setDescription(server.getDescription());
        result.setName(server.getName());
        result.setMethodOfMeasurement(server.getMethodOfMeasurement());
        for (IfcDecPhysicalQuantity quantity : server.getQuantities()) {
            if (quantity instanceof IfcDecQuantityArea) {
                result.getQuantities().add(IfcDecQuantityArea.getClientInstance((IfcDecQuantityArea) quantity));
            }
            if (quantity instanceof IfcDecQuantityLength) {
                result.getQuantities().add(IfcDecQuantityLength.getClientInstance((IfcDecQuantityLength) quantity));
            }
        }


        return result;
    }

    public void onPrePut() {
        quantities_area = new ArrayList<IfcDecQuantityArea>();
        quantities_length = new ArrayList<IfcDecQuantityLength>();
        for ( IfcDecPhysicalQuantity q : getQuantities()) {
            if (q instanceof IfcDecQuantityArea) {
                quantities_area.add((IfcDecQuantityArea) q);
            }
            if (q instanceof IfcDecQuantityLength) {
                quantities_length.add((IfcDecQuantityLength) q);
            }
        }
    }

    public void onPostLoad() {
        getQuantities_area();
        getQuantities_length();
        for (IfcDecQuantityArea area : quantities_area) {
            area.onPostLoad();
            quantities.add(area);
        }
        for (IfcDecQuantityLength area : quantities_length) {
            area.onPostLoad();
            quantities.add(area);
        }
    }

    public String getIfcString(String quantitiesChain) {
        String guid = GuidCompressor.getNewIfcGloballyUniqueId();
        String ownerHistory = "*";
        String name = (getName() == null || getName().isEmpty()) ? "*" : getName();
        String description = (getDescription() == null || getDescription().isEmpty()) ? "*" : getDescription();

        String methodOfMeasurement = "*";

        String quantities = quantitiesChain;


        //adding IFCELEMENTQUANTITY
        return String.format(Constants.IFCELEMENTQUANTITY, guid, ownerHistory, name, description, methodOfMeasurement, quantities);

    }


    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        return null;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return null;
    }
}









