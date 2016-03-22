package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactDeletion extends TestBase {

    @Test
    public void testContactDeletion() {
        createContactIfNotExists(contactDummy);
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);
    }

}
