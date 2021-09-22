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


interface RolesApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmClientsIdRolesGet
     * Get all roles for the realm or client */
    suspend fun realmClientsIdRolesGet(realm:kotlin.String?,id:kotlin.String?,briefRepresentation:kotlin.Boolean?,first:kotlin.Int?,max:kotlin.Int?,search:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdRolesPost
     * Create a new role for the realm or client */
    suspend fun realmClientsIdRolesPost(realm:kotlin.String?,id:kotlin.String?,roleRepresentation:RoleRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientsIdRolesRoleNameCompositesClientsClientUuidGet
     * Get client-level roles for the client that are in the role’s composite */
    suspend fun realmClientsIdRolesRoleNameCompositesClientsClientUuidGet(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,clientUuid:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdRolesRoleNameCompositesDelete
     * Remove roles from the role’s composite */
    suspend fun realmClientsIdRolesRoleNameCompositesDelete(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientsIdRolesRoleNameCompositesGet
     * Get composites of the role */
    suspend fun realmClientsIdRolesRoleNameCompositesGet(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdRolesRoleNameCompositesPost
     * Add a composite to the role */
    suspend fun realmClientsIdRolesRoleNameCompositesPost(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientsIdRolesRoleNameCompositesRealmGet
     * Get realm-level roles of the role’s composite */
    suspend fun realmClientsIdRolesRoleNameCompositesRealmGet(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdRolesRoleNameDelete
     * Delete a role by name */
    suspend fun realmClientsIdRolesRoleNameDelete(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientsIdRolesRoleNameGet
     * Get a role by name */
    suspend fun realmClientsIdRolesRoleNameGet(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<RoleRepresentation>
    /* realmClientsIdRolesRoleNameGroupsGet
     * Returns a stream of groups that have the specified role name */
    suspend fun realmClientsIdRolesRoleNameGroupsGet(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,briefRepresentation:kotlin.Boolean?,first:kotlin.Int?,max:kotlin.Int?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdRolesRoleNameManagementPermissionsGet
     * Return object stating whether role Authorization permissions have been initialized or not and a reference */
    suspend fun realmClientsIdRolesRoleNameManagementPermissionsGet(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmClientsIdRolesRoleNameManagementPermissionsPut
     * Return object stating whether role Authorization permissions have been initialized or not and a reference */
    suspend fun realmClientsIdRolesRoleNameManagementPermissionsPut(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,managementPermissionReference:ManagementPermissionReference?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmClientsIdRolesRoleNamePut
     * Update a role by name */
    suspend fun realmClientsIdRolesRoleNamePut(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,roleRepresentation:RoleRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientsIdRolesRoleNameUsersGet
     * Returns a stream of users that have the specified role name. */
    suspend fun realmClientsIdRolesRoleNameUsersGet(realm:kotlin.String?,id:kotlin.String?,roleName:kotlin.String?,first:kotlin.Int?,max:kotlin.Int?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmRolesGet
     * Get all roles for the realm or client */
    suspend fun realmRolesGet(realm:kotlin.String?,briefRepresentation:kotlin.Boolean?,first:kotlin.Int?,max:kotlin.Int?,search:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmRolesPost
     * Create a new role for the realm or client */
    suspend fun realmRolesPost(realm:kotlin.String?,roleRepresentation:RoleRepresentation?,context:OperationRequest):Response<Void>
    /* realmRolesRoleNameCompositesClientsClientUuidGet
     * Get client-level roles for the client that are in the role’s composite */
    suspend fun realmRolesRoleNameCompositesClientsClientUuidGet(realm:kotlin.String?,roleName:kotlin.String?,clientUuid:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmRolesRoleNameCompositesDelete
     * Remove roles from the role’s composite */
    suspend fun realmRolesRoleNameCompositesDelete(realm:kotlin.String?,roleName:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmRolesRoleNameCompositesGet
     * Get composites of the role */
    suspend fun realmRolesRoleNameCompositesGet(realm:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmRolesRoleNameCompositesPost
     * Add a composite to the role */
    suspend fun realmRolesRoleNameCompositesPost(realm:kotlin.String?,roleName:kotlin.String?,roleRepresentation:kotlin.Array<RoleRepresentation>?,context:OperationRequest):Response<Void>
    /* realmRolesRoleNameCompositesRealmGet
     * Get realm-level roles of the role’s composite */
    suspend fun realmRolesRoleNameCompositesRealmGet(realm:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmRolesRoleNameDelete
     * Delete a role by name */
    suspend fun realmRolesRoleNameDelete(realm:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmRolesRoleNameGet
     * Get a role by name */
    suspend fun realmRolesRoleNameGet(realm:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<RoleRepresentation>
    /* realmRolesRoleNameGroupsGet
     * Returns a stream of groups that have the specified role name */
    suspend fun realmRolesRoleNameGroupsGet(realm:kotlin.String?,roleName:kotlin.String?,briefRepresentation:kotlin.Boolean?,first:kotlin.Int?,max:kotlin.Int?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmRolesRoleNameManagementPermissionsGet
     * Return object stating whether role Authorization permissions have been initialized or not and a reference */
    suspend fun realmRolesRoleNameManagementPermissionsGet(realm:kotlin.String?,roleName:kotlin.String?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmRolesRoleNameManagementPermissionsPut
     * Return object stating whether role Authorization permissions have been initialized or not and a reference */
    suspend fun realmRolesRoleNameManagementPermissionsPut(realm:kotlin.String?,roleName:kotlin.String?,managementPermissionReference:ManagementPermissionReference?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmRolesRoleNamePut
     * Update a role by name */
    suspend fun realmRolesRoleNamePut(realm:kotlin.String?,roleName:kotlin.String?,roleRepresentation:RoleRepresentation?,context:OperationRequest):Response<Void>
    /* realmRolesRoleNameUsersGet
     * Returns a stream of users that have the specified role name. */
    suspend fun realmRolesRoleNameUsersGet(realm:kotlin.String?,roleName:kotlin.String?,first:kotlin.Int?,max:kotlin.Int?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    companion object {
        const val address = "RolesApi-service"
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
            for (m in RolesApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(RolesApi::class.java, address)
            return routerFactory
        }
    }
}
