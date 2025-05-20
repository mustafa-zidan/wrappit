package me.zidan.wrappit

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

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
}