package examples;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class mytests {

    @Test
    public void getBooking(){
        String endpoint = "https://restful-booker.herokuapp.com/booking";
        ValidatableResponse response =
                given().
                when().
                       get(endpoint).
                then();
        response.log().body();
    }

    @Test
    public void getBookingbyID(){
        String endpoint = "https://restful-booker.herokuapp.com/booking/{bookingid}";
        ValidatableResponse response =
                given().
                        pathParam("bookingid","993").
                        when().
                        get(endpoint).
                        then();
        response.log().body();
    }

    @Test
    public void createBooking() {
        String endpoint = "https://restful-booker.herokuapp.com/booking";
        String body = "{" +
                "\"firstname\": \"Sally\",\n" +
                "\"lastname\": \"Potter\",\n" +
                "\"totalprice\": 111,\n" +
                "\"depositpaid\": true,\n" +
                "\"additionalneeds\": \"Lunch\",\n" +
                "\"bookingdates\": { \n" +
                "\"checkin\": \"2023-02-23\", \n" +
                "\"checkout\": \"2023-10-23\" \n" +
                  "} \n" +
                 "}";
        ValidatableResponse response = given().
                header("Content-Type","application/json").
                body(body).
                when().
                        post(endpoint).
                then();
        response.log().body();
    }

    @Test
    public void updateBooking() {
        String endpoint = "https://restful-booker.herokuapp.com/booking/{bookingid}";
        String body = "{" +
                "\"firstname\": \"Sally\",\n" +
                "\"lastname\": \"Adams\",\n" +
                "\"totalprice\": 100,\n" +
                "\"depositpaid\": true,\n" +
                "\"additionalneeds\": \"Lunch\",\n" +
                "\"bookingdates\": { \n" +
                "\"checkin\": \"2023-02-23\", \n" +
                "\"checkout\": \"2023-10-23\" \n" +
                "} \n" +
                "}";
        ValidatableResponse response = given().
                pathParam("bookingid", "5").
                header("Content-Type","application/json").
                header("Accept","application/json").
                body(body).
                when().
                put(endpoint).
                then();
        response.log().body();
    }

    @Test
    public void deleteBooking() {
        String endpoint = "https://restful-booker.herokuapp.com/booking/{bookingid}";
        ValidatableResponse response = given().
                pathParam("bookingid", "5").
                when().
                delete(endpoint).
                then();
        response.log().body();
    }

}