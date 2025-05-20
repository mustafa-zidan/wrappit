package me.zidan.wrappit

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/hello")
class HelloResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun hello(): Map<String, String> {
        return mapOf("message" to "This is a CodeRabbit test")
    }
}
