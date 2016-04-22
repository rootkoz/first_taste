package rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    public Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    boolean isIssueOpen(int issueId) throws IOException {
        String state = getIssueStateById(issueId);
        if (state.equals("Resolved") || state.equals("Closed")) return false;
        return true;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            System.out.println("Ignored because of issue " + issueId);
            throw new SkipException("This message consumed somehow..");
        }
    }

    public String getIssueStateById(int id) throws IOException {
        String json = RestAssured.get("http://demo.bugify.com/api/issues/" + id + ".json").asString();
        JsonElement issues = new JsonParser().parse(json).getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        return issue.getAsJsonObject().get("state_name").getAsString();
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();

        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
