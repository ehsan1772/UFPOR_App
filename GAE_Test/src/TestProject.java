import com.ufpor.app.client.NotLoggedInException;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.IfcClientNamedUnit;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.IfcClientSIUnit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Ehsan Barekati on 3/24/15.
 */
public class TestProject extends BaseTest {


    public void testSaveProjectNotLogedIn() {
        IfcClientProject clientProject = getNewTestProject();
        EnvironmentServiceImpl service = getEnvironmentService();
        NotLoggedInException error = null;
        try {
            service.addProjectForId(clientProject, false);
            fail("shouldn't get here");
        } catch (NotLoggedInException e) {
            e.printStackTrace();
            error = e;

        }
        assertNotNull(error);
    }

    public void testFullCycle() {
        IfcClientProject clientProject = getNewTestProject();
        String key = newProjectSaveTest(clientProject);
        String ifcString = newProjectRetrieveTest(key);
        ifcString = headerTest(ifcString);
        ifcString = ifcStringBodyTest(ifcString);
        ifcString = ifcStringEndingTest(ifcString);



        assertTrue(ifcString.length() == 0);
    }

    private String ifcStringEndingTest(String ifcString) {
        String expectedFooter = "ENDSEC;" + "(?s).*" +
                "END-ISO-10303-21;";
        ifcString = findAndRemoveMatch(ifcString, expectedFooter);
        return ifcString;
    }

    public String newProjectSaveTest(IfcClientProject clientProject) {

        String key = null;

        try {
            key = getEnvironmentService().addProjectForId(clientProject, true);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
        System.out.print("Key is: " + key + "\n");

        assertNotNull(key);

        return key;
    }

    public String newProjectRetrieveTest(String key) {
        String ifcString = null;
        try {
            ifcString = getEnvironmentService().getIfcString(key, true);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
        assertNotNull(ifcString);
        System.out.print(ifcString);
        return ifcString;
    }

    public String ifcStringBodyTest(String ifcString) {
        // we have to use non-greedy pattern that's why  "?" is added after the "*"
        ifcString = findAndRemoveMatch(ifcString, "#1=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#2=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#3=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#4=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#5=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#6=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#7=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#8=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#9=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#10=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#11=" + "(?s).*?;");
        ifcString = findAndRemoveMatch(ifcString, "#12=" + "(?s).*?;");

        assertTrue(!ifcString.contains("#13="));

        return ifcString;
    }

    private String findAndRemoveMatch(String original, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(original);

        ArrayList<String> array = new ArrayList<>();

        while (m.find()) {
            array.add(m.group());
        }

        assertTrue(array.size() > 0);

        original = original.replace(findShortestString(array), "");
        //removing the empty line created in the previous line
        original = original.replaceAll("(?m)^[ \t]*\r?\n", "");
        return original;
    }

    private String findShortestString(ArrayList<String> arrays) {
        String shortest = null;
        for (String string : arrays) {
            if (shortest == null) {
                shortest = string;
            }
            if (string.length() < shortest.length()) {
                shortest = string;
            }
        }

        return shortest;
    }


    /**
     * Checks the header and eliminates it from the ifc file
     *
     * @param ifcString
     * @return
     */
    private String headerTest(String ifcString) {
        String expectedHeader = "ISO-10303-21;" + "(?s).*" +
                "HEADER;" + "(?s).*" +
                "FILE_DESCRIPTION \\(" + "(?s).*" +
                "\\('TestProject Long'\\)," + "(?s).*" +
                "'2;1'\\);" + "(?s).*" +
                "FILE_NAME \\(" + "(?s).*" +
                "'TestProject.ifc'," + "(?s).*" +
                "\\d{4}/\\d{2}/\\d{2}T\\d{2}:\\d{2}:\\d{2}'," + "(?s).*" +
                "\\('test'\\)," + "(?s).*" +
                "\\('test@test.com'\\)," + "(?s).*" +
                "'UFPOR APP 0.0.1'," + "(?s).*" +
                "'UFPOR DEMO beta'," + "(?s).*" +
                "'test.com'\\);" + "(?s).*" +
                "FILE_SCHEMA \\(\\('IFC4RC4'\\)\\);" + "(?s).*" +
                "ENDSEC;" + "(?s).*" +
                "DATA;";


        Pattern p = Pattern.compile(expectedHeader);
        Matcher m = p.matcher(ifcString);

        assertTrue(m.find());
        ifcString = ifcString.replace(m.group(0), "");
        //removing the empty line created in the previous line
        ifcString = ifcString.replaceAll("(?m)^[ \t]*\r?\n", "");
        return ifcString;
    }


    public static IfcClientProject getNewTestProject() {
        IfcClientProject testProject = new IfcClientProject();
        testProject.setMinArea(200);
        testProject.setMaxArea(400);
        testProject.setName("TestProject");
        testProject.setLongName(new IfcClientLabel("TestProject" + " Long"));
        // testProject.setTotalGrossArea();

        IfcClientSIUnit.IfcSIUnitName unitName1 = IfcClientSIUnit.IfcSIUnitName.valueOf("METRE");
        IfcClientSIUnit lengthUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.LENGTHUNIT, null, unitName1);
        testProject.getUnitsInContext().addUnit(lengthUnit);


        IfcClientSIUnit.IfcSIUnitName unitName2 = IfcClientSIUnit.IfcSIUnitName.valueOf("SQUARE_METRE");
        IfcClientSIUnit areaUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.AREAUNIT, null, unitName2);
        testProject.getUnitsInContext().addUnit(areaUnit);

        IfcClientSIUnit.IfcSIUnitName unitName3 = IfcClientSIUnit.IfcSIUnitName.valueOf("CUBIC_METRE");
        IfcClientSIUnit volumeUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.VOLUMEUNIT, null, unitName3);
        testProject.getUnitsInContext().addUnit(volumeUnit);

        return testProject;

    }
}

