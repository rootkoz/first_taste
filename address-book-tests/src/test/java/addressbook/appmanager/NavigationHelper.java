package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/***
 * by rootkoz
 * >(((*>
 */


public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void groupPage() {
        wd.findElement(By.linkText("groups")).click();
    }


}
