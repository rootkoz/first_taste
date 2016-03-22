package addressbook.tests;

import addressbook.model.ContactData;
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
        ContactData contactDummy = new ContactData("ED557ITED-", "e-lastname", "e-Contact-nick", "e- schi", "e-notes");

        createContactIfNotExists();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(contactDummy);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.add(contactDummy);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
    }
}
