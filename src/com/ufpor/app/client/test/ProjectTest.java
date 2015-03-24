package com.ufpor.app.client.test;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.gwt.test.utils.events.Browser;
import com.ufpor.app.client.App;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.MenuBuilder;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.IfcClientNamedUnit;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.IfcClientSIUnit;

import java.util.List;



/**
 * Created by Ehsan Barekati on 3/19/15.
 */
public class ProjectTest extends GWTTestCase {
//    private final LocalServiceTestHelper helper =
//            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
//
//    @Override
//    protected void gwtSetUp() throws Exception {
//        helper.setUp();
//    }
//
//    @Override
//    protected void gwtTearDown() throws Exception {
//        helper.tearDown();
//    }

    @Override
    public String getModuleName() {
        return "com.ufpor.app.app";
    }

//    public void testText() {
//        assertEquals("BAZ", "BAZ");
//    }

//    public void testNewProject() {
//    //    Log.debug("Test Started");
//        IfcClientProject newProject = getNewTestProject();
//        delayTestFinish(5000);
//
//        saveProject2(newProject, new TestListener() {
//            @Override
//            public void onResult(Object object) {
//                assertNotNull(object);
//                finishTest();
//            }
//        });
//
//        assertEquals("BAZ", "BAZ");
//    }

    public void testMenu() {
        //click on the "New" button in the menu
        MenuBuilder.UfporMenu menu = (MenuBuilder.UfporMenu) App.app.getmHomeView().getMenu();
        Browser.click(menu, menu.getNewProject());

        com.google.gwt.user.client.Timer timer = new com.google.gwt.user.client.Timer() {
            public void run() {
                //Assert
                assertTrue(App.app.getPopup().isShowing());

                finishTest();
            }
        };

        delayTestFinish(1000);

        timer.schedule(100);
    }

//    private void saveProject(IfcClientProject project) {
//        // Setup an asynchronous event handler.
//        Timer timer = new Timer() {
//            public void run() {
//                // do some validation logic
//
//                // tell the test system the test is now done
//                finishTest();
//            }
//        };
//
//
//    }

    private void saveProject2(IfcClientProject project, final TestListener listener) {
        EnvironmentService.App.getInstance().addProject(project, true, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();

              //  Assert.fail(caught.getMessage());
            }

            @Override
            public void onSuccess(List<String> result) {
          //      Log.debug("Result is: " +  String.valueOf(result == null));
                String ifc = result.get(0);
                listener.onResult(ifc);

            }
        });
    }

    private IfcClientProject getNewTestProject() {
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

    public static interface TestListener {
        public void onResult(Object object);
    }
}
