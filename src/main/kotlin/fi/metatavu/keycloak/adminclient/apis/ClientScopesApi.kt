package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.ClientScopeRepresentation
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


interface ClientScopesApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmClientScopesGet
     * Get client scopes belonging to the realm   Returns a list of client scopes belonging to the realm */
    suspend fun realmClientScopesGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientScopesIdDelete
     * Delete the client scope */
    suspend fun realmClientScopesIdDelete(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientScopesIdGet
     * Get representation of the client scope */
    suspend fun realmClientScopesIdGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<ClientScopeRepresentation>
    /* realmClientScopesIdPut
     * Update the client scope */
    suspend fun realmClientScopesIdPut(realm:kotlin.String?,id:kotlin.String?,clientScopeRepresentation:ClientScopeRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientScopesPost
     * Create a new client scope   Client Scope’s name must be unique! */
    suspend fun realmClientScopesPost(realm:kotlin.String?,clientScopeRepresentation:ClientScopeRepresentation?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "ClientScopesApi-service"
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
            for (m in ClientScopesApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(ClientScopesApi::class.java, address)
            return routerFactory
        }
    }
}
