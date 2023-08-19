package api_day1;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidateJsonResponseBody {

    @Test(description = "API Get response and validate fields or data.")
    @Severity(SeverityLevel.NORMAL)
    public void validateJsonResponseBody() {
        //https://reqres.in/api/users?page=2
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://reqres.in");
        requestSpecification.basePath("/api/users?page=2");

        //create get request
        Response response = requestSpecification.get();
        ResponseBody responseBody = response.getBody();
        String responseBodyAsString = responseBody.prettyPrint();
        Assert.assertEquals(responseBodyAsString.contains("\"total_pages\": 2,"), true, "check if presence of response");
        JsonPath jsonPath = responseBody.jsonPath();
        String email = jsonPath.getJsonObject("data[4].email");
        Assert.assertEquals(email, "charles.morris@reqres.in");

        List<String> si = jsonPath.getJsonObject("data");
        List<String> allEmails = new ArrayList<>();
        for (int i = 0; i < si.size(); i++) {
            allEmails.add(jsonPath.getJsonObject("data[" + i + "].email"));
        }

        allEmails.forEach(e -> System.out.println("email: " + e));
    }

}
