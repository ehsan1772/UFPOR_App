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
        IfcClientSpaceType spaceType = getNewSpaceType("name!", "description", "longname");
        IfcClientSpaceType spaceType2 = getNewSpaceType("name2!", "description2", "longname2");
        IfcClientSpaceType spaceType3 = getNewSpaceType("name3!", "description3", "longname3");
        IfcClientSpaceType spaceType4 = getNewSpaceType("name4!", "description4", "longname4");

        String projectKey = newProjectSaveTest(project);
        String spaceTypeKey = saveNewSpaceTypeTest(projectKey, spaceType);
        String spaceTypeKey2 = saveNewSpaceTypeTest(projectKey, spaceType2);
        String spaceTypeKey3 = saveNewSpaceTypeTest(projectKey, spaceType3);
        String spaceTypeKey4 = saveNewSpaceTypeTest(projectKey, spaceType4);

        IfcDecSpaceType decSpaceType = spaceTypeValuesTest(spaceTypeKey);


        valueMatchTest(decSpaceType, spaceType);

        ifcStringTest(projectKey);
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
        System.out.print("Key is: " + key + "\n");

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
