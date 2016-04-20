package mantistbt.appmanager;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import model.MantisProject;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class SoapHelper {

    private AppManager app;

    public SoapHelper(AppManager app) {
        this.app = app;
    }

    public Set<MantisProject> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost:808/mantisbt-1.2.19/api/soap/mantisconnect.php"));
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");

        return Arrays.asList(projects).stream().map(projectData ->
                new MantisProject().withId(projectData.getId().intValue()).withName(projectData.getName()))
                .collect(Collectors.toSet());

    }
}
