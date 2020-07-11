package API;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class HTTPMethods {

    protected ValidatableResponse get(String uri, Map<String,String> queryParams){
        ValidatableResponse response = given()
                .queryParams(queryParams)
                .header("authority","d.joinhoney.com")
                .header("content-type","application/json")
                .when()
                .get(uri)
                .then();
        return response;
    }


}
