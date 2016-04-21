package mantistbt.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import model.Issue;
import model.MantisProject;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
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
        MantisConnectPortType mc = getMantisConnection();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");

        return Arrays.asList(projects).stream().map(projectData ->
                new MantisProject().withId(projectData.getId().intValue()).withName(projectData.getName()))
                .collect(Collectors.toSet());

    }

    public MantisConnectPortType getMantisConnection() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("mantisConnection")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        String adminUser = app.getProperty("webAdminLogin");
        String password = app.getProperty("webAdminPass");

        MantisConnectPortType mc = getMantisConnection();
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSum());
        issueData.setDescription(issue.getDescription());

        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));

        String[] categories = mc.mc_project_get_categories(adminUser, password, BigInteger.valueOf(issue.getProject().getId()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(adminUser, password, issueData);
        IssueData createdIssueData = mc.mc_issue_get(adminUser, password, issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withSum(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
                .withProject(new MantisProject().withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));


    }
}
