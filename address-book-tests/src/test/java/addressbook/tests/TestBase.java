package addressbook.tests;

import addressbook.appmanager.AppManager;
import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/***
 * by rootkoz
 * >(((*>
 */


public class TestBase {

    protected static final AppManager app = new AppManager(BrowserType.FIREFOX);
    protected GroupData groupDummy = new GroupData().withName("Zorr0").withHeader("backspinS").withFooter("bod");
    protected ContactData contactDummy = new ContactData().
            withName("Dummy").withLastName("dummyLN").withCompany("dummyCo").withNickName("nick").withNotes("notes")
            .withEmail("main e-mail@so").withEmail2("   3space   2after  ")
            .withAddress("Zirororo 89-09, \n call baY area  ")
            .withHomePhone("02-  02").withWorkPhone("  +7 (911) 614-1222");

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    protected void createGroupIfNotExist(GroupData group) {
        if (app.group().all().size() == 0) {
            app.group().create(group);
            app.goTo().groupPage();
        }
    }

    protected void createContactIfNotExists(ContactData contactData) {
        if (!app.contact().contactExists()) {
//            app.contact().list().size() == 0) {  // doubtfully
            app.goTo().newContactPage();
            app.contact().createContact(contactData);
            app.goTo().homePage();
        }
    }
}
