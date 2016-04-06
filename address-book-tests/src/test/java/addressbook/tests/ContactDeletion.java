package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactDeletion extends TestBase {

    @BeforeMethod
    public void preConditions() {
        createContactIfNotExists(contactA);
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deleteContact = before.iterator().next();

        app.contact().delete(deleteContact);
        app.goTo().homePage();

        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deleteContact)));
        verifyContactListOnUI();
    }
}
