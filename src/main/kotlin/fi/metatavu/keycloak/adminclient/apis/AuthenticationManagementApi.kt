package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.AuthenticationExecutionInfoRepresentation
import fi.metatavu.keycloak.adminclient.models.AuthenticationExecutionRepresentation
import fi.metatavu.keycloak.adminclient.models.AuthenticationFlowRepresentation
import fi.metatavu.keycloak.adminclient.models.AuthenticatorConfigInfoRepresentation
import fi.metatavu.keycloak.adminclient.models.AuthenticatorConfigRepresentation
import fi.metatavu.keycloak.adminclient.models.RequiredActionProviderRepresentation
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


interface AuthenticationManagementApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmAuthenticationAuthenticatorProvidersGet
     * Get authenticator providers   Returns a stream of authenticator providers. */
    suspend fun realmAuthenticationAuthenticatorProvidersGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmAuthenticationClientAuthenticatorProvidersGet
     * Get client authenticator providers   Returns a stream of client authenticator providers. */
    suspend fun realmAuthenticationClientAuthenticatorProvidersGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmAuthenticationConfigDescriptionProviderIdGet
     * Get authenticator provider’s configuration description */
    suspend fun realmAuthenticationConfigDescriptionProviderIdGet(realm:kotlin.String?,providerId:kotlin.String?,context:OperationRequest):Response<AuthenticatorConfigInfoRepresentation>
    /* realmAuthenticationConfigIdDelete
     * Delete authenticator configuration */
    suspend fun realmAuthenticationConfigIdDelete(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationConfigIdGet
     * Get authenticator configuration */
    suspend fun realmAuthenticationConfigIdGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<AuthenticatorConfigRepresentation>
    /* realmAuthenticationConfigIdPut
     * Update authenticator configuration */
    suspend fun realmAuthenticationConfigIdPut(realm:kotlin.String?,id:kotlin.String?,authenticatorConfigRepresentation:AuthenticatorConfigRepresentation?,context:OperationRequest):Response<Void>
    /* realmAuthenticationExecutionsExecutionIdConfigPost
     * Update execution with new configuration */
    suspend fun realmAuthenticationExecutionsExecutionIdConfigPost(realm:kotlin.String?,executionId:kotlin.String?,authenticatorConfigRepresentation:AuthenticatorConfigRepresentation?,context:OperationRequest):Response<Void>
    /* realmAuthenticationExecutionsExecutionIdDelete
     * Delete execution */
    suspend fun realmAuthenticationExecutionsExecutionIdDelete(realm:kotlin.String?,executionId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationExecutionsExecutionIdGet
     * Get Single Execution */
    suspend fun realmAuthenticationExecutionsExecutionIdGet(realm:kotlin.String?,executionId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationExecutionsExecutionIdLowerPriorityPost
     * Lower execution’s priority */
    suspend fun realmAuthenticationExecutionsExecutionIdLowerPriorityPost(realm:kotlin.String?,executionId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationExecutionsExecutionIdRaisePriorityPost
     * Raise execution’s priority */
    suspend fun realmAuthenticationExecutionsExecutionIdRaisePriorityPost(realm:kotlin.String?,executionId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationExecutionsPost
     * Add new authentication execution */
    suspend fun realmAuthenticationExecutionsPost(realm:kotlin.String?,authenticationExecutionRepresentation:AuthenticationExecutionRepresentation?,context:OperationRequest):Response<Void>
    /* realmAuthenticationFlowsFlowAliasCopyPost
     * Copy existing authentication flow under a new name   The new name is given as &#39;newName&#39; attribute of the passed JSON object */
    suspend fun realmAuthenticationFlowsFlowAliasCopyPost(realm:kotlin.String?,flowAlias:kotlin.String?,requestBody:kotlin.collections.Map<kotlin.String, kotlin.Any>?,context:OperationRequest):Response<Void>
    /* realmAuthenticationFlowsFlowAliasExecutionsExecutionPost
     * Add new authentication execution to a flow */
    suspend fun realmAuthenticationFlowsFlowAliasExecutionsExecutionPost(realm:kotlin.String?,flowAlias:kotlin.String?,requestBody:kotlin.collections.Map<kotlin.String, kotlin.Any>?,context:OperationRequest):Response<Void>
    /* realmAuthenticationFlowsFlowAliasExecutionsFlowPost
     * Add new flow with new execution to existing flow */
    suspend fun realmAuthenticationFlowsFlowAliasExecutionsFlowPost(realm:kotlin.String?,flowAlias:kotlin.String?,requestBody:kotlin.collections.Map<kotlin.String, kotlin.Any>?,context:OperationRequest):Response<Void>
    /* realmAuthenticationFlowsFlowAliasExecutionsGet
     * Get authentication executions for a flow */
    suspend fun realmAuthenticationFlowsFlowAliasExecutionsGet(realm:kotlin.String?,flowAlias:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationFlowsFlowAliasExecutionsPut
     * Update authentication executions of a Flow */
    suspend fun realmAuthenticationFlowsFlowAliasExecutionsPut(realm:kotlin.String?,flowAlias:kotlin.String?,authenticationExecutionInfoRepresentation:AuthenticationExecutionInfoRepresentation?,context:OperationRequest):Response<Void>
    /* realmAuthenticationFlowsGet
     * Get authentication flows   Returns a stream of authentication flows. */
    suspend fun realmAuthenticationFlowsGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmAuthenticationFlowsIdDelete
     * Delete an authentication flow */
    suspend fun realmAuthenticationFlowsIdDelete(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationFlowsIdGet
     * Get authentication flow for id */
    suspend fun realmAuthenticationFlowsIdGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<AuthenticationFlowRepresentation>
    /* realmAuthenticationFlowsIdPut
     * Update an authentication flow */
    suspend fun realmAuthenticationFlowsIdPut(realm:kotlin.String?,id:kotlin.String?,authenticationFlowRepresentation:AuthenticationFlowRepresentation?,context:OperationRequest):Response<Void>
    /* realmAuthenticationFlowsPost
     * Create a new authentication flow */
    suspend fun realmAuthenticationFlowsPost(realm:kotlin.String?,authenticationFlowRepresentation:AuthenticationFlowRepresentation?,context:OperationRequest):Response<Void>
    /* realmAuthenticationFormActionProvidersGet
     * Get form action providers   Returns a stream of form action providers. */
    suspend fun realmAuthenticationFormActionProvidersGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmAuthenticationFormProvidersGet
     * Get form providers   Returns a stream of form providers. */
    suspend fun realmAuthenticationFormProvidersGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmAuthenticationPerClientConfigDescriptionGet
     * Get configuration descriptions for all clients */
    suspend fun realmAuthenticationPerClientConfigDescriptionGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmAuthenticationRegisterRequiredActionPost
     * Register a new required actions */
    suspend fun realmAuthenticationRegisterRequiredActionPost(realm:kotlin.String?,requestBody:kotlin.collections.Map<kotlin.String, kotlin.Any>?,context:OperationRequest):Response<Void>
    /* realmAuthenticationRequiredActionsAliasDelete
     * Delete required action */
    suspend fun realmAuthenticationRequiredActionsAliasDelete(realm:kotlin.String?,alias:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationRequiredActionsAliasGet
     * Get required action for alias */
    suspend fun realmAuthenticationRequiredActionsAliasGet(realm:kotlin.String?,alias:kotlin.String?,context:OperationRequest):Response<RequiredActionProviderRepresentation>
    /* realmAuthenticationRequiredActionsAliasLowerPriorityPost
     * Lower required action’s priority */
    suspend fun realmAuthenticationRequiredActionsAliasLowerPriorityPost(realm:kotlin.String?,alias:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationRequiredActionsAliasPut
     * Update required action */
    suspend fun realmAuthenticationRequiredActionsAliasPut(realm:kotlin.String?,alias:kotlin.String?,requiredActionProviderRepresentation:RequiredActionProviderRepresentation?,context:OperationRequest):Response<Void>
    /* realmAuthenticationRequiredActionsAliasRaisePriorityPost
     * Raise required action’s priority */
    suspend fun realmAuthenticationRequiredActionsAliasRaisePriorityPost(realm:kotlin.String?,alias:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAuthenticationRequiredActionsGet
     * Get required actions   Returns a stream of required actions. */
    suspend fun realmAuthenticationRequiredActionsGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmAuthenticationUnregisteredRequiredActionsGet
     * Get unregistered required actions   Returns a stream of unregistered required actions. */
    suspend fun realmAuthenticationUnregisteredRequiredActionsGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    companion object {
        const val address = "AuthenticationManagementApi-service"
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
            for (m in AuthenticationManagementApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(AuthenticationManagementApi::class.java, address)
            return routerFactory
        }
    }
}
