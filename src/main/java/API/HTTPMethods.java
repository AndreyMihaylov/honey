package API;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class HTTPMethods {

    protected ValidatableResponse get(String uri, Map<String,String> queryParams){
        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .queryParams(queryParams)
                .header("authority","d.joinhoney.com")
                .header("content-type","application/json")
                .when()
                .get(uri)
                .then();
        return response;
    }


}
