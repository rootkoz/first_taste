package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
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
        createContactIfNotExists(contactA);
    }

    @Test
    public void testContactEdition() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();

        ContactData contactData = new ContactData().
                withId(modifiedContact.getId()).withName("EediteD").withLastName("e-Lname").withCompany("e-co").withNickName("e-nick").withNotes("e-notes");

        app.contact().modify(contactData);
        app.goTo().homePage();

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.contact().all();

        assertThat(after, equalTo
                (before.without(modifiedContact).withAdded(contactData)));
    }
}
