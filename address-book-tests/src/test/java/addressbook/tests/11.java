//import org.junit.After;
//import org.junit.Before;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import java.util.concurrent.TimeUnit;
//import java.util.Date;
//import java.io.File;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.*;
//import static org.openqa.selenium.OutputType.*;
//
//public class 11 {
//    FirefoxDriver wd;
//
//    @Before
//    public void setUp() throws Exception {
//        wd = new FirefoxDriver();
//        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void 11() {
//        wd.get("http://localhost/addressbook/");
//        wd.findElement(By.name("user")).click();
//        wd.findElement(By.name("user")).clear();
//        wd.findElement(By.name("user")).sendKeys("admin");
//        wd.findElement(By.name("user")).click();
//        wd.findElement(By.name("user")).sendKeys("\n");
//        wd.findElement(By.name("pass")).click();
//        wd.findElement(By.name("pass")).clear();
//        wd.findElement(By.name("pass")).sendKeys("secret");
//        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
//        wd.findElement(By.xpath("//table[@id='maintable']//td[.='ddd']")).click();
//        wd.findElement(By.xpath("//table[@id='maintable']//td[.='ddd']")).click();
//        wd.findElement(By.xpath("//table[@id='maintable']//td[.='Her']")).click();
//        wd.findElement(By.xpath("//table[@id='maintable']//td[.='w']")).click();
//        if (!wd.findElement(By.id("60")).isSelected()) {
//            wd.findElement(By.id("60")).click();
//        }
//    }
//
//    @After
//    public void tearDown() {
//        wd.quit();
//    }
//
//    public static boolean isAlertPresent(FirefoxDriver wd) {
//        try {
//            wd.switchTo().alert();
//            return true;
//        } catch (NoAlertPresentException e) {
//            return false;
//        }
//    }
//}
