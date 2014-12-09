package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.datastore.PostLoad;
import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PrePut;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.shared.ifcclient.*;
import com.ufpor.app.shared.ifcclient.select.IfcClientPropertySetDefinitionSelect;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpatialElement;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecDimensionalExponents;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecElementQuantity;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySet;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecSIUnit;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/17/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecProject extends IfcDecContext {
    @NotPersistent
    protected IfcDecSIUnit AREA_UNIT = getAreaUnit();

    /**
     * this is how we can add properties such as total area. those properties might be associated with constraints
     * it doesn;t need to be persistent because we are adding it to "isDefinedBy" which is store as a
     * BLOB
     */
    // TODO this property represents IfcRelDefinesByProperties
    @NotPersistent
    protected IfcDecElementQuantity generalProperties;

    /**
     * This is the root of the spatial structure and is either a building, a site
     * or a spatial zone. seems like a project can have more than one spatial root
     * if they are independent (for example two different sites)
     * //TODO this relationship should be represented by IfcRelAggregates
     * //TODO right now we store it through an owned one-to-one relationship. it means deleting
     * //TODO the project will delete the spatial structure attached to it. If we want to have an archive of spatial elements. it's not a good idea
     */
    // @Persistent(serialized = "true")
    @NotPersistent
    protected ArrayList<IfcDecSpatialElement> spatialStructureRoot;

    @Persistent
    private ArrayList<IfcDecSpace> spatialStructureRoot_Space;

    public IfcDecProject(String guid, User user) {
        super(guid, user);
    }

    public IfcDecProject() {
    }

    public static IfcDecSIUnit getAreaUnit() {
        IfcDecDimensionalExponents dimension = new IfcDecDimensionalExponents();
        dimension.setLengthExponent(2);

        IfcDecSIUnit unit = new IfcDecSIUnit(IfcClientNamedUnit.IfcUnitEnum.AREAUNIT,
                dimension,
                IfcClientSIUnit.IfcSIUnitName.SQUARE_METRE
        );
        return unit;
    }


    public static IfcDecProject getInstance(IfcClientProject project) {
        //Getting GUID
        String guid = GuidCompressor.getNewIfcGloballyUniqueId();

        //Getting the User
        User user = UserServiceFactory.getUserService().getCurrentUser();

        IfcDecProject result = new IfcDecProject(guid, user);


        for (IfcClientPropertySetDefinitionSelect property : project.getIsDefinedBy()) {
            if (property instanceof IfcClientPropertySet) {
                IfcDecPropertySet propertSet = IfcDecPropertySet.getInstance((IfcClientPropertySet) property);
                result.addDefinedBy(propertSet);
            }
            if (property instanceof IfcClientElementQuantity) {
                IfcDecElementQuantity propertSet = IfcDecElementQuantity.getInstance((IfcClientElementQuantity) property);
                result.addDefinedBy(propertSet);
            }
        }

        if (project.getUnitsInContext() != null) {
            IfcDecUnitAssignment unitAssignment = IfcDecUnitAssignment.getInstance(project.getUnitsInContext());
            result.setUnitsInContext(unitAssignment);
        }

        //TODO complete this list
        result.setLongName(IfcDecLabel.getInstance(project.getLongName()));
        result.setName(project.getName());
        if (project.getDescription() != null) {
            result.setDescription(project.getDescription());
        }


        return result;
    }

    @PrePut(kinds = {"IfcDecProject"})
    public void prepareDataForStore(PutContext context) {
        spatialStructureRoot_Space = new ArrayList<IfcDecSpace>();
        if (spatialStructureRoot != null) {
            for (IfcDecSpatialElement element : spatialStructureRoot) {
                if (element instanceof IfcDecSpace) {
                    spatialStructureRoot_Space.add((IfcDecSpace) element);
                }
            }
        }



    }

    @PostLoad(kinds = {"IfcDecProject"})
    public void prepareDataForClient(PostLoadContext context) {
        spatialStructureRoot = new ArrayList<IfcDecSpatialElement>();
        spatialStructureRoot.addAll(spatialStructureRoot_Space);
    }

    public String getHeader() {
        return null;
    }
}
