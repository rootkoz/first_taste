package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactEmailTest extends TestBase
{

    @BeforeMethod
    public void preConditions() {
        createContactIfNotExists(contactA);
    }

    @Test
    public void testContactEmail(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        String a =contact.getAllEmails();
        String b = mergeEmails(contactInfoFromEditForm);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData emails) {
        return Arrays.asList(emails.getEmail(),emails.getEmail2(), emails.getEmail3())
                .stream().filter((s -> ! s.equals("")))
                .map(ContactEmailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email){
        return email.replaceAll("\\s+", " ").replaceAll(" $","").replaceAll("^ ","");
    }
}
