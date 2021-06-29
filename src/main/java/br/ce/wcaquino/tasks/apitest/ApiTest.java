package br.ce.wcaquino.tasks.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }


    @Test
    public void test() {
        RestAssured
                .given()
                .when()
                .get("/todo")
                .then()
                .statusCode(200);
    }

    @Test
    public void acidionar() {
        RestAssured.given()
                .body("{ \"task\" : \"teste a\", \"dueDate\" : \"2021-12-30\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(201);
    }

    @Test
    public void acidionar1() {
        RestAssured.given()
                .body("{ \"task\" : \"teste a\", \"dueDate\" : \"2020-12-30\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(400)
        .body("message", CoreMatchers.is("Due date must not be in past"));
    }

}


