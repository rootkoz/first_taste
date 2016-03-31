package addressbook.tests;

/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();

        app.goTo().newContactPage();
        File photo = new File("src/test/resources/33small.png");
        app.contact().createContact(contactA.withPhoto(photo));
        app.goTo().homePage();

        Assert.assertEquals(app.contact().count(), before.size() + 1);

        Contacts after = app.contact().all();
        assertThat(after, equalTo
                (before.withAdded(contactA.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        Contacts before = app.contact().all();

        app.goTo().newContactPage();
        app.contact().createContact(contactB.withName("100562'"));
        app.goTo().homePage();

        Assert.assertEquals(app.contact().count(), before.size());

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }


}


