package addressbook.tests;

import addressbook.appmanager.AppManager;
import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
            .withHomePhone("02-02").withMobilePhone("+7 (911) 614-1222").withWorkPhone("15010-00")
            .withPhoto(new File("src/test/resources/33small.png"));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }


    protected void createContactIfNotExists(ContactData contactData) {
        if (!app.contact().contactExists()) {
//            app.contact().list().size() == 0) {  // doubtfully
            app.goTo().newContactPage();
            app.contact().createNew(contactData);
            app.goTo().homePage();
        }
    }


    protected void createGroupIfNotExist(GroupData group) {
        if (app.group().all().size() == 0) {
            app.group().create(group);
            app.goTo().groupPage();
        }
    }

    //
    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info(m.getName() + " started with parameters:" + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestEnd(Method m) {
        logger.info(m.getName() + " stoped");
    }


    public void verifyGroupListOnUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups fromDB = app.db().groups();
            Groups fromUI = app.group().all();
//            assertThat(fromUI, equalTo(fromDB));
            assertThat(fromUI, equalTo(fromDB.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListOnUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts fromDB = app.db().contacts();
            Contacts fromUI = app.contact().all();

            assertThat(fromUI, equalTo(fromDB.stream()
                    .map((c) -> new ContactData().withId(c.getId()).withName(c.getName()).withLastName(c.getLastName()))
                    .collect(Collectors.toSet())));
        }

    }


    public int getGroupIdForContact(int contact_id) {
        Connection conn;
        int group_id = 0;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" + "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, group_id FROM `address_in_groups` where id = " + Integer.toString(contact_id));
            rs.first();
            group_id = rs.getInt("group_id");
            rs.close();
            st.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return group_id;
    }

    public String groupNameById(int group_id){
        Connection conn;
        String group_name="";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" + "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT group_name FROM `group_list` where group_id = " + Integer.toString(group_id));
            rs.first();
            group_name = rs.getString("group_name");
            rs.close();
            st.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return group_name;
    }


    public int groupIdByName(String group_name){
        Connection conn;
        int group_id=0;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" + "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT group_id FROM `group_list` where group_name = '" + group_name+"'");
            rs.first();
            group_id = rs.getInt("group_id");
            rs.close();
            st.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return group_id;
    }

    public Boolean isConnected(int contact_id, int group_id){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" + "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `address_in_groups` where group_id = " + group_id +" AND id = "+contact_id);

            if (rs.first()){
                rs.close();
                st.close();
                conn.close();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return false;
    }

}
