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

    protected static final AppManager app = new AppManager(System.getProperty("browser", BrowserType.CHROME));

    protected GroupData groupDummy = new GroupData().withName("Zorr0").withHeader("backspinS").withFooter("bod");


    protected ContactData contactA = new ContactData()
            .withName("Ivan").withLastName("Petrov")
            .withAddress("BayArea")
            .withEmail("IvanP@soviet.uk").withEmail2("ip@ip").withEmail3("ipetrov@com.co")
            .withHomePhone("02-02").withMobilePhone("+7 (911) 614-1222").withWorkPhone("15010-00");

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
