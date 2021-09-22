package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.SynchronizationResult
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


interface UserStorageProviderApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* idNameGet
     * Need this for admin console to display simple name of provider when displaying client detail   KEYCLOAK-4328 */
    suspend fun idNameGet(id:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmUserStorageIdNameGet
     * Need this for admin console to display simple name of provider when displaying user detail   KEYCLOAK-4328 */
    suspend fun realmUserStorageIdNameGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmUserStorageIdRemoveImportedUsersPost
     * Remove imported users */
    suspend fun realmUserStorageIdRemoveImportedUsersPost(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUserStorageIdSyncPost
     * Trigger sync of users   Action can be \&quot;triggerFullSync\&quot; or \&quot;triggerChangedUsersSync\&quot; */
    suspend fun realmUserStorageIdSyncPost(realm:kotlin.String?,id:kotlin.String?,action:kotlin.String?,context:OperationRequest):Response<SynchronizationResult>
    /* realmUserStorageIdUnlinkUsersPost
     * Unlink imported users from a storage provider */
    suspend fun realmUserStorageIdUnlinkUsersPost(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUserStorageParentIdMappersIdSyncPost
     * Trigger sync of mapper data related to ldap mapper (roles, groups, …​)   direction is \&quot;fedToKeycloak\&quot; or \&quot;keycloakToFed\&quot; */
    suspend fun realmUserStorageParentIdMappersIdSyncPost(realm:kotlin.String?,parentId:kotlin.String?,id:kotlin.String?,direction:kotlin.String?,context:OperationRequest):Response<SynchronizationResult>
    companion object {
        const val address = "UserStorageProviderApi-service"
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
            for (m in UserStorageProviderApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(UserStorageProviderApi::class.java, address)
            return routerFactory
        }
    }
}
