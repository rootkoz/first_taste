package addressbook.tests;

/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();

        app.goTo().newContactPage();
        app.contact().createContact(contactDummy.withName("RightNAME"));
        app.goTo().homePage();

        Assert.assertEquals(app.contact().count(), before.size() + 1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo
                (before.withAdded(contactDummy.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        Contacts before = app.contact().all();

        app.goTo().newContactPage();
        app.contact().createContact(contactDummy.withName("100562'"));
        app.goTo().homePage();

        Assert.assertEquals(app.contact().count(), before.size());

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }


}


