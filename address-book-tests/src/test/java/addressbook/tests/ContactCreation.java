package addressbook.tests;

/***
 * by rootkoz
 * >(((*>
 */


import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreation extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().newContactPage();
        app.getContactHelper().fillContactForm(new ContactData("HHHelpersz-TESTBAS", "Contact-lastname", "Contact-nick", "schi", "Contact-notes"));
        app.getContactHelper().submitContactCreation();
    }

}
