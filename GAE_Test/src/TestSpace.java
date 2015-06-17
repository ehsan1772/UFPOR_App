import com.ufpor.app.client.NotLoggedInException;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.shared.datatransfer.IfcSpace;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.type.IfcDecSpaceType;

/**
 * Created by Ehsan Barekati on 3/30/15.
 */
public class TestSpace extends BaseTest {


    public void testFullTest() {
        IfcClientProject project = TestProject.getNewTestProject();
        String projectKey = newProjectSaveTest(project);

        IfcDecProject proj0 = getEnvironmentService().getProjectByKey(projectKey);

        IfcClientSpaceType spaceType = getNewSpaceType("name!", "description", "longname");
        String spaceTypeKey = saveNewSpaceTypeTest(projectKey, spaceType);

        String spaceKey1 = addSpaceToTheProject(null, projectKey, spaceTypeKey);
        String spaceKey2 = addSpaceToTheProject(null, projectKey, spaceTypeKey);


     //   testIfChildrenHasAmember(childrenKey);

        IfcDecProject proj = getEnvironmentService().getProjectByKey(projectKey);

        testIfSpaceIsPersisted(spaceKey1, projectKey, 0);

//        String spaceKey2 = addSpaceToTheProject(null, projectKey, spaceTypeKey);
        testIfSpaceIsPersisted(spaceKey2, projectKey, 1);
//
//        testIfChileSpaceIsAddedToTheProject(projectKey, spaceKey2);
//
//        String spaceKey3 = addSpaceToTheProject(null, projectKey, spaceTypeKey);
//        testIfSpaceIsPersisted(spaceKey3, projectKey, 2);
//
//        testIfChileSpaceIsAddedToTheProject(projectKey, spaceKey3);

        IfcDecSpaceType decSpaceType = spaceTypeValuesTest(spaceTypeKey);

        valueMatchTest(decSpaceType, spaceType);

        ifcStringTest(projectKey);
    }

    private void testIfChildrenHasAmember(String childrenKey) {
        int size = getSpaceService().getIfcRelAggregateByKey(childrenKey);
        assertEquals(size, 2);
    }

    private void testIfChileSpaceIsAddedToTheProject(String projectKey, String spaceKey) {
        IfcDecProject proj = getEnvironmentService().getProjectByKey(projectKey);
        assertNotNull(proj.getChildSpaces());
    }

    private void testIfSpaceIsPersisted(String spaceKey, String projectKey, int index) {
        IfcDecSpace space = getSpaceService().getSpaceByKey(spaceKey);
        assertNotNull(space);

        IfcDecProject project = getEnvironmentService().getProjectByKey(projectKey);

        assertEquals(space.getKey(), project.getChildSpaces().getList().get(index).getKey());
    }

    private String addSpaceToTheProject(String spaceType, String projectKey, String spaceTypeKey) {
        String spaceKey = getSpaceService().addSpaceInstance(IfcSpace.Type.PROJECT, projectKey, spaceTypeKey);
        assertNotNull(spaceKey);
        assertTrue(spaceKey.length() > 0);

        System.out.println("Space Key = " + spaceKey);
        return spaceKey;
    }

    private void ifcStringTest(String projectKey) {
        String ifc = null;
        try {
            ifc = getEnvironmentService().getIfcString(projectKey, true);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        assertNotNull(ifc);

        for (int i = 1 ; i < 1000 ; i++) {
            if (!ifc.contains("#" + i + "=")) {
                System.out.print(i-1 + " is the last number!");
                break;
            }
        }

        String[] ifcs = ifc.split("\n");

        for (String fc : ifcs) {
            System.out.println(fc);
        }

        System.out.print(ifc);
    }

    private void valueMatchTest(IfcDecSpaceType decSpaceType, IfcClientSpaceType spaceType) {
        assertEquals(decSpaceType.getName(), spaceType.getName());
        assertEquals(decSpaceType.getDescription(), spaceType.getDescription());
        assertEquals(decSpaceType.getLongName(), spaceType.getLongName());
        assertEquals(decSpaceType.getPredefinedType(), spaceType.getPredefinedType());

        assertNotNull(decSpaceType.getHasProperties());
        assertEquals(decSpaceType.getHasProperties().size(), spaceType.getProperties().size());
    }

    public static IfcClientSpaceType getNewSpaceType(String name, String description, String longName) {
        IfcClientSpaceType result = new IfcClientSpaceType();
        result.setName(name);
        result.setLongName(longName);
        result.setPredefinedType(IfcClientSpaceType.IfcSpaceTypeEnum.EXTERNAL);
        result.setDescription(description);
        result.setElementType(IfcSpace.Type.SPACE_COMPLEX.toString());

        TestSpaceTypeBuilder.attachCommonProperties(result);
        TestSpaceTypeBuilder.attachConstraints(result);
        return result;
    }

    public String newProjectSaveTest(IfcClientProject clientProject) {
        String key = null;
        try {
            key = getEnvironmentService().addProjectForId(clientProject, true);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        assertNotNull(key);

        return key;
    }

    public String saveNewSpaceTypeTest(String projectKey, IfcClientSpaceType spaceType) {
        String spaceKey = null;
        System.out.print("\n  testSaveNewSpaceType()  \n");
        EnvironmentServiceImpl service = getEnvironmentService();
        try {
            assertNotNull(projectKey);
            spaceKey = service.addSpaceType(spaceType, projectKey, true);
        } catch (NotLoggedInException e) {
            assertNotNull(null);
            e.printStackTrace();
        }
        assertNotNull(spaceKey);

        return spaceKey;
    }

    public IfcDecSpaceType spaceTypeValuesTest(String spaceKey) {
        IfcDecSpaceType spaceType = null;
        try {
            spaceType = getEnvironmentService().getSpaceTypeByKey(spaceKey);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        assertNotNull(spaceType);

        return spaceType;
    }


}
