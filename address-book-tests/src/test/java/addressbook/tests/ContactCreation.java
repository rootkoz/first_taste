package addressbook.tests;

/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withName(split[0]).withLastName(split[1]).withMobilePhone(split[2])});
            line = reader.readLine();
        }
        return list.iterator();

    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        Contacts before = app.contact().all();
        app.goTo().newContactPage();
        File photo = new File("src/test/resources/33small.png");
        app.contact().createContact(contact.withPhoto(photo));
        app.goTo().homePage();

        Assert.assertEquals(app.contact().count(), before.size() + 1);

        Contacts after = app.contact().all();
        assertThat(after, equalTo
                (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testBadContactCreation() {
        Contacts before = app.contact().all();

        app.goTo().newContactPage();
        app.contact().createContact(contactB.withName("100562'"));
        app.goTo().homePage();

        Assert.assertEquals(app.contact().count(), before.size());

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }


}


