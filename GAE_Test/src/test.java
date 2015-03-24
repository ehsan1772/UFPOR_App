import com.google.appengine.api.users.User;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ufpor.app.client.NotLoggedInException;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.IfcClientNamedUnit;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.IfcClientSIUnit;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.List;

/**
 * Created by Ehsan Barekati on 3/24/15.
 */
public class test extends TestCase {
    private final static User TestUser = new User("test@test.com", "test.com", "test", "com");

    private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    protected void setUp() throws Exception {
        helper.setUp();
    }

    @After
    protected void tearDown() throws Exception {
        helper.tearDown();
    }


    public void testNewProject() {
        IfcClientProject clientProject = getNewTestProject();

        IfcDecProject decProject = persist(clientProject);

        assertNotNull(decProject);
        assertEquals(clientProject.getName(), decProject.getName());
          assertEquals(clientProject.getName(), decProject.getDescription());



        assertEquals("BAZ", "BAZ");
    }


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

    public IfcDecProject persist(IfcClientProject ifcClientProject) {
        IfcDecProject project;

        project = IfcDecProject.getInstance(ifcClientProject, TestUser);

        String ifcFile = null;

        project.prepareDataForStore(null);
        project.prepareDataForStoreIfcDecContext(null);

        PersistenceManager pm = PMF.getPersistenceManager();
        try {
            pm.makePersistent(project);
            return project;
        } catch (Exception exception) {
            return null;
            //  LOG.log(Level.SEVERE, exception.getMessage());
        }
        finally {
            pm.close();
        }
    }

    public void saveProject3(IfcClientProject project, final TestListener listener) {
        EnvironmentServiceImpl service = new EnvironmentServiceImpl();
        try {
            List<String> result = service.addProject(project, true);
            String ifc = result.get(0);
            listener.onResult(ifc);
        } catch (NotLoggedInException e)  {
            e.printStackTrace();
        }

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

