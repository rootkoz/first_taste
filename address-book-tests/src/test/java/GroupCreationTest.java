import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;


public class GroupCreationTest {
        FirefoxDriver wd;

@BeforeMethod
public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }

@Test
public void CreateGroup() {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
        wd.findElement(By.linkText("groups")).click();
        wd.findElement(By.name("new")).click();
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys("G3");
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select//option[1]")).isSelected()) {
        wd.findElement(By.xpath("//div[@id='content']/form/select//option[1]")).click();
        }
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys("G3-header");
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys("G3-footer");
        wd.findElement(By.name("submit")).click();
        wd.findElement(By.linkText("group page")).click();
        }

@AfterMethod
public void tearDown() {
        wd.quit();
        }

public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
        wd.switchTo().alert();
        return true;
        } catch (NoAlertPresentException e) {
        return false;
        }
        }
        }
