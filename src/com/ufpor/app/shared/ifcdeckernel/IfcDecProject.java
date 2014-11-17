package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.IfcClientPropertySet;
import com.ufpor.app.shared.ifcclient.select.IfcClientPropertySetDefinitionSelect;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpatialElement;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecDimensionalExponents;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecNamedUnit;
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
    protected final static IfcDecSIUnit AREA_UNIT = getAreaUnit();
    /**
     * this is how we can add properties such as total area. those properties might be associated with constraints
     * it doesn;t need to be persistent because we are adding it to "isDefinedBy" which is store as a
     * BLOB
     */
    // TODO this property represents IfcRelDefinesByProperties
    @NotPersistent
    protected IfcDecPropertySet generalProperties;

    /**
     * This is the root of the spatial structure and is either a building, a site
     * or a spatial zone. seems like a project can have more than one spatial root
     * if they are independent (for example two different sites)
     * //TODO this relationship should be represented by IfcRelAggregates
     * //TODO right now we store it through an owned one-to-one relationship. it means deleting
     * //TODO the project will delete the spatial structure attached to it. If we want to have an archive of spatial elements. it's not a good idea
     */
    @Persistent
    protected ArrayList<IfcDecSpatialElement> spatialStructureRoot;

    public static IfcDecSIUnit getAreaUnit() {
        IfcDecDimensionalExponents dimension = new IfcDecDimensionalExponents();
        dimension.setLengthExponent(2);

        IfcDecSIUnit unit = new IfcDecSIUnit(IfcDecNamedUnit.IfcDecUnitEnum.AREAUNIT,
                dimension,
                IfcDecSIUnit.IfcDecSIUnitName.SQUARE_METRE
        );
        return unit;
    }

    public static IfcDecProject getInstance(IfcClientProject project) {
        int i = 0;
        IfcDecProject result = new IfcDecProject();
        for (IfcClientPropertySetDefinitionSelect property : project.getIsDefinedBy())
        if (property instanceof IfcClientPropertySet) {
            IfcDecPropertySet propertSet = IfcDecPropertySet.getInstance((IfcClientPropertySet) property);
            result.addDefinedBy(propertSet);
        }

        //TODO complete this list
        result.setLongName(IfcDecLabel.getInstance(project.getLongName()));
        result.setName(IfcDecLabel.getInstance(project.getName()));
        result.setDescription(IfcDecText.getInstance(project.getDescription()));


        return result;
    }
}
