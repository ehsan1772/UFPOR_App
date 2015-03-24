import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Ehsan Barekati on 3/20/15.
 */
@GwtModule("com.ufpor.app.app")
//@RunWith(MyGwtRunner.class)
public class GwtTestSample extends GwtTest {

//    private App app;

//    @Before
//    public void setupGwtTestSample() {
//        app = new App();
//        app.onModuleLoad();
//
//        // Some pre-assertions
//        Assert.assertFalse(false);
//      //  Assert.assertEquals("", app.errorLabel.getText());
//    }

//    @Test
//    public void checkClickOnSendMoreThan4chars() {
//        //click on the "New" button in the menu
//        MenuBuilder.UfporMenu menu = (MenuBuilder.UfporMenu) app.getmHomeView().getMenu();
//        Browser.click(menu, menu.getNewProject());
//
//        //Assert
//        Assert.assertTrue(app.getPopup().isShowing());
//
//    }

    @Test
    public void test() {

        Assert.assertTrue(true);

    }
}
