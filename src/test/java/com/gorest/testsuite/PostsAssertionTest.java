package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()

                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);


    }
    //  1. Verify the if the total record is 25
    @Test
    public void test001(){
        response.body("total.size()", equalTo(25));
    }
    // 2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test002() {
        response.body("find { it.id == 38825 }.title", equalTo("Suus tremo sit autem adhuc voco valde."));
    }
    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test003(){
        response.body("user_id",hasItem("Aatreya Shah"));
    }
    //4.Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004(){
        response.body("user_id",hasItem(2223279));
    }
    // 5. Verify the body of userid = 2678 is equal
    @Test
    public void test005(){
        response.body("find{it.user_id == 2223284 }.body",equalTo("Certe dolorum accendo. Triumphus beatae despecto. Accusator cruciamentum optio. Dicta aeneus succurro. Degenero bene vito. Argumentum aestivus coniuratio. Aufero umquam caritas. Solio astrum patruus. Tolero ea dolorem. Temporibus acsi strenuus. Speciosus commodo natus. Doloremque necessitatibus spes."));
    }



}





