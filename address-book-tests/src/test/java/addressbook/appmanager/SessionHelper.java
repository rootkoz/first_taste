package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/***
 * by rootkoz
 * >(((*>
 */


public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd) {
        super(wd);
    }
    public void login(String password, String username) {
        type(By.name("user"),username );
        type(By.name("pass"),password );
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
