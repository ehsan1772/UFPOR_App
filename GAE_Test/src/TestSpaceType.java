import com.ufpor.app.client.NotLoggedInException;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;
import com.ufpor.app.shared.ifcdeckernel.type.IfcDecSpaceType;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 3/30/15.
 */
public class TestSpaceType extends BaseTest {


    public void testFullTest() {
        IfcClientProject project = TestProject.getNewTestProject();
        IfcClientSpaceType spaceType = getNewSpaceType("name!", "description", "longname");

        String projectKey = newProjectSaveTest(project);
        String spaceTypeKey = saveNewSpaceTypeTest(projectKey, spaceType);

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

        ArrayList<TestUtils.LineItem> lines = TestUtils.getLines(ifc);

        testLines(lines);

        System.out.print(ifc);
    }

    private void testLines(ArrayList<TestUtils.LineItem> lines) {
        //getting the project
        TestUtils.LineItem project = TestUtils.findByName("IFCPROJECT", lines);

        assertNotNull(project);
        lines.remove(project);
        assertEquals(project.properties.size(), 9);

        //finding IFCUNITASSIGNMENT
        String unitsNumber = project.properties.get(project.properties.size() - 1);
        TestUtils.LineItem units = TestUtils.findByNumber(unitsNumber, lines);
        assertNotNull(units);
        lines.remove(units);
        assertEquals(units.properties.size(), 3);

        for (String numberString : units.properties) {
            TestUtils.LineItem unit = TestUtils.findByNumber(numberString, lines);
            assertNotNull(unit);
            lines.remove(unit);
        }

        //finding IFCRELDECLARES
        TestUtils.LineItem ifcreldeclares = TestUtils.findByName("IFCRELDECLARES", lines);
        assertNotNull(ifcreldeclares);
        lines.remove(ifcreldeclares);

        //finding IFCRELDEFINESBYPROPERTIES for project
        ArrayList<TestUtils.LineItem> ifcreldefinesByProperties = TestUtils.findAllByName("IFCRELDEFINESBYPROPERTIES", lines);
        assertEquals(ifcreldefinesByProperties.size(), 1);

        for (TestUtils.LineItem ifcreldefinesByProperty : ifcreldefinesByProperties) {
            if (ifcreldefinesByProperty.properties.get(4).contains(String.valueOf(project.number))) {
                String elementQuantityNumber = TestUtils.findNumbers(ifcreldefinesByProperty.properties.get(5)).get(0);
                TestUtils.LineItem elementQuantity = TestUtils.findByNumber(elementQuantityNumber, lines);

                String elementQuantityAreaNumber = TestUtils.findNumbers(elementQuantity.properties.get(5)).get(0);
                TestUtils.LineItem elementQuantityArea = TestUtils.findByNumber(elementQuantityAreaNumber, lines);

                //finding the constraints
                testConstraint(elementQuantityArea, lines);

                lines.remove(ifcreldefinesByProperty);
                lines.remove(elementQuantity);
                lines.remove(elementQuantityArea);


            }
        }


        //declaring spaceType
        TestUtils.LineItem spaceType = TestUtils.findByNumber(ifcreldeclares.properties.get(ifcreldeclares.properties.size() - 1), lines);
        assertEquals(spaceType.type, "IFCSPACETYPE");
        lines.remove(spaceType);

        ArrayList<String> numbers = TestUtils.findNumbers(spaceType.properties.get(5));

        assertEquals(numbers.size(), 2);

        TestUtils.LineItem propertySet = TestUtils.findByNumber(numbers.get(0), lines);
        TestUtils.LineItem elementQuantity = TestUtils.findByNumber(numbers.get(1), lines);

        assertEquals(propertySet.type, "IFCPROPERTYSET");
        assertEquals(elementQuantity.type, "IFCELEMENTQUANTITY");

        testProperties(propertySet, lines);
        lines.remove(propertySet);
        testQuantities(elementQuantity, lines);

        assertEquals(lines.size(), 1);
        lines.remove(elementQuantity);

        assertEquals(lines.size(), 0);
        int i = 0;


    }

    private void testConstraint(TestUtils.LineItem item, ArrayList<TestUtils.LineItem> lines) {
        //finding all the constraint relationships
        ArrayList<TestUtils.LineItem> constraints = TestUtils.findAllByName("IFCRESOURCECONSTRAINTRELATIONSHIP", lines);

        TestUtils.LineItem constraint = getConstraint(constraints, item);

        assertNotNull(constraint);
        constraints.remove(constraint);
        TestUtils.LineItem objective = TestUtils.findByNumber(TestUtils.findNumbers(constraint.properties.get(2)).get(0), lines);
        assertEquals(objective.type, "IFCOBJECTIVE");
        lines.remove(objective);

        ArrayList<String> metrics = TestUtils.findNumbers(objective.properties.get(7));
        for (String metric : metrics) {
            TestUtils.LineItem met = TestUtils.findByNumber(metric, lines);
            assertNotNull(met);
            lines.remove(met);
        }

        lines.remove(constraint);
    }

    private void testQuantities(TestUtils.LineItem elementQuantity, ArrayList<TestUtils.LineItem> lines) {
        //finding all the constraint relationships
        ArrayList<TestUtils.LineItem> constraints = TestUtils.findAllByName("IFCRESOURCECONSTRAINTRELATIONSHIP", lines);

        ArrayList<String> nums = TestUtils.findNumbers(elementQuantity.properties.get(5));

        for (String num : nums) {
            TestUtils.LineItem property = TestUtils.findByNumber(num, lines);
            lines.remove(property);
            assertNotNull(property);
            TestUtils.LineItem constraint = getConstraint(constraints, property);
            assertNotNull(constraint);
            constraints.remove(constraint);
            TestUtils.LineItem objective = TestUtils.findByNumber(TestUtils.findNumbers(constraint.properties.get(2)).get(0), lines);
            assertEquals(objective.type, "IFCOBJECTIVE");
            lines.remove(objective);
            ArrayList<String> metrics = TestUtils.findNumbers(objective.properties.get(7));
            for (String metric : metrics) {
                TestUtils.LineItem met = TestUtils.findByNumber(metric, lines);
                assertNotNull(met);
                lines.remove(met);
            }

            lines.remove(constraint);
            lines.remove(property);
        }


        int i = 0;

    }

    private void testProperties(TestUtils.LineItem propertySet, ArrayList<TestUtils.LineItem> lines) {
        ArrayList<String> nums = TestUtils.findNumbers(propertySet.properties.get(4));
        assertEquals(nums.size(), 7);
        for (String num : nums) {
            TestUtils.LineItem property = TestUtils.findByNumber(num, lines);
            assertNotNull(property);
            lines.remove(property);
        }
    }

    private TestUtils.LineItem getConstraint(ArrayList<TestUtils.LineItem> constraints, TestUtils.LineItem property) {
        for (TestUtils.LineItem constraint : constraints) {
            if (Integer.valueOf(TestUtils.findNumbers(constraint.properties.get(3)).get(0)) == property.number) {
                return constraint;
            }
        }

        return null;
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
