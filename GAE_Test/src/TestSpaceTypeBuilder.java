import com.ufpor.app.shared.ifcclient.IfcClientPropertySetDefinition;
import com.ufpor.app.shared.ifcclient.property.SpaceBaseQuantitiesBuilder;
import com.ufpor.app.shared.ifcclient.property.SpaceCommonPropertySetBuilder;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;

/**
 * Created by Ehsan Barekati on 3/30/15.
 */
public class TestSpaceTypeBuilder {

    private static final String REFERENCE_ID = "1234";
    private static final double NET_PLANNED_AREA = 1200;
    private static final double GROSS_PLANNED_AREA = 1400;
    private static final double MAX_CEILING_HEIGHT = 12;
    private static final double MIN_CEILING_HEIGHT = 10;
    private static final double MAX_NET_CEILING_AREA = 1400;
    private static final double MIN_NET_CEILING_AREA = 1200;
    private static final double MAX_GROSS_WALL_AREA = 2800;
    private static final double Min_GROSS_WALL_AREA = 2400;
    private static final double MAX_NET_WALL_AREA = 2600;
    private static final double MIN_NET_WALL_AREA = 2200;
    private static final double MAX_GROSS_FLOOR_AREA = 2800;
    private static final double MIN_GROSS_FLOOR_AREA = 2400;
    private static final double MAX_NET_FLOOR_AREA = 2600;
    private static final double MIN_NET_FLOOR_AREA = 2200;
    private static final double MAX_FINISH_CEILING_HEIGHT = 12;
    private static final double MIN_FINISH_CEILING_HEIGHT = 10;
    private static final double MAX_FINISH_FLOOR_HEIGHT = 12;
    private static final double MIN_FINISH_FLOOR_HEIGHT = 10;
    private static final double MAX_GROSS_PLANNED_AREA = 2800;
    private static final double MIN_GROSS_PLANNED_AREA = 2400;
    private static final double MAX_NET_PLANNED_AREA = 2600;
    private static final double MIN_NET_PLANNED_AREA = 2300;
    private static final double MAX_GROSS_PERIMETER = 400;
    private static final double MIN_GROSS_PERIMETER = 300;
    private static final double MAX_NET_PERIMETER = 500;
    private static final double MIN_NET_PERIMETER = 400;

    private static IfcClientSpaceType spaceType;
    private static SpaceCommonPropertySetBuilder pBuilder;
    private static SpaceBaseQuantitiesBuilder constraintBuilder;


    public static IfcClientSpaceType getSpaceType() {
        spaceType = new IfcClientSpaceType();
        SpaceBaseQuantitiesBuilder constraintBuilder = new SpaceBaseQuantitiesBuilder();



        spaceType.addPropertySet(pBuilder.getProperties());
        spaceType.addPropertySet((constraintBuilder.getIfcClientElementQuantity()));
        return spaceType;

    }

    public static void attachCommonProperties(IfcClientSpaceType spaceType) {
        spaceType.addPropertySet(getSpaceTypeProperties());
    }

    public static void attachConstraints(IfcClientSpaceType spaceType) {
        spaceType.addPropertySet((getConstraints()));
    }

    private static IfcClientPropertySetDefinition getSpaceTypeProperties() {
        pBuilder = new SpaceCommonPropertySetBuilder();

        pBuilder.setReferenceId(REFERENCE_ID);
        pBuilder.setIsPublic(true);
        pBuilder.setGrossPlannedArea(GROSS_PLANNED_AREA);
        pBuilder.setNetPlannedArea(NET_PLANNED_AREA);
        pBuilder.setIsPubliclyAccessible(false);
        pBuilder.setIsHandicapAccessible(true);

        pBuilder.setIsExternal(false);
        pBuilder.setGrossPlannedAreaMax(MAX_GROSS_PLANNED_AREA);
        pBuilder.setGrossPlannedAreaMin(MIN_GROSS_PLANNED_AREA);
        pBuilder.setIsExternalConstraint(true);
        pBuilder.setIsHandicaAccessiblePropertyConstraint(true);


        pBuilder.setIsPublicConstraint(true);
        pBuilder.setNetPlannedAreaMax(MAX_NET_PLANNED_AREA);
        pBuilder.setNetPlannedAreaMin(MIN_NET_PLANNED_AREA);
        pBuilder.setIsPubliclyAccessibleConstraint(true);

        return pBuilder.getProperties();
    }

    private static IfcClientPropertySetDefinition getConstraints() {
        constraintBuilder = new SpaceBaseQuantitiesBuilder();

        constraintBuilder.setMaxFinishCeilingHeight(MAX_CEILING_HEIGHT);
        constraintBuilder.setMinFinishCeilingHeight(MIN_CEILING_HEIGHT);

        constraintBuilder.setMaxNetCeilingArea(MAX_NET_CEILING_AREA);
        constraintBuilder.setMinNetCeilingArea(MIN_NET_CEILING_AREA);

        constraintBuilder.setMaxGrossWallArea(MAX_GROSS_WALL_AREA);
        constraintBuilder.setMinGrossWallArea(Min_GROSS_WALL_AREA);

        constraintBuilder.setMaxNetWallArea(MAX_NET_WALL_AREA);
        constraintBuilder.setMinNetWallArea(MIN_NET_WALL_AREA);

        constraintBuilder.setMaxGrossFloorArea(MAX_GROSS_FLOOR_AREA);
        constraintBuilder.setMinGrossFloorArea(MIN_GROSS_FLOOR_AREA);

        constraintBuilder.setMaxNetFloorArea(MAX_NET_FLOOR_AREA);
        constraintBuilder.setMinNetFloorArea(MIN_NET_FLOOR_AREA);

        constraintBuilder.setMaxFinishCeilingHeight(MAX_FINISH_CEILING_HEIGHT);
        constraintBuilder.setMinFinishCeilingHeight(MIN_FINISH_CEILING_HEIGHT);

        constraintBuilder.setMaxFinishFloorHeight(MAX_FINISH_FLOOR_HEIGHT);
        constraintBuilder.setMinFinishFloorHeight(MIN_FINISH_FLOOR_HEIGHT);

        constraintBuilder.setMaxGrossPerimeter(MAX_GROSS_PERIMETER);
        constraintBuilder.setMinGrossPerimeter(MIN_GROSS_PERIMETER);

        constraintBuilder.setMaxNetPerimeter(MAX_NET_PERIMETER);
        constraintBuilder.setMinNetPerimeter(MIN_NET_PERIMETER);


        return constraintBuilder.getIfcClientElementQuantity();
    }
}
