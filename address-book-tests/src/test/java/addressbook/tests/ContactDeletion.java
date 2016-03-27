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


public class ContactDeletion extends TestBase {

    @BeforeMethod
    public void preConditions() {
        createContactIfNotExists(contactDummy);
    }

    @Test
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deleteContact = before.iterator().next();

        app.contact().delete(deleteContact);
        app.goTo().homePage();

        Set<ContactData> after = app.contact().all();


        before.remove(deleteContact);
        Assert.assertEquals(after, before);
    }

}
