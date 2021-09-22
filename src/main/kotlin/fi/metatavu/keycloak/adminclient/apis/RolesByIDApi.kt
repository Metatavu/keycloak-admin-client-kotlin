package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.ManagementPermissionReference
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


interface RolesByIDApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmRolesByIdRoleIdCompositesClientsClientUuidGet
     * Get client-level roles for the client that are in the role’s composite */
    suspend fun realmRolesByIdRoleIdCompositesClientsClientUuidGet(realm:kotlin.String?,roleId:kotlin.String?,clientUuid:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmRolesByIdRoleIdCompositesDelete
     * Remove a set of roles from the role’s composite */
    suspend fun realmRolesByIdRoleIdCompositesDelete(realm:kotlin.String?,roleId:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmRolesByIdRoleIdCompositesGet
     * Get role’s children   Returns a set of role’s children provided the role is a composite. */
    suspend fun realmRolesByIdRoleIdCompositesGet(realm:kotlin.String?,roleId:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmRolesByIdRoleIdCompositesPost
     * Make the role a composite role by associating some child roles */
    suspend fun realmRolesByIdRoleIdCompositesPost(realm:kotlin.String?,roleId:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmRolesByIdRoleIdCompositesRealmGet
     * Get realm-level roles that are in the role’s composite */
    suspend fun realmRolesByIdRoleIdCompositesRealmGet(realm:kotlin.String?,roleId:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmRolesByIdRoleIdDelete
     * Delete the role */
    suspend fun realmRolesByIdRoleIdDelete(realm:kotlin.String?,roleId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmRolesByIdRoleIdGet
     * Get a specific role’s representation */
    suspend fun realmRolesByIdRoleIdGet(realm:kotlin.String?,roleId:kotlin.String?,context:OperationRequest):Response<RoleRepresentation>
    /* realmRolesByIdRoleIdManagementPermissionsGet
     * Return object stating whether role Authoirzation permissions have been initialized or not and a reference */
    suspend fun realmRolesByIdRoleIdManagementPermissionsGet(realm:kotlin.String?,roleId:kotlin.String?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmRolesByIdRoleIdManagementPermissionsPut
     * Return object stating whether role Authoirzation permissions have been initialized or not and a reference */
    suspend fun realmRolesByIdRoleIdManagementPermissionsPut(realm:kotlin.String?,roleId:kotlin.String?,managementPermissionReference:ManagementPermissionReference?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmRolesByIdRoleIdPut
     * Update the role */
    suspend fun realmRolesByIdRoleIdPut(realm:kotlin.String?,roleId:kotlin.String?,roleRepresentation:RoleRepresentation?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "RolesByIDApi-service"
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
            for (m in RolesByIDApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(RolesByIDApi::class.java, address)
            return routerFactory
        }
    }
}
