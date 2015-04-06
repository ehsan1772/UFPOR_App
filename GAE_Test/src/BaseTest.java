import com.google.appengine.api.users.User;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.server.service.SpaceServiceImpl;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Created by Ehsan Barekati on 3/30/15.
 */
public class BaseTest extends TestCase {
    protected final static User TestUser = new User("test@test.com", "test.com", "test", "com");
    protected static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");

    protected EnvironmentServiceImpl environmentService;
    protected SpaceServiceImpl spaceService;

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    @Before
    protected void setUp() throws Exception {
        helper.setUp();
    }

    @After
    protected void tearDown() throws Exception {
        helper.tearDown();
    }

    protected EnvironmentServiceImpl getEnvironmentService() {
        if (environmentService == null) {
            environmentService = new EnvironmentServiceImpl();
        }

        return environmentService;
    }

    protected SpaceServiceImpl getSpaceService() {
        if (spaceService == null) {
            spaceService = new SpaceServiceImpl();
        }

        return spaceService;
    }
}
