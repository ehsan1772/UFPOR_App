import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Ehsan Barekati on 3/27/15.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestProject.class, TestSpaceType.class
})

public class TestSuite {
    private static String key;

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        TestSuite.key = key;
    }
}
