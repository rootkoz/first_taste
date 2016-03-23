package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactEdition extends TestBase {
    @Test
    public void testContactEdition() {
        createContactIfNotExists(contactDummy);

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() + 1); // cause of table structure
        app.getContactHelper().fillContactForm(contactDummy);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        ContactData contactData = new ContactData(before.get(before.size()-1).getId(), "ED557ITED-", "e-lastname", "e-Contact-nick", "e- schi", "e-notes");
        before.remove(before.size() - 1);
        before.add(contactData);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }
}
