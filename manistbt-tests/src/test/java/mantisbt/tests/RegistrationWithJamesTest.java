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


public class RegistrationWithJamesTest extends TestBase {


    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now =System.currentTimeMillis();
        String email = "james@" + now;
        String user = "james" + now;
        String password = "password";

        app.james().createUser(user, password);
        app.userOperations().startRegistration(user, email);

        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);

        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.userOperations().finishUpdate(confirmationLink, password);
        assertTrue(app.newSession().login(user,password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression re = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return re.getText(mailMessage.text);
    }
}
