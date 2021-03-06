package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactHelper extends HelperBase {


    public Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, Boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("notes"), contactData.getNotes());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            } else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
        }


    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void updateContact() {
        click(By.name("update"));
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        accept();
    }

    private String addContactToFirstGroup() {
        String group = wd.findElement(By.xpath("//div[4]/select/option[1]")).getText();
        
        click(By.name("add"));
        return group;
    }

    public void addToGroup(ContactData contact) {
        selectContactById(contact.getId());
        contact.setAddedToGroup(addContactToFirstGroup());

        contactCache = null;
    }


    public void removeFromGroup(ContactData contact) {
        selectContactById(contact.getId());
        click(By.name("remove"));
    }


    public void editContact(int id) {
        click(By.cssSelector("[href='edit.php?id=" + id + "'"));
    }

    public boolean contactExists() {
        return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        editContact(contact.getId());
        fillContactForm(contact, false);
        updateContact();
        contactCache = null;
    }

    public void createNew(ContactData contact) {
        fillContactForm(contact, false);
        submitContactCreation();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
    }



    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//tr[@name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> contactBasic = element.findElements(By.tagName("td"));
            String name = contactBasic.get(2).getText();
            String lastName = contactBasic.get(1).getText();
            String allPhones = contactBasic.get(5).getText();
            String allEmails = contactBasic.get(4).getText();
            String address = contactBasic.get(3).getText();


            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            ContactData contact = new ContactData().withId(id).withName(name).withLastName(lastName)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails)
                    .withAddress(address);
            contacts.add(contact);
        }
        return contacts;
    }


    public int count() {
        return wd.findElements(By.xpath("//tr[@name='entry']")).size();
    }

    public String[] getContactDetails(int id) {
        click(By.cssSelector("[href='view.php?id=" + id + "'"));
        WebElement element = wd.findElement(By.xpath("//div[@id='content']"));
        return element.getText().split("\n");
    }

    public ContactData infoFromDetails(int index) {

        String[] info = getContactDetails(index);

        String name = info[0].split(" ")[0];
        String lastName = info[0].split(" ")[1];
        String address = info[1];
        String homePhone = info[3].replaceAll("H: ", "");
        String mobilePhone = info[4].replaceAll("M: ", "");
        String workPhone = info[5].replaceAll("W: ", "");
        String email = info[7].split(" ")[0];
        String email2 = info[8].split(" ")[0];
        String email3 = info[9].split(" ")[0];

        return new ContactData().withName(name).withLastName(lastName)
                .withAddress(address)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail(email).withEmail2(email2).withEmail3(email3);

    }

    public ContactData infoFromEditForm(ContactData contact) {

        editContact(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");

        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");

        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        String address = wd.findElement(By.name("address")).getText();

        wd.navigate().back();
        return new ContactData().withName(name).withLastName(lastName).
                withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withAddress(address);
    }

}
