package addressbook.tests;

import addressbook.appmanager.AppManager;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/***
 * by rootkoz
 * >(((*>
 */


public class TestBase extends AppManager {

    FirefoxDriver wd;

    @BeforeMethod
    public void setUp() throws Exception {
        init();
    }

    @AfterMethod
    public void tearDown() {
        stop();
    }



}
