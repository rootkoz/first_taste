package addressbook.tests;

import addressbook.appmanager.AppManager;
import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.lang.reflect.*;
import java.util.Arrays;


/***
 * by rootkoz
 * >(((*>
 */


public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

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
//
    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info(m.getName()+ " started with parameters:"+ Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestEnd(Method m){
        logger.info(m.getName()+ " stoped");
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
