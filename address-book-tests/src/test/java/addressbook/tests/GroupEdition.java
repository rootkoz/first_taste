package addressbook.tests;
/***
 * by rootkoz
 * >(((*>
 */

import org.testng.annotations.Test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class GroupEdition extends TestBase{

    @Test
    public void editGroupTest() {
        navigationHelper.groupPage();
        groupHelper.selectGroup();
        groupHelper.editGroup("EDITED", "e-GROUP", "-321-");
        navigationHelper.groupPage();
    }

}
