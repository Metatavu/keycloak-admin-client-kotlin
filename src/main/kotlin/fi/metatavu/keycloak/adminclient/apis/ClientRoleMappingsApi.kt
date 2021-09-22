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


interface ClientRoleMappingsApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmGroupsIdRoleMappingsClientsClientAvailableGet
     * Get available client-level roles that can be mapped to the user */
    suspend fun realmGroupsIdRoleMappingsClientsClientAvailableGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmGroupsIdRoleMappingsClientsClientCompositeGet
     * Get effective client-level role mappings   This recurses any composite roles */
    suspend fun realmGroupsIdRoleMappingsClientsClientCompositeGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,briefRepresentation:kotlin.Boolean?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmGroupsIdRoleMappingsClientsClientDelete
     * Delete client-level roles from user role mapping */
    suspend fun realmGroupsIdRoleMappingsClientsClientDelete(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmGroupsIdRoleMappingsClientsClientGet
     * Get client-level role mappings for the user, and the app */
    suspend fun realmGroupsIdRoleMappingsClientsClientGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmGroupsIdRoleMappingsClientsClientPost
     * Add client-level roles to the user role mapping */
    suspend fun realmGroupsIdRoleMappingsClientsClientPost(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmUsersIdRoleMappingsClientsClientAvailableGet
     * Get available client-level roles that can be mapped to the user */
    suspend fun realmUsersIdRoleMappingsClientsClientAvailableGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdRoleMappingsClientsClientCompositeGet
     * Get effective client-level role mappings   This recurses any composite roles */
    suspend fun realmUsersIdRoleMappingsClientsClientCompositeGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,briefRepresentation:kotlin.Boolean?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdRoleMappingsClientsClientDelete
     * Delete client-level roles from user role mapping */
    suspend fun realmUsersIdRoleMappingsClientsClientDelete(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmUsersIdRoleMappingsClientsClientGet
     * Get client-level role mappings for the user, and the app */
    suspend fun realmUsersIdRoleMappingsClientsClientGet(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdRoleMappingsClientsClientPost
     * Add client-level roles to the user role mapping */
    suspend fun realmUsersIdRoleMappingsClientsClientPost(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "ClientRoleMappingsApi-service"
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
            for (m in ClientRoleMappingsApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(ClientRoleMappingsApi::class.java, address)
            return routerFactory
        }
    }
}
