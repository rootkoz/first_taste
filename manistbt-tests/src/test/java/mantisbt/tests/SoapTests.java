package mantisbt.tests;

import model.Issue;
import model.MantisProject;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<MantisProject> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (MantisProject project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {


        Set<MantisProject> projects = app.soap().getProjects();
        Issue issue = new Issue()
                .withSum("Test sum")
                .withDescription("Testo is here description")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSum(), created.getSum());
    }
}


