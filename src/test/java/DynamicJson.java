import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    // with hard codded json
//    @Test
    public void addBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payload.AddBook())
                .when()
                .post("Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .asString();

        JsonPath jsonPath = ReUsableMethods.rawToJson(response);
        String id = jsonPath.get("ID");
        System.out.println(id);
        System.out.println("AddBook id" + id);
    }

    // with dynamic values
//    @Test
    public void addBookDynamic() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payload.AddBookDynamic("asdsaf", "6464"))
                .when()
                .post("Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .asString();

        JsonPath jsonPath = ReUsableMethods.rawToJson(response);
        String id = jsonPath.get("ID");
        System.out.println(id);
        System.out.println("AddBook id" + id);

        // delete a created book by id
        given().log().all().header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"ID\": \"" + id + "\"\n" +
                        "}")
                .when()
                .delete("Library/DeleteBook.php")
                .then()
                .assertThat().statusCode(200);
    }

    // with dynamic with Data Provider
    @Test(dataProvider = "BooksData")
    public void addBookWithDataProvider(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payload.AddBookDynamic(isbn, aisle))
                .when()
                .post("Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .asString();

        JsonPath jsonPath = ReUsableMethods.rawToJson(response);
        String id = jsonPath.get("ID");
        System.out.println(id);

        // delete a created book
        given().log().all().header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"ID\": \"" + id + "\"\n" +
                        "}")
                .when()
                .delete("Library/DeleteBook.php")
                .then()
                .assertThat().statusCode(200);
    }
    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        // array = collection of elements
        // multidimensional array = collection of arrays
        return new Object[][]{{"qweraa", "1231"}, {"zxcvzzx", "0909"}, {"asdfff", "5655"}};

    }
}
