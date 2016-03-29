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


public class ContactInfoTest extends TestBase {

    @BeforeMethod
//    public void preConditions() {
//        createContactIfNotExists(contactDummy);
//    }

    @Test
    public void testContactInfo() {
        app.goTo().homePage();

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        app.contact().infoContact(contact.getId());

        String info = app.contact().info();
        System.out.println(info+"\n~~~\n");
        String s = mergedInfo(contactInfoFromEditForm);
        System.out.println(s);
        assertThat(info, equalTo(mergedInfo(contactInfoFromEditForm)));
    }
//    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
//}
//
    private String mergedInfo(ContactData info) {
        return Arrays.asList(info.getName(),info.getLastName(),
                info.getHomePhone(), info.getMobilePhone(), info.getWorkPhone(),
                info.getEmail(),info.getEmail2(), info.getEmail3())
                .stream().filter((s -> ! s.equals("")))
                .map(ContactEmailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }
//
//    public static String cleaned(String email){
//        return email.replaceAll("\\s+", " ").replaceAll(" $","").replaceAll("^ ","");
//    }
//
    public static String cleaned(String address) {
        return address.replaceAll(" +", " ").replaceAll(" +\n", "\n").replaceAll("\n ", "\n").replaceAll(" $", "");
    }

}
