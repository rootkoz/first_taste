package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/***
 * by rootkoz
 * >(((*>
 */


public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void preConditions() {
        if (app.db().contacts().size() == 0) {
            createContactIfNotExists(contactA);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            createGroupIfNotExist(groupDummy);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAdditionToGroup() {
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();

        app.contact().addToGroup(contact);

        Assert.assertTrue(isConnected(contact.getId(), groupIdByName(contact.getAddedToGroup())));
    }
}
