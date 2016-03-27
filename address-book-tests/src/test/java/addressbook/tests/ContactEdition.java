package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactEdition extends TestBase {

    @BeforeMethod
    public void preConditions() {
        createContactIfNotExists(contactDummy);
    }

    @Test
    public void testContactEdition() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        app.contact().modify(index + 2, contactDummy);  // cause of table structure
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();

        ContactData contactData = new ContactData(before.get(index).getId(), "ED557ITED-", "e-lastname", "e-Contact-nick", "e- schi", "e-notes");
        before.remove(index);
        before.add(contactData);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
