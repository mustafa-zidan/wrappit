package me.zidan.wrappit

import jakarta.annotation.security.RolesAllowed
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import io.quarkus.security.identity.SecurityIdentity

/**
 * A secured resource that requires Keycloak authentication.
 * This endpoint demonstrates how to use Keycloak with Quarkus.
 */
@Path("/secured")
class SecuredResource {

    @Inject
    lateinit var securityIdentity: SecurityIdentity

    /**
     * This endpoint requires the user to be authenticated and have the "user" role.
     * It returns information about the authenticated user.
     */
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    fun userEndpoint(): Map<String, Any> {
        return mapOf(
            "username" to securityIdentity.principal.name,
            "roles" to securityIdentity.roles,
            "isAnonymous" to !securityIdentity.isAnonymous,
            "message" to "This endpoint is secured with Keycloak and requires the 'user' role"
        )
    }

    /**
     * This endpoint requires the user to be authenticated and have the "admin" role.
     * It returns information about the authenticated user and additional admin-specific data.
     */
    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    fun adminEndpoint(): Map<String, Any> {
        return mapOf(
            "username" to securityIdentity.principal.name,
            "roles" to securityIdentity.roles,
            "isAnonymous" to !securityIdentity.isAnonymous,
            "message" to "This endpoint is secured with Keycloak and requires the 'admin' role",
            "adminData" to "This is sensitive data only visible to admins"
        )
    }
}