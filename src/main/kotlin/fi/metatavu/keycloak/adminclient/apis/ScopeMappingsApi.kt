package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.RoleRepresentation
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.core.json.JsonArray
import com.github.wooyme.openapi.Response
import io.vertx.ext.web.api.OperationRequest
import io.vertx.kotlin.ext.web.api.contract.openapi3.OpenAPI3RouterFactory
import io.vertx.serviceproxy.ServiceBinder
import io.vertx.ext.web.handler.CookieHandler
import io.vertx.ext.web.handler.SessionHandler
import io.vertx.ext.web.sstore.LocalSessionStore
import java.util.List
import java.util.Map


interface ScopeMappingsApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmClientScopesIdScopeMappingsClientsClientAvailableGet
     * The available client-level roles   Returns the roles for the client that can be associated with the client’s scope */
    suspend fun realmClientScopesIdScopeMappingsClientsClientAvailableGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientScopesIdScopeMappingsClientsClientCompositeGet
     * Get effective client roles   Returns the roles for the client that are associated with the client’s scope. */
    suspend fun realmClientScopesIdScopeMappingsClientsClientCompositeGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,briefRepresentation:kotlin.Boolean?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientScopesIdScopeMappingsClientsClientDelete
     * Remove client-level roles from the client’s scope. */
    suspend fun realmClientScopesIdScopeMappingsClientsClientDelete(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientScopesIdScopeMappingsClientsClientGet
     * Get the roles associated with a client’s scope   Returns roles for the client. */
    suspend fun realmClientScopesIdScopeMappingsClientsClientGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientScopesIdScopeMappingsClientsClientPost
     * Add client-level roles to the client’s scope */
    suspend fun realmClientScopesIdScopeMappingsClientsClientPost(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientScopesIdScopeMappingsRealmAvailableGet
     * Get realm-level roles that are available to attach to this client’s scope */
    suspend fun realmClientScopesIdScopeMappingsRealmAvailableGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientScopesIdScopeMappingsRealmCompositeGet
     * Get effective realm-level roles associated with the client’s scope   What this does is recurse  any composite roles associated with the client’s scope and adds the roles to this lists. */
    suspend fun realmClientScopesIdScopeMappingsRealmCompositeGet(realm:kotlin.String?,id:kotlin.String?,briefRepresentation:kotlin.Boolean?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientScopesIdScopeMappingsRealmDelete
     * Remove a set of realm-level roles from the client’s scope */
    suspend fun realmClientScopesIdScopeMappingsRealmDelete(realm:kotlin.String?,id:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientScopesIdScopeMappingsRealmGet
     * Get realm-level roles associated with the client’s scope */
    suspend fun realmClientScopesIdScopeMappingsRealmGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientScopesIdScopeMappingsRealmPost
     * Add a set of realm-level roles to the client’s scope */
    suspend fun realmClientScopesIdScopeMappingsRealmPost(realm:kotlin.String?,id:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientsIdScopeMappingsClientsClientAvailableGet
     * The available client-level roles   Returns the roles for the client that can be associated with the client’s scope */
    suspend fun realmClientsIdScopeMappingsClientsClientAvailableGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdScopeMappingsClientsClientCompositeGet
     * Get effective client roles   Returns the roles for the client that are associated with the client’s scope. */
    suspend fun realmClientsIdScopeMappingsClientsClientCompositeGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,briefRepresentation:kotlin.Boolean?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdScopeMappingsClientsClientDelete
     * Remove client-level roles from the client’s scope. */
    suspend fun realmClientsIdScopeMappingsClientsClientDelete(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientsIdScopeMappingsClientsClientGet
     * Get the roles associated with a client’s scope   Returns roles for the client. */
    suspend fun realmClientsIdScopeMappingsClientsClientGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdScopeMappingsClientsClientPost
     * Add client-level roles to the client’s scope */
    suspend fun realmClientsIdScopeMappingsClientsClientPost(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientsIdScopeMappingsRealmAvailableGet
     * Get realm-level roles that are available to attach to this client’s scope */
    suspend fun realmClientsIdScopeMappingsRealmAvailableGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdScopeMappingsRealmCompositeGet
     * Get effective realm-level roles associated with the client’s scope   What this does is recurse  any composite roles associated with the client’s scope and adds the roles to this lists. */
    suspend fun realmClientsIdScopeMappingsRealmCompositeGet(realm:kotlin.String?,id:kotlin.String?,briefRepresentation:kotlin.Boolean?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdScopeMappingsRealmDelete
     * Remove a set of realm-level roles from the client’s scope */
    suspend fun realmClientsIdScopeMappingsRealmDelete(realm:kotlin.String?,id:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientsIdScopeMappingsRealmGet
     * Get realm-level roles associated with the client’s scope */
    suspend fun realmClientsIdScopeMappingsRealmGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdScopeMappingsRealmPost
     * Add a set of realm-level roles to the client’s scope */
    suspend fun realmClientsIdScopeMappingsRealmPost(realm:kotlin.String?,id:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "ScopeMappingsApi-service"
        suspend fun createRouterFactory(vertx: Vertx,path:String): io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory {
            val routerFactory = OpenAPI3RouterFactory.createAwait(vertx,path)
            routerFactory.addGlobalHandler(CookieHandler.create())
            routerFactory.addGlobalHandler(SessionHandler.create(LocalSessionStore.create(vertx)))
            routerFactory.setExtraOperationContextPayloadMapper{
                JsonObject().put("files",JsonArray(it.fileUploads().map { it.uploadedFileName() }))
            }
            val opf = routerFactory::class.java.getDeclaredField("operations")
            opf.isAccessible = true
            val operations = opf.get(routerFactory) as Map<String, Any>
            for (m in ScopeMappingsApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(ScopeMappingsApi::class.java, address)
            return routerFactory
        }
    }
}
