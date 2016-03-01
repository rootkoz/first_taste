package addressbook.tests;

/***
 * by rootkoz
 * >(((*>
 */


import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        createNewContact();
        fillContactForm(new ContactData("Contact-TESTBAS", "Contact-lastname", "Contact-nick", "schi", "Contact-notes"));
        submitContactCreation();
    }

}
