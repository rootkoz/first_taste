package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/***
 * by rootkoz
 * >(((*>
 */


public class SessionHelper {
    private FirefoxDriver wd;

    public SessionHelper(FirefoxDriver wd) {
        this.wd = wd;
    }
    public void login(String password, String username) {
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }
}
