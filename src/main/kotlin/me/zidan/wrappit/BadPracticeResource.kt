package me.zidan.wrappit

import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

// Public model class without documentation comments
class UserCredentials {
    var username: String = ""
    var password: String = ""
    var token: String = ""
}

// Response model without documentation
class UserProfile {
    var id: Int = 0
    var name: String = ""
    var email: String = ""
    var role: String = ""
}

@Path("/bad-practice")
class BadPracticeResource {

    // Breaking rule 1: Using println
    @GET
    @Path("/log")
    @Produces(MediaType.APPLICATION_JSON)
    fun logEndpoint(): Map<String, String> {
        println("This is a bad practice - using println in production code")
        return mapOf("message" to "Endpoint with println")
    }

    // Breaking rule 3: Hardcoded secrets
    @GET
    @Path("/secrets")
    @Produces(MediaType.APPLICATION_JSON)
    fun secretsEndpoint(): Map<String, String> {
        val apiKey = "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"
        val dbPassword = "super_secret_password123!"

        println("Connected with API key: $apiKey")

        return mapOf(
            "message" to "Endpoint with hardcoded secrets",
            "database" to "Connected with password: $dbPassword"
        )
    }

    // Breaking multiple rules:
    // 1. No input validation
    // 2. No exception handling
    // 3. Logging sensitive information
    // 4. No structured logger
    // 5. No test case
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun login(credentials: UserCredentials): Response {
        // Logging sensitive information
        println("Login attempt with username: ${credentials.username} and password: ${credentials.password}")

        // No validation of input
        // No exception handling

        // Hardcoded token
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ"

        // Creating response without validation
        val profile = UserProfile()
        profile.id = 123
        profile.name = "John Doe"
        profile.email = credentials.username
        profile.role = "ADMIN"  // No role validation

        println("Generated access token: $token")

        return Response.ok(profile).build()
    }
}
