package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

/***
 * by rootkoz
 * >(((*>
 */


public class HelperBase {

    protected FirefoxDriver wd;

    public HelperBase(FirefoxDriver wd) {
        this.wd = wd;
    }

    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
