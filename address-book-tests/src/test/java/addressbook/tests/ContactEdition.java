package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts before = app.contact().all();
        ContactData editContact = before.iterator().next();

        app.contact().modify(editContact);
        app.goTo().homePage();

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.contact().all();
        ContactData contactData = contactDummy.withId(editContact.getId());

        assertThat(after, equalTo(before.without(editContact).withAdded(contactData)));
    }
}
