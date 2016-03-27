package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("notes"), contactData.getNotes());
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

    public void editContact(int id) {
        click(By.cssSelector("[href='edit.php?id=" + id + "'"));
    }


    public boolean contactExists() {
        return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
    }

    public void create(ContactData contactDummy) {
        fillContactForm(contactDummy);
        submitContactCreation();
    }

    public void modify(ContactData contact) {
        editContact(contact.getId());
        fillContactForm(contact);
        updateContact();
    }


    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
    }


    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//tr[@name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> contactBasic = element.findElements(By.tagName("td"));
            String name = contactBasic.get(2).getText();
            String lastName = contactBasic.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            ContactData contact = new ContactData().withId(id).withName(name).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

}
