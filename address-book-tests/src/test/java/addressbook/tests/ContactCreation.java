package addressbook.tests;

/***
 * by rootkoz
 * >(((*>
 */

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.Groups;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String line = reader.readLine();
            String json = "";
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromXML() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String line = reader.readLine();
            String xml = "";
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();

        app.goTo().newContactPage();
        File photo = new File("src/test/resources/33small.png");

        app.contact().createNew(contact.withPhoto(photo));//.inGroup(groups.iterator().next()));
        app.goTo().homePage();

        Assert.assertEquals(app.contact().count(), before.size() + 1);

        Contacts after = app.db().contacts();

        assertThat(after, equalTo
                (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

        verifyContactListOnUI();
    }

    @Test(enabled = false)
    public void testBadContactCreation() {
        Contacts before = app.contact().all();

        app.goTo().newContactPage();
        app.contact().createContact(contactA.withName("100562'"));
        app.goTo().homePage();

        Assert.assertEquals(app.contact().count(), before.size());

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));

        verifyContactListOnUI();
    }

}


