package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
        ContactData editContact = before.iterator().next();

        app.contact().modify(editContact);
        app.goTo().homePage();

        Set<ContactData> after = app.contact().all();
        ContactData contactData = contactDummy.withId(editContact.getId());
        before.remove(editContact);
        before.add(contactData);

        Assert.assertEquals(before, after);
    }
}
