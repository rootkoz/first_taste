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
    protected GroupData groupDummy = new GroupData("Zorr0", "headSpins", "toe");
    protected ContactData contactDummy = new ContactData("ED557ITED-", "e-lastname", "e-Contact-nick", "e- schi", "e-notes");

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

    protected void createContactIfNotExists(ContactData contactData) {
        if (!app.getContactHelper().contactExists()) {
            app.getNavigationHelper().newContactPage();
            app.getContactHelper().createContact(contactData);
            app.getNavigationHelper().homePage();
        }
    }
}
