package com.ufpor.app.shared.ifcdeckernel.decproduct;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.server.service.SpaceServiceImpl;
import com.ufpor.app.shared.datatransfer.IfcSpace;
import com.ufpor.app.shared.ifcclient.product.IfcClientSpace;
import com.ufpor.app.shared.ifcdeckernel.IfcDecElementCompositionEnum;
import com.ufpor.app.shared.ifcdeckernel.IfcDecLengthMeasure;
import com.ufpor.app.shared.ifcdeckernel.type.IfcDecSpaceType;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecSpace extends IfcDecSpatialStructureElement {
    @Persistent(defaultFetchGroup = "true")
    protected IfcDecSpaceTypeEnum predefinedType;
    @Persistent(defaultFetchGroup = "true")
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

        space.setDescription(clienSpace.getDescription() != null ? clienSpace.getDescription() : "");
        space.setName(clienSpace.getName() != null ? clienSpace.getName() : "");

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

    public static IfcDecSpace getInstance(IfcDecSpaceType spaceType) {
        //Getting GUID
        String guid = GuidCompressor.getNewIfcGloballyUniqueId();

        //Getting the User
        User user = UserServiceFactory.getUserService().getCurrentUser();

        IfcDecSpace space = new IfcDecSpace(guid, user);

        IfcSpace.Type hierarchyType = SpaceServiceImpl.getHierarchyType(spaceType.getElementType());

        switch (hierarchyType) {
            case SPACE_COMPLEX:
                space.setCompositionType(IfcDecElementCompositionEnum.COMPLEX);
                break;
            case SPACE_ELEMENT:
                space.setCompositionType(IfcDecElementCompositionEnum.ELEMENT);
                break;
            case SPACE_PARTIAL:
                space.setCompositionType(IfcDecElementCompositionEnum.PARTIAL);
                break;
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

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        return null;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return null;
    }
}
