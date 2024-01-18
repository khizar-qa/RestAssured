package examples;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class test2 {

    private static String requestBody = "{\n" +
            "  \"title\": \"foo\",\n" +
            "  \"body\": \"baz\",\n" +
            "  \"userId\": \"31\",\n" +
            "  \"id\": \"41\" \n}";

    private static String putbody = "{\n" +
            "  \"title\": \"foo3\",\n" +
            "  \"body\": \"baz\",\n" +
            "  \"userId\": \"21\",\n" +
            "  \"id\": \"1\" \n}";

    private static String patchbody = "{\n" +
            "  \"title\": \"foo\",\n" +
            "  \"body\": \"baz4\",\n" +
            "  \"userId\": \"31\",\n" +
            "  \"id\": \"1\" \n}";


    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "Get Scenario", priority = 1)
    public void getRequest() {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts")
                .then().
                assertThat().
                statusCode(200);
        response.log().all();
    }

    @Test(description = "Get with QueryParams Scenario", priority = 2)
    public void getRequestWithQueryParam() {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .param("id", "31")
                .when()
                .get("/todos")
                .then().
                assertThat().
                statusCode(200);//.body("records.size()",greaterThan(0));
        response.log().all();
    }

    @Test (description = "Post Scenario", priority = 3)
    public void postRequest() {
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/todos")
                .then().assertThat().
                statusCode(201).body("size()", greaterThan(0));
        response.log().all();
    }

    @Test (description = "Put Scenario", priority = 4)
    public void putRequest() {
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(putbody)
                .when()
                .put("/todos/3")
                .then().assertThat().
                statusCode(200).body("title",equalTo("foo3"));
        response.log().all();

    }

    @Test (description = "Patch Scenario", priority = 5)
    public void patchRequest() {
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(patchbody)
                .when()
                .patch("/posts/1")
                .then().assertThat().
                statusCode(200).body("body",equalTo("baz4"));
        response.log().all();
    }
    @Test (description = "Delete Scenario", priority = 6)
    public void deleteRequest() {
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/posts/1")
                .then().assertThat().
                statusCode(200);
        response.log().all();

    }


}
