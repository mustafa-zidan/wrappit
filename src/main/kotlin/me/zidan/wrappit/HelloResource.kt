package me.zidan.wrappit

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/hello")
class HelloResource {

    /**
     * Handles HTTP GET requests to the /hello endpoint and returns a JSON message.
     *
     * @return A map containing a single key "message" with a test string value.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun hello(): Map<String, String> {
        return mapOf("message" to "This is a CodeRabbit test")
    }
}
