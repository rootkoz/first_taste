package addressbook.tests;

/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();

        app.goTo().newContactPage();
        app.contact().create(contactDummy);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();

        Assert.assertEquals(after.size(), before.size() + 1);

        contactDummy.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contactDummy);

        Assert.assertEquals(after, before);
    }


}


