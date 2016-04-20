package mantisbt.tests;

import model.MantisProject;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTest extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<MantisProject> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (MantisProject project : projects) {
            System.out.println(project.getName());
        }

    }

}
