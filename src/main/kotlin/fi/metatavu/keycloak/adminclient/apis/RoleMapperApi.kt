package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.MappingsRepresentation
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


interface RoleMapperApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmGroupsIdRoleMappingsGet
     * Get role mappings */
    suspend fun realmGroupsIdRoleMappingsGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<MappingsRepresentation>
    /* realmGroupsIdRoleMappingsRealmAvailableGet
     * Get realm-level roles that can be mapped */
    suspend fun realmGroupsIdRoleMappingsRealmAvailableGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmGroupsIdRoleMappingsRealmCompositeGet
     * Get effective realm-level role mappings   This will recurse all composite roles to get the result. */
    suspend fun realmGroupsIdRoleMappingsRealmCompositeGet(realm:kotlin.String?,id:kotlin.String?,briefRepresentation:kotlin.Boolean?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmGroupsIdRoleMappingsRealmDelete
     * Delete realm-level role mappings */
    suspend fun realmGroupsIdRoleMappingsRealmDelete(realm:kotlin.String?,id:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmGroupsIdRoleMappingsRealmGet
     * Get realm-level role mappings */
    suspend fun realmGroupsIdRoleMappingsRealmGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmGroupsIdRoleMappingsRealmPost
     * Add realm-level role mappings to the user */
    suspend fun realmGroupsIdRoleMappingsRealmPost(realm:kotlin.String?,id:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmUsersIdRoleMappingsGet
     * Get role mappings */
    suspend fun realmUsersIdRoleMappingsGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<MappingsRepresentation>
    /* realmUsersIdRoleMappingsRealmAvailableGet
     * Get realm-level roles that can be mapped */
    suspend fun realmUsersIdRoleMappingsRealmAvailableGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdRoleMappingsRealmCompositeGet
     * Get effective realm-level role mappings   This will recurse all composite roles to get the result. */
    suspend fun realmUsersIdRoleMappingsRealmCompositeGet(realm:kotlin.String?,id:kotlin.String?,briefRepresentation:kotlin.Boolean?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdRoleMappingsRealmDelete
     * Delete realm-level role mappings */
    suspend fun realmUsersIdRoleMappingsRealmDelete(realm:kotlin.String?,id:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmUsersIdRoleMappingsRealmGet
     * Get realm-level role mappings */
    suspend fun realmUsersIdRoleMappingsRealmGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdRoleMappingsRealmPost
     * Add realm-level role mappings to the user */
    suspend fun realmUsersIdRoleMappingsRealmPost(realm:kotlin.String?,id:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "RoleMapperApi-service"
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
            for (m in RoleMapperApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(RoleMapperApi::class.java, address)
            return routerFactory
        }
    }
}
