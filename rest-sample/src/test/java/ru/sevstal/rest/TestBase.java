package ru.sevstal.rest;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.apache.commons.exec.Executor;
import org.omg.CORBA.Request;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestBase {

    public boolean isIssueOpen(int issueId) throws IOException {

        String status = getIssueStatus(issueId);
        return status.equals("Open");

    }

    public void skipIfNotFixed(int issueId) throws IOException {

        if (!isIssueOpen(issueId)) {
            System.out.println("Ignored because of issue " + issueId);
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private Executor getExecutor() {
        return Executor.newInstance().
                auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    private String getIssueStatus(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId))).
                returnContent().asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        String s =issue.getAsJsonObject().get("state_name").getAsString();
        System.out.println("   " + s);
        return s;

    }
}