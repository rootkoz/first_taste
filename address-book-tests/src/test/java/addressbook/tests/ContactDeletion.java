package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();

        before.remove(index);
        Assert.assertEquals(after, before);
    }


}
