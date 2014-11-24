package com.ufpor.app.shared.ifcdeckernel.decproduct;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.shared.ifcclient.product.IfcClientSpace;
import com.ufpor.app.shared.ifcdeckernel.IfcDecElementCompositionEnum;
import com.ufpor.app.shared.ifcdeckernel.IfcDecLengthMeasure;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecSpace extends IfcDecSpatialStructureElement {
    @Persistent
    protected IfcDecSpaceTypeEnum predefinedType;
    @Persistent
    protected IfcDecLengthMeasure elevationWithFlooring;

    public IfcDecSpace(String GUID, User user) {
        super(GUID, user);
    }

    public static IfcDecSpace getInstance(IfcClientSpace clienSpace) {
        //Getting GUID
        String guid = GuidCompressor.getNewIfcGloballyUniqueId();

        //Getting the User
        User user = UserServiceFactory.getUserService().getCurrentUser();

        IfcDecSpace space = new IfcDecSpace(guid, user);

        //setting the attributes

        space.setDescription(clienSpace.getDescription() != null ? clienSpace.getDescription().getValue() : "");
        space.setName(clienSpace.getName() != null ? clienSpace.getName().getValue() : "");

        for (IfcDecSpaceTypeEnum typeEnum : IfcDecSpaceTypeEnum.values()) {
            if(typeEnum.toString().equals(clienSpace.getPredefinedType().toString())) {
                space.setPredefinedType(typeEnum);
            }
        }


        for (IfcDecElementCompositionEnum typeEnum : IfcDecElementCompositionEnum.values()) {
            if(typeEnum.toString().equals(clienSpace.getCompositionType().toString())) {
                space.setCompositionType(typeEnum);
            }
        }

        return space;
    }

    protected IfcDecSpace() {
        super();
    }

    public IfcDecSpaceTypeEnum getPredefinedType() {
        return predefinedType;
    }

    public void setPredefinedType(IfcDecSpaceTypeEnum predefinedType) {
        this.predefinedType = predefinedType;
    }

    public IfcDecLengthMeasure getElevationWithFlooring() {
        return elevationWithFlooring;
    }

    public void setElevationWithFlooring(IfcDecLengthMeasure elevationWithFlooring) {
        this.elevationWithFlooring = elevationWithFlooring;
    }
}
