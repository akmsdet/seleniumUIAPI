package api_day1;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiAuth {
    @Test(priority = 1, description = "API Get response using basic authentication")
    @Severity(SeverityLevel.NORMAL)
    public void requestByBasicAuth() {
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.baseUri("http://postman-echo.com");
        requestSpec.basePath("/basic-auth");

         //By Default basic auth is non-preemptive

        Response response = requestSpec.auth().preemptive().basic("postman", "password").get();
        ResponseBody responseBody = response.getBody();
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.statusLine());
        System.out.println(responseBody.prettyPrint());

    }
}
