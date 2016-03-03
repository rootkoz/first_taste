package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactHelper extends HelperBase{

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
      }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstname() );
        type(By.name("lastname"),contactData.getLastname() );
        type(By.name("nickname"),contactData.getNickname() );
        type(By.name("company"),contactData.getCompany() );
        type(By.name("notes"),contactData.getNotes() );
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void updateContact(){
        click(By.name("update"));
    }

    public void selectContact(){
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact(){
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        accept();
    }

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }
}
