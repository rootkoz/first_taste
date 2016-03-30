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


public class ContactDetailsInfoTest extends TestBase {

    @BeforeMethod
    public void preConditions() {
        createContactIfNotExists(contactA);
    }

    @Test
    public void testContactInfo() {
        app.goTo().homePage();

        ContactData contact = app.contact().all().iterator().next();

        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetailsPage = app.contact().infoFromDetails(contact.getId());

        assertThat(mergedContactData(contactInfoFromDetailsPage),
                equalTo(mergedContactData(contactInfoFromEditForm)));
    }

    private String mergedContactData(ContactData contactData) {
        return Arrays.asList(contactData.getName(), contactData.getLastName(), contactData.getAddress(),
                contactData.getHomePhone(), contactData.getMobilePhone(), contactData.getWorkPhone(),
                contactData.getEmail(), contactData.getEmail2(), contactData.getEmail3())
                .stream().filter((s -> !s.equals("")))
                .map(ContactDetailsInfoTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s+", " ").replaceAll("[-()]", "").replaceAll("^\\s+", "");
    }
}



