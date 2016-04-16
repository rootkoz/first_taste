package mantisbt.tests;

import mantistbt.appmanager.HTTPSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

/*** by rootkoz
 *     >(((*>
 */

public class LoginTest extends TestBase {
    @Test
    public void testLogin() throws IOException {
        HTTPSession session= app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLogeedAs("administrator"));
    }
}
