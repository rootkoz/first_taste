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
        app.contact().create(contactDummy);
        app.goTo().homePage();
        Contacts after = app.contact().all();

        Assert.assertEquals(after.size(), before.size() + 1);

        assertThat(after, equalTo
                (before.withAdded(contactDummy.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


}


