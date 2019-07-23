package ru.sevstal.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1721);
        Set<Issue> oldIssues = getIssue();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("test issue description");
        int issueId = createIssue(newIssue);
        System.out.println("Created issue id = "+ issueId);
        Set<Issue> newIssues = getIssue();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(oldIssues, newIssues);
    }

    private int createIssue(Issue newIssue)  {

        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();

        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();

    }

    private Set<Issue> getIssue()  {

        String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }
}