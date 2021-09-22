package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.GroupRepresentation
import fi.metatavu.keycloak.adminclient.models.ManagementPermissionReference
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


interface GroupsApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmGroupsCountGet
     * Returns the groups counts. */
    suspend fun realmGroupsCountGet(realm:kotlin.String?,search:kotlin.String?,top:kotlin.Boolean?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmGroupsGet
     * Get group hierarchy. */
    suspend fun realmGroupsGet(realm:kotlin.String?,briefRepresentation:kotlin.Boolean?,first:kotlin.Int?,max:kotlin.Int?,search:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmGroupsIdChildrenPost
     * Set or create child. */
    suspend fun realmGroupsIdChildrenPost(realm:kotlin.String?,id:kotlin.String?,groupRepresentation:GroupRepresentation?,context:OperationRequest):Response<Void>
    /* realmGroupsIdDelete
     *  */
    suspend fun realmGroupsIdDelete(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmGroupsIdGet
     *  */
    suspend fun realmGroupsIdGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<GroupRepresentation>
    /* realmGroupsIdManagementPermissionsGet
     * Return object stating whether client Authorization permissions have been initialized or not and a reference */
    suspend fun realmGroupsIdManagementPermissionsGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmGroupsIdManagementPermissionsPut
     * Return object stating whether client Authorization permissions have been initialized or not and a reference */
    suspend fun realmGroupsIdManagementPermissionsPut(realm:kotlin.String?,id:kotlin.String?,managementPermissionReference:ManagementPermissionReference?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmGroupsIdMembersGet
     * Get users   Returns a stream of users, filtered according to query parameters */
    suspend fun realmGroupsIdMembersGet(realm:kotlin.String?,id:kotlin.String?,briefRepresentation:kotlin.Boolean?,first:kotlin.Int?,max:kotlin.Int?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmGroupsIdPut
     * Update group, ignores subgroups. */
    suspend fun realmGroupsIdPut(realm:kotlin.String?,id:kotlin.String?,groupRepresentation:GroupRepresentation?,context:OperationRequest):Response<Void>
    /* realmGroupsPost
     * create or add a top level realm groupSet or create child. */
    suspend fun realmGroupsPost(realm:kotlin.String?,groupRepresentation:GroupRepresentation?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "GroupsApi-service"
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
            for (m in GroupsApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(GroupsApi::class.java, address)
            return routerFactory
        }
    }
}
