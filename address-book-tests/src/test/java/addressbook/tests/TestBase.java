package addressbook.tests;

import addressbook.appmanager.AppManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/***
 * by rootkoz
 * >(((*>
 */


public class TestBase {

    protected final AppManager app = new AppManager(BrowserType.IE);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}
