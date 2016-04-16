package mantisbt.tests;

import org.testng.annotations.Test;

/***
 * by rootkoz
 * >(((*>
 */


public class RegistrationTest extends TestBase {

    @Test
    public void testRegistration(){
        app.registration().start("user", "password");
    }
}
