package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.AccessToken
import fi.metatavu.keycloak.adminclient.models.ClientRepresentation
import fi.metatavu.keycloak.adminclient.models.CredentialRepresentation
import fi.metatavu.keycloak.adminclient.models.GlobalRequestResult
import fi.metatavu.keycloak.adminclient.models.IDToken
import fi.metatavu.keycloak.adminclient.models.ManagementPermissionReference
import fi.metatavu.keycloak.adminclient.models.UserRepresentation
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


interface ClientsApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmClientsGet
     * Get clients belonging to the realm   Returns a list of clients belonging to the realm */
    suspend fun realmClientsGet(realm:kotlin.String?,clientId:kotlin.String?,first:kotlin.Int?,max:kotlin.Int?,q:kotlin.String?,search:kotlin.Boolean?,viewableOnly:kotlin.Boolean?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdClientSecretGet
     * Get the client secret */
    suspend fun realmClientsIdClientSecretGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<CredentialRepresentation>
    /* realmClientsIdClientSecretPost
     * Generate a new secret for the client */
    suspend fun realmClientsIdClientSecretPost(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<CredentialRepresentation>
    /* realmClientsIdDefaultClientScopesClientScopeIdDelete
     *  */
    suspend fun realmClientsIdDefaultClientScopesClientScopeIdDelete(realm:kotlin.String?,id:kotlin.String?,clientScopeId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientsIdDefaultClientScopesClientScopeIdPut
     *  */
    suspend fun realmClientsIdDefaultClientScopesClientScopeIdPut(realm:kotlin.String?,id:kotlin.String?,clientScopeId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientsIdDefaultClientScopesGet
     * Get default client scopes. */
    suspend fun realmClientsIdDefaultClientScopesGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdDelete
     * Delete the client */
    suspend fun realmClientsIdDelete(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientsIdEvaluateScopesGenerateExampleAccessTokenGet
     * Create JSON with payload of example access token */
    suspend fun realmClientsIdEvaluateScopesGenerateExampleAccessTokenGet(realm:kotlin.String?,id:kotlin.String?,scope:kotlin.String?,userId:kotlin.String?,context:OperationRequest):Response<AccessToken>
    /* realmClientsIdEvaluateScopesGenerateExampleIdTokenGet
     * Create JSON with payload of example id token */
    suspend fun realmClientsIdEvaluateScopesGenerateExampleIdTokenGet(realm:kotlin.String?,id:kotlin.String?,scope:kotlin.String?,userId:kotlin.String?,context:OperationRequest):Response<IDToken>
    /* realmClientsIdEvaluateScopesGenerateExampleUserinfoGet
     * Create JSON with payload of example user info */
    suspend fun realmClientsIdEvaluateScopesGenerateExampleUserinfoGet(realm:kotlin.String?,id:kotlin.String?,scope:kotlin.String?,userId:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmClientsIdEvaluateScopesProtocolMappersGet
     * Return list of all protocol mappers, which will be used when generating tokens issued for particular client. */
    suspend fun realmClientsIdEvaluateScopesProtocolMappersGet(realm:kotlin.String?,id:kotlin.String?,scope:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdEvaluateScopesScopeMappingsRoleContainerIdGrantedGet
     * Get effective scope mapping of all roles of particular role container, which this client is defacto allowed to have in the accessToken issued for him. */
    suspend fun realmClientsIdEvaluateScopesScopeMappingsRoleContainerIdGrantedGet(realm:kotlin.String?,id:kotlin.String?,roleContainerId:kotlin.String?,scope:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdEvaluateScopesScopeMappingsRoleContainerIdNotGrantedGet
     * Get roles, which this client doesn’t have scope for and can’t have them in the accessToken issued for him. */
    suspend fun realmClientsIdEvaluateScopesScopeMappingsRoleContainerIdNotGrantedGet(realm:kotlin.String?,id:kotlin.String?,roleContainerId:kotlin.String?,scope:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdGet
     * Get representation of the client */
    suspend fun realmClientsIdGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<ClientRepresentation>
    /* realmClientsIdInstallationProvidersProviderIdGet
     *  */
    suspend fun realmClientsIdInstallationProvidersProviderIdGet(realm:kotlin.String?,id:kotlin.String?,providerId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientsIdManagementPermissionsGet
     * Return object stating whether client Authorization permissions have been initialized or not and a reference */
    suspend fun realmClientsIdManagementPermissionsGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmClientsIdManagementPermissionsPut
     * Return object stating whether client Authorization permissions have been initialized or not and a reference */
    suspend fun realmClientsIdManagementPermissionsPut(realm:kotlin.String?,id:kotlin.String?,managementPermissionReference:ManagementPermissionReference?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmClientsIdNodesNodeDelete
     * Unregister a cluster node from the client */
    suspend fun realmClientsIdNodesNodeDelete(realm:kotlin.String?,id:kotlin.String?,node:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientsIdNodesPost
     * Register a cluster node with the client   Manually register cluster node to this client - usually it’s not needed to call this directly as adapter should handle  by sending registration request to Keycloak */
    suspend fun realmClientsIdNodesPost(realm:kotlin.String?,id:kotlin.String?,requestBody:kotlin.collections.Map<kotlin.String, kotlin.Any>?,context:OperationRequest):Response<Void>
    /* realmClientsIdOfflineSessionCountGet
     * Get application offline session count   Returns a number of offline user sessions associated with this client   {      \&quot;count\&quot;: number  } */
    suspend fun realmClientsIdOfflineSessionCountGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmClientsIdOfflineSessionsGet
     * Get offline sessions for client   Returns a list of offline user sessions associated with this client */
    suspend fun realmClientsIdOfflineSessionsGet(realm:kotlin.String?,id:kotlin.String?,first:kotlin.Int?,max:kotlin.Int?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdOptionalClientScopesClientScopeIdDelete
     *  */
    suspend fun realmClientsIdOptionalClientScopesClientScopeIdDelete(realm:kotlin.String?,id:kotlin.String?,clientScopeId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientsIdOptionalClientScopesClientScopeIdPut
     *  */
    suspend fun realmClientsIdOptionalClientScopesClientScopeIdPut(realm:kotlin.String?,id:kotlin.String?,clientScopeId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientsIdOptionalClientScopesGet
     * Get optional client scopes. */
    suspend fun realmClientsIdOptionalClientScopesGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdPushRevocationPost
     * Push the client’s revocation policy to its admin URL   If the client has an admin URL, push revocation policy to it. */
    suspend fun realmClientsIdPushRevocationPost(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<GlobalRequestResult>
    /* realmClientsIdPut
     * Update the client */
    suspend fun realmClientsIdPut(realm:kotlin.String?,id:kotlin.String?,clientRepresentation:ClientRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientsIdRegistrationAccessTokenPost
     * Generate a new registration access token for the client */
    suspend fun realmClientsIdRegistrationAccessTokenPost(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<ClientRepresentation>
    /* realmClientsIdServiceAccountUserGet
     * Get a user dedicated to the service account */
    suspend fun realmClientsIdServiceAccountUserGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<UserRepresentation>
    /* realmClientsIdSessionCountGet
     * Get application session count   Returns a number of user sessions associated with this client   {      \&quot;count\&quot;: number  } */
    suspend fun realmClientsIdSessionCountGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmClientsIdTestNodesAvailableGet
     * Test if registered cluster nodes are available   Tests availability by sending &#39;ping&#39; request to all cluster nodes. */
    suspend fun realmClientsIdTestNodesAvailableGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<GlobalRequestResult>
    /* realmClientsIdUserSessionsGet
     * Get user sessions for client   Returns a list of user sessions associated with this client */
    suspend fun realmClientsIdUserSessionsGet(realm:kotlin.String?,id:kotlin.String?,first:kotlin.Int?,max:kotlin.Int?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsPost
     * Create a new client   Client’s client_id must be unique! */
    suspend fun realmClientsPost(realm:kotlin.String?,clientRepresentation:ClientRepresentation?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "ClientsApi-service"
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
            for (m in ClientsApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(ClientsApi::class.java, address)
            return routerFactory
        }
    }
}
