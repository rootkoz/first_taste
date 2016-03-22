package addressbook.tests;

import addressbook.appmanager.AppManager;
import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/***
 * by rootkoz
 * >(((*>
 */


public class TestBase {

    protected final AppManager app = new AppManager(BrowserType.FIREFOX);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

    protected void createGroupIfNotExist(GroupData group) {
        if (!app.getGroupHelper().groupExists()) {
            app.getGroupHelper().createGroup(group);
            app.getNavigationHelper().groupPage();
        }
    }

    protected void createContactIfNotExists() {
        if (!app.getContactHelper().contactExists()) {
            app.getNavigationHelper().newContactPage();
            app.getContactHelper().createContact(new ContactData("4 Edition/Deletion", null, "11", null, "33"));
            app.getNavigationHelper().homePage();
        }
    }
}
