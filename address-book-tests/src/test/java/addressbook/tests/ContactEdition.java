package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactEdition extends TestBase {

    @BeforeMethod
    public void preConditions() {
        if (app.db().contacts().size() == 0) {
            createContactIfNotExists(contactA);
        }
    }

    @Test
    public void testContactEdition() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/33small.png");

        ContactData contactData = new ContactData().
                withId(modifiedContact.getId()).withName("EediteD").withLastName("e-Lname").withCompany("e-co").withNickName("e-nick").withNotes("e-notes").withPhoto(photo);


        app.contact().modify(contactData);
        app.goTo().homePage();

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.db().contacts();
//
//        assertThat(after, equalTo
//                (before.without(modifiedContact).withAdded(contactData)));

        verifyContactListOnUI();
    }
}
