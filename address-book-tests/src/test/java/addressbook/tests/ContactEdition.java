package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactEdition extends TestBase {
    @Test
    public void testContactEdition(){
        createContactIfNotExists();
        app.getContactHelper().selectContact(16);
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("ED557ITED-", "e-lastname", "e-Contact-nick", "e- schi", "e-notes"));
        app.getContactHelper().updateContact();
    }

}
