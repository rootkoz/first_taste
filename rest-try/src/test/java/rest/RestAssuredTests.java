package rest;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;


public class RestAssuredTests extends TestBase {
    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        int id = 10;
        skipIfNotFixed(id);
        String issueState = getIssueStateById(id);

        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("restTry subJo0").withDescription("Mam-paP");

        int issueId = createIssue(newIssue);

        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
