package com.ufpor.app.shared.ifcdeckernel.type;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.shared.ifcclient.IfcClientElementQuantity;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySet;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySetDefinition;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecElementQuantity;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySet;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinition;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecSpaceType extends IfcDecSpatialStructureElementType {
    @Persistent
    private IfcClientSpaceType.IfcSpaceTypeEnum predefinedType;
    @Persistent
    private String longName;

    public IfcClientSpaceType.IfcSpaceTypeEnum getPredefinedType() {
        return predefinedType;
    }

    public void setPredefinedType(IfcClientSpaceType.IfcSpaceTypeEnum predefinedType) {
        this.predefinedType = predefinedType;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public static IfcDecSpaceType getInstance(IfcClientSpaceType client) {
        //Getting GUID
        String guid = GuidCompressor.getNewIfcGloballyUniqueId();

        //Getting the User
        User user = UserServiceFactory.getUserService().getCurrentUser();

        IfcDecSpaceType type = new IfcDecSpaceType();

        type.setName(client.getName());
        type.setDescription(client.getDescription());
        type.setLongName(client.getLongName());
        type.setPredefinedType(client.getPredefinedType());

        for (IfcClientPropertySetDefinition property : client.getProperties()) {
            IfcDecPropertySetDefinition prop = null;
            if (property instanceof IfcClientPropertySet) {
                prop = IfcDecPropertySet.getInstance((IfcClientPropertySet) property);
            }
            if (property instanceof IfcClientElementQuantity) {
                prop = IfcDecElementQuantity.getInstance((IfcClientElementQuantity) property);
            }

            type.addPropert(prop);
        }

        return type;
    }
}
