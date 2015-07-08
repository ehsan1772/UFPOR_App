package com.ufpor.app.shared.ifcdeckernel.type;

import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
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
import java.util.ArrayList;

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
    private String ifcString;

    public IfcDecSpaceType(String guid, User user) {
        super(guid, user);
    }

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
     //   System.out.println("GUID: " + guid);

        //Getting the User
        User user = UserServiceFactory.getUserService().getCurrentUser();

        IfcDecSpaceType type = new IfcDecSpaceType(guid, user);

        type.setName(client.getName());
        type.setDescription(client.getDescription());
        type.setLongName(client.getLongName());
        type.setPredefinedType(client.getPredefinedType());
        type.setElementType(client.getElementType());

        for (IfcClientPropertySetDefinition property : client.getProperties()) {
            IfcDecPropertySetDefinition prop = null;
            if (property instanceof IfcClientPropertySet) {
                prop = IfcDecPropertySet.getInstance((IfcClientPropertySet) property);
            }
            if (property instanceof IfcClientElementQuantity) {
                prop = IfcDecElementQuantity.getInstance((IfcClientElementQuantity) property);
            }

            type.addPropert(prop);
            prop.setRelForIfcRequired(false);
        }

        return type;
    }

    public String getIfcString(String properties) {
        //ROOT
        String guid = getGlobalId().getValue();
        String ownerHistory = "*";
        String name = getName();
        String description = (getDescription() == null || getHasProperties().isEmpty()) ? "*" : getDescription() ;

        //IFCTYPEOBJECT
        String applicableOccurance = (getApplicableOccurance()== null || getApplicableOccurance().isEmpty()) ? "*" : getApplicableOccurance();
        String hasPropertSets = properties;

        //IfcTypeProduct
        String representationalMap = "*";
        String tag = "*";

        //IFCspatialelementtype
        String elementType = (getElementType() == null || getElementType().isEmpty()) ? "*" : getElementType();

        //IfcSpaceType
        String predefinedType = getPredefinedType().name();
        String longName = (getLongName() == null || getLongName().isEmpty()) ? "*" : getLongName();

        return String.format(Constants.IFCSPACETYPE, guid, ownerHistory, name, description, applicableOccurance, hasPropertSets, representationalMap, tag, elementType, predefinedType, longName);
    }

    public static IfcClientSpaceType getClientSpace(IfcDecSpaceType decSpace) {
        IfcClientSpaceType clientSpace = new IfcClientSpaceType();

        clientSpace.setDescription(decSpace.getDescription());
        clientSpace.setName(decSpace.getName());
        clientSpace.setLongName(decSpace.getLongName());
        clientSpace.setElementType(decSpace.getElementType());

        //properties

        IfcClientPropertySetDefinition propertySet = null;
        for(IfcDecPropertySetDefinition property : decSpace.getHasProperties()) {
            if (property instanceof IfcDecPropertySet) {
                propertySet = IfcDecPropertySet.getClientInstance((IfcDecPropertySet) property);
            }

            if (property instanceof IfcDecElementQuantity) {
                propertySet = IfcDecElementQuantity.getClientInstance((IfcDecElementQuantity) property);
            }

            clientSpace.addProperty(propertySet);
        }

        return clientSpace;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        String properties = fileManager.getNumberString(new ArrayList<IfcFileObject>(getHasProperties()));
        return getIfcString(properties);
    }

    @Override
    public void prepareDataForClientIfcDecContext(PostLoadContext context) {
        super.prepareDataForClientIfcDecContext(context);
        predefinedType = getPredefinedType();
        globalId =  getGlobalId();

    }
}
