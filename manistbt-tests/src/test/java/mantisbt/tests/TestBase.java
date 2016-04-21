package mantisbt.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import mantistbt.appmanager.AppManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


/***
 * by rootkoz
 * >(((*>
 */


public class TestBase {
    protected static final AppManager app = new AppManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
//        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws IOException {
//        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            System.out.println("Ignored because of issue " + issueId + " not Fixed");
            throw new SkipException("Ignored because of issue " + issueId); // message not appeared here
        }
    }

    public boolean isIssueOpen(int index) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnection();
        String resolution = mc.mc_issue_get(app.getProperty("webAdminLogin"), app.getProperty("webAdminPass"), BigInteger.valueOf(index))
                .getResolution().getName();
        return !resolution.equals("fixed");
    }
}
