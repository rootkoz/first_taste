package mantistbt.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/***
 * by rootkoz
 * >(((*>
 */


public class AppManager {
    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private UsersHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;


    public AppManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }


    public HTTPSession newSession() {
        return new HTTPSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public UsersHelper userOperations() {
        if (registrationHelper == null) {
            registrationHelper = new UsersHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james(){
        if(jamesHelper==null){
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
//            wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}
