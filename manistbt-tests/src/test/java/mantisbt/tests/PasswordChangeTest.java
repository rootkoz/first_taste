package mantisbt.tests;

import model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/***
 * by rootkoz
 * >(((*>
 */


public class PasswordChangeTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordChange() throws IOException, MessagingException {
        String newPassword = "newPass";
        app.userOperations().adminLogin("administrator", "root");
        String user = app.userOperations().resetUserPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findResetLink(mailMessages);
        app.userOperations().finishUpdate(confirmationLink, newPassword);
        assertTrue(app.newSession().login(user, newPassword));
    }

    private String findResetLink(List<MailMessage> mailMessages) {
        MailMessage mailMessage = mailMessages.get(0);// only one mail ~ to user
        VerbalExpression re = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return re.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
