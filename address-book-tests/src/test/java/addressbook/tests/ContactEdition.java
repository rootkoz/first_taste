package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactEdition extends TestBase {
    @Test
    public void testContactModifying(){
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("ED557ITED-TESTBAS", "e-lastname", "e-Contact-nick", "e- schi", "e-notes"));
        app.getContactHelper().updateContact();
    }
}
