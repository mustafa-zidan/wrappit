package me.zidan.wrappit

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.junit.jupiter.api.Test

@QuarkusTest
class SecuredResourceTest {

    @Test
    fun testSecuredEndpointWithoutAuth() {
        // Test accessing secured endpoint without authentication
        // Should return 403 Forbidden when OIDC is disabled in tests
        RestAssured.given()
            .`when`()
                .get("/secured/user")
            .then()
                .statusCode(403)
    }
}
