package api_day1;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DigestAuth {

    @Test(priority = 1, description = "API Get response using Digest authentication")
    @Severity(SeverityLevel.NORMAL)
    public void getResponseByDigestAuth(){
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.baseUri("http://httpbin.org");
        requestSpec.basePath("/digest-auth/undefined/anil/admin");
        Response response = requestSpec.auth().digest("anil","admin").get();
        System.out.println(response.prettyPrint());
        Assert.assertEquals(200,response.getStatusCode());
        System.out.println("ResponseCode:" + response.getHeaders() );
    }
}
