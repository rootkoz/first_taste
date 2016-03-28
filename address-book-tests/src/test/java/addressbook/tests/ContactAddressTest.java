package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactAddressTest extends TestBase {
    @Test

    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergeAddress(ContactData address) {
        return Arrays.asList(address.getAddress())
                .stream().filter((s -> !s.equals("")))
                .map(ContactEmailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String address) {
        return address.replaceAll("\\s+", " ");
    }
}
