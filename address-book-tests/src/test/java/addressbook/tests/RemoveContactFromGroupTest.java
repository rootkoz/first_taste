package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/***
 * by rootkoz
 * >(((*>
 */


public class RemoveContactFromGroupTest extends TestBase {
    @BeforeMethod
    public void preConditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            createGroupIfNotExist(groupDummy);
            app.goTo().homePage();
        }

        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        app.group().selectGroup(group.getName());

        if (!app.contact().contactExists()) {
            createContactIfNotExists(contactA); // group will be selected automatically
        }
    }

    @Test
    public void testContactAdditionToGroup() {

        Contacts contact = app.contact().all();
        ContactData iter = contact.iterator().next();
        app.contact().removeFromGroup(iter);
        app.goTo().homePage();

        Assert.assertFalse(isConnected(iter.getId(), groupIdByName(iter.getAddedToGroup())));
    }

}
