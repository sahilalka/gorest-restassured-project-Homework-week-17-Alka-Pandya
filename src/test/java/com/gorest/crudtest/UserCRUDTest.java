package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    private String name = "Suhani" + TestUtils.getRandomValue();
    private String email = "Kimaya" + TestUtils.getRandomValue()+ "@gmail.com";
    private String gender = "female";
    private String status = "active";


    @Test
    public void getUser() {
        Response response = given()
                .when()
                .get("https://gorest.co.in/public/v2/users");
        response.then().statusCode(200);
        response.prettyPrint();


    }

    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .body(userPojo)
                .post("https://gorest.co.in/public/v2/users");
        response.prettyPrint();
        response.then().log().all().statusCode(201);

    }
    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("zeel");
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .body(userPojo)
                .patch("https://gorest.co.in/public/v2/users/2279331");
        response.prettyPrint();
        response.then().log().all().statusCode(200);


    }
    @Test
    public void verifyUserDeleteSuccessfully() {
        String token ="61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .delete("https://gorest.co.in/public/v2/users/2278724");
        response.then().log().all().statusCode(204);


    }


    }




