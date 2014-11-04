package com.ufpor.app.shared.ifcdeckernel.decproduct;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.shared.ifcclient.decproduct.IfcClientSpace;
import com.ufpor.app.shared.ifcdeckernel.IfcDecLabel;
import com.ufpor.app.shared.ifcdeckernel.IfcDecLengthMeasure;
import com.ufpor.app.shared.ifcdeckernel.IfcDecText;

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
        String guid = GuidCompressor.getNewIfcGloballyUniqueId();
        User user = UserServiceFactory.getUserService().getCurrentUser();
        IfcDecSpace space = new IfcDecSpace(guid, user);
        space.setDescription(new IfcDecText(clienSpace.getDescription().getValue()));
        space.setName(new IfcDecLabel(clienSpace.getName().getValue()));

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
