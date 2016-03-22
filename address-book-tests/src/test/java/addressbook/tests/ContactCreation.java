package addressbook.tests;

/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().newContactPage();
        app.getContactHelper().fillContactForm(contactDummy);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contactDummy);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}


