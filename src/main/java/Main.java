import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Main {
    public static void main(String[] args) throws IOException {

        // validate if Add Place API is working as expected
        // Given - All inputs details,
        // When - Submit the API resource http method,
        // Then - Validate the response

        RestAssured.baseURI="https://rahulshettyacademy.com";

 /*       // validate status code
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -55.383494,\n" +
                        "    \"lng\": 55.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Backyard house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, Detelina\",\n" +
                        "  \"types\": [\n" +
                        "    \"running shoes\",\n" +
                        "    \"beer shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"https://rahulshettyacademy.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}\n")
                .when().log().all().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200);*/

/*        // validate body scope match app
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.AddPlace())
                .when().log().all().post("maps/api/place/add/json")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)");
        */

/*        // Add place -> Update Place with new Address -> Get Place to validate if new Address is present in the response
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); // for passing Json
        String placeId = js.getString("place_id");
        System.out.println(placeId);

        // Update Place
        String newAddress = "70 Summer walk, USA";
        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Context-Type", "application/json")
                .body("{\n" +
                "\"place_id\":\"" + placeId + "\",\n" +
                "\"address\":\"" + newAddress +"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        // Get Place
        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = jsonPath.getString("address");

        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress, newAddress);
    }*/


        // Add place -> Update Place with new Address -> Get Place to validate if new Address is present in the response
        // convert content to String -> content of file can be converted into Byte data to String

        String jsonString = new String(Files.readAllBytes(Paths.get("C:\\Users\\StiliyanPopov\\OneDrive - Coherent Solutions\\Documents\\Udemy courses\\Rest API Testing(Automation) from Scratch-Rest Assured Java\\addPlace.json")));
        System.out.println("This is converted json into the string\n" + jsonString);
        System.out.println();
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when().post("maps/api/place/add/json")
                .then().assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); // for passing Json
        String placeId = js.getString("place_id");
        System.out.println(placeId);

        // Update Place
        String newAddress = "70 Summer walk, USA";
        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Context-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        // Get Place
        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = jsonPath.getString("address");

        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress, newAddress);
    }
}