package api_day1;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRequest {
    @Test(priority = 1, description = "API Get response")
    @Severity(SeverityLevel.NORMAL)
    public void getResponseStatus() {
        Response response = RestAssured.given().get("https://reqres.in/api/users?page=2");
        System.out.println(response.getBody().prettyPrint());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2, description = "API Get response using params")
    @Severity(SeverityLevel.NORMAL)
    public void getResponseUsingParams() {
        RequestSpecification requestSpec= RestAssured.given();
        requestSpec.baseUri("https://reqres.in");
        requestSpec.basePath("/api/users");
        requestSpec.queryParam("page",2);
        Response response = requestSpec.get();
        System.out.println(response.getBody().prettyPrint());
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 3, description = "API Get response using Multiple params")
    @Severity(SeverityLevel.NORMAL)
    public void getResponseUsingMultiParams() {
        RequestSpecification requestSpec= RestAssured.given();
        requestSpec.baseUri("https://reqres.in");
        requestSpec.basePath("/api/users");
        requestSpec.queryParam("page",2).queryParam("id",12);

        Response response = requestSpec.get();
        System.out.println(response.getBody().prettyPrint());
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
