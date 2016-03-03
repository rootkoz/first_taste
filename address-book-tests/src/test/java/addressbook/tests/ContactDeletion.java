package addressbook.tests;

import org.testng.annotations.Test;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactDeletion extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
    }

}
