import com.ufpor.app.client.NotLoggedInException;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;
import com.ufpor.app.shared.ifcdeckernel.type.IfcDecSpaceType;

/**
 * Created by Ehsan Barekati on 3/30/15.
 */
public class TestSpaceType extends BaseTest {


    public void testFullTest() {
        IfcClientProject project = TestProject.getNewTestProject();
        IfcClientSpaceType spaceType = getNewSpaceType();

        String projectKey = newProjectSaveTest(project);
        String spaceTypeKey = saveNewSpaceTypeTest(projectKey, spaceType);

        IfcDecSpaceType decSpaceType = spaceTypeValuesTest(spaceTypeKey);
        valueMatchTest(decSpaceType, spaceType);

        ifcStringTest(projectKey);
    }

    private void ifcStringTest(String projectKey) {
        String ifc = null;
        try {
            ifc = getService().getIfcString(projectKey, true);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        assertNotNull(ifc);

        for (int i = 1 ; i < 90 ; i++) {
            if (ifc.contains("#" + i + "=")) {
                System.out.print(i + " does exist!");
            } else {
                break;
            }
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

    public static IfcClientSpaceType getNewSpaceType() {
        IfcClientSpaceType result = new IfcClientSpaceType();
        result.setName("Type");
        result.setLongName("Long Type");
        result.setPredefinedType(IfcClientSpaceType.IfcSpaceTypeEnum.EXTERNAL);
        result.setDescription("Description");

        TestSpaceTypeBuilder.attachCommonProperties(result);
        TestSpaceTypeBuilder.attachConstraints(result);
        return result;
    }

    public String newProjectSaveTest(IfcClientProject clientProject) {
        String key = null;
        try {
            key = getService().addProjectForId(clientProject, true);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        assertNotNull(key);
        System.out.print("Key is: " + key + "\n");

        return key;
    }

    public String saveNewSpaceTypeTest(String projectKey, IfcClientSpaceType spaceType) {
        String spaceKey = null;
        System.out.print("\n  testSaveNewSpaceType()  \n");
        EnvironmentServiceImpl service = getService();
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
            spaceType = getService().getSpaceTypeByKey(spaceKey);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        assertNotNull(spaceType);

        return spaceType;
    }


}
