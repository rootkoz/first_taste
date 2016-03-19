package addressbook.tests;

/***
 * by rootkoz
 * >(((*>
 */


import addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreation extends TestBase{

    @Test
    public void testContactCreation() {

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().newContactPage();
        app.getContactHelper().fillContactForm(new ContactData("111-TESTBAS", "Contact-lastname", "Contact-nick", "schi", "Contact-notes"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();


    }


//    Assert.assertEquals(after.size(), before.size()+1);
//
//    before.add(group);
//
//    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);
//    Assert.assertEquals(before, after);
}


