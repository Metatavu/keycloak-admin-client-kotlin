package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.CredentialRepresentation
import fi.metatavu.keycloak.adminclient.models.FederatedIdentityRepresentation
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


interface UsersApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmUsersCountGet
     * Returns the number of users that match the given criteria. */
    suspend fun realmUsersCountGet(realm:kotlin.String?,email:kotlin.String?,emailVerified:kotlin.Boolean?,firstName:kotlin.String?,lastName:kotlin.String?,search:kotlin.String?,username:kotlin.String?,context:OperationRequest):Response<kotlin.Int>
    /* realmUsersGet
     * Get users   Returns a stream of users, filtered according to query parameters */
    suspend fun realmUsersGet(realm:kotlin.String?,briefRepresentation:kotlin.Boolean?,email:kotlin.String?,emailVerified:kotlin.Boolean?,enabled:kotlin.Boolean?,exact:kotlin.Boolean?,first:kotlin.Int?,firstName:kotlin.String?,idpAlias:kotlin.String?,idpUserId:kotlin.String?,lastName:kotlin.String?,max:kotlin.Int?,search:kotlin.String?,username:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdConfiguredUserStorageCredentialTypesGet
     * Return credential types, which are provided by the user storage where user is stored. */
    suspend fun realmUsersIdConfiguredUserStorageCredentialTypesGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdConsentsClientDelete
     * Revoke consent and offline tokens for particular client from user */
    suspend fun realmUsersIdConsentsClientDelete(realm:kotlin.String?,id:kotlin.String?,client:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdConsentsGet
     * Get consents granted by the user */
    suspend fun realmUsersIdConsentsGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdCredentialsCredentialIdDelete
     * Remove a credential for a user */
    suspend fun realmUsersIdCredentialsCredentialIdDelete(realm:kotlin.String?,id:kotlin.String?,credentialId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdCredentialsCredentialIdMoveAfterNewPreviousCredentialIdPost
     * Move a credential to a position behind another credential */
    suspend fun realmUsersIdCredentialsCredentialIdMoveAfterNewPreviousCredentialIdPost(realm:kotlin.String?,id:kotlin.String?,credentialId:kotlin.String?,newPreviousCredentialId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdCredentialsCredentialIdMoveToFirstPost
     * Move a credential to a first position in the credentials list of the user */
    suspend fun realmUsersIdCredentialsCredentialIdMoveToFirstPost(realm:kotlin.String?,id:kotlin.String?,credentialId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdCredentialsCredentialIdUserLabelPut
     * Update a credential label for a user */
    suspend fun realmUsersIdCredentialsCredentialIdUserLabelPut(realm:kotlin.String?,id:kotlin.String?,credentialId:kotlin.String?,body:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdCredentialsGet
     *  */
    suspend fun realmUsersIdCredentialsGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdDelete
     * Delete the user */
    suspend fun realmUsersIdDelete(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdDisableCredentialTypesPut
     * Disable all credentials for a user of a specific type */
    suspend fun realmUsersIdDisableCredentialTypesPut(realm:kotlin.String?,id:kotlin.String?,requestBody:kotlin.Array<kotlin.String>?,context:OperationRequest):Response<Void>
    /* realmUsersIdExecuteActionsEmailPut
     * Send a update account email to the user   An email contains a link the user can click to perform a set of required actions. */
    suspend fun realmUsersIdExecuteActionsEmailPut(realm:kotlin.String?,id:kotlin.String?,requestBody:kotlin.Array<kotlin.String>?,clientId:kotlin.String?,lifespan:kotlin.Int?,redirectUri:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdFederatedIdentityGet
     * Get social logins associated with the user */
    suspend fun realmUsersIdFederatedIdentityGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdFederatedIdentityProviderDelete
     * Remove a social login provider from user */
    suspend fun realmUsersIdFederatedIdentityProviderDelete(realm:kotlin.String?,id:kotlin.String?,provider:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdFederatedIdentityProviderPost
     * Add a social login provider to the user */
    suspend fun realmUsersIdFederatedIdentityProviderPost(realm:kotlin.String?,id:kotlin.String?,provider:kotlin.String?,federatedIdentityRepresentation:FederatedIdentityRepresentation?,context:OperationRequest):Response<Void>
    /* realmUsersIdGet
     * Get representation of the user */
    suspend fun realmUsersIdGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<UserRepresentation>
    /* realmUsersIdGroupsCountGet
     *  */
    suspend fun realmUsersIdGroupsCountGet(realm:kotlin.String?,id:kotlin.String?,search:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmUsersIdGroupsGet
     *  */
    suspend fun realmUsersIdGroupsGet(realm:kotlin.String?,id:kotlin.String?,briefRepresentation:kotlin.Boolean?,first:kotlin.Int?,max:kotlin.Int?,search:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdGroupsGroupIdDelete
     *  */
    suspend fun realmUsersIdGroupsGroupIdDelete(realm:kotlin.String?,id:kotlin.String?,groupId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdGroupsGroupIdPut
     *  */
    suspend fun realmUsersIdGroupsGroupIdPut(realm:kotlin.String?,id:kotlin.String?,groupId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdImpersonationPost
     * Impersonate the user */
    suspend fun realmUsersIdImpersonationPost(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmUsersIdLogoutPost
     * Remove all user sessions associated with the user   Also send notification to all clients that have an admin URL to invalidate the sessions for the particular user. */
    suspend fun realmUsersIdLogoutPost(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdOfflineSessionsClientUuidGet
     * Get offline sessions associated with the user and client */
    suspend fun realmUsersIdOfflineSessionsClientUuidGet(realm:kotlin.String?,id:kotlin.String?,clientUuid:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersIdPut
     * Update the user */
    suspend fun realmUsersIdPut(realm:kotlin.String?,id:kotlin.String?,userRepresentation:UserRepresentation?,context:OperationRequest):Response<Void>
    /* realmUsersIdResetPasswordPut
     * Set up a new password for the user. */
    suspend fun realmUsersIdResetPasswordPut(realm:kotlin.String?,id:kotlin.String?,credentialRepresentation:CredentialRepresentation?,context:OperationRequest):Response<Void>
    /* realmUsersIdSendVerifyEmailPut
     * Send an email-verification email to the user   An email contains a link the user can click to verify their email address. */
    suspend fun realmUsersIdSendVerifyEmailPut(realm:kotlin.String?,id:kotlin.String?,clientId:kotlin.String?,redirectUri:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmUsersIdSessionsGet
     * Get sessions associated with the user */
    suspend fun realmUsersIdSessionsGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmUsersPost
     * Create a new user   Username must be unique. */
    suspend fun realmUsersPost(realm:kotlin.String?,userRepresentation:UserRepresentation?,context:OperationRequest):Response<Void>
    /* realmUsersProfileGet
     *  */
    suspend fun realmUsersProfileGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.String>
    /* realmUsersProfilePut
     *  */
    suspend fun realmUsersProfilePut(realm:kotlin.String?,body:kotlin.String?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "UsersApi-service"
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
            for (m in UsersApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(UsersApi::class.java, address)
            return routerFactory
        }
    }
}
