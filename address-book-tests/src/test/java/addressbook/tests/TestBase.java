package addressbook.tests;

import addressbook.appmanager.AppManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/***
 * by rootkoz
 * >(((*>
 */


public class TestBase extends AppManager {

    @BeforeMethod
    public void setUp() throws Exception {
        init();
    }

    @AfterMethod
    public void tearDown() {
        stop();
    }


}
