package me.zidan.wrappit

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

@QuarkusTest
class HelloResourceTest {

    @Test
    fun testHelloEndpoint() {
        RestAssured.given()
          .`when`()
             .get("/hello")
          .then()
             .statusCode(200)
             .body("message", CoreMatchers.equalTo("This is a CodeRabbit test"))
    }
}
