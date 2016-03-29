package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("notes"), contactData.getNotes());
        type(By.name("address"),contactData.getAddress());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("work"),contactData.getWorkPhone());
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

    public void infoContact(int id) {
        click(By.cssSelector("[href='view.php?id=" + id + "'"));
    }

    public boolean contactExists() {
        return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        editContact(contact.getId());
        fillContactForm(contact);
        updateContact();
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

    public String info() {
        WebElement element = wd.findElement(By.xpath("//div[@id='content']"));
        String info = element.getText();
        return info;
    }

    public int count() {
        return wd.findElements(By.xpath("//tr[@name='entry']")).size();
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
