package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.ClientPoliciesRepresentation
import fi.metatavu.keycloak.adminclient.models.ClientProfilesRepresentation
import fi.metatavu.keycloak.adminclient.models.ClientRepresentation
import fi.metatavu.keycloak.adminclient.models.GroupRepresentation
import fi.metatavu.keycloak.adminclient.models.ManagementPermissionReference
import fi.metatavu.keycloak.adminclient.models.PartialImportRepresentation
import fi.metatavu.keycloak.adminclient.models.RealmEventsConfigRepresentation
import fi.metatavu.keycloak.adminclient.models.RealmRepresentation
import fi.metatavu.keycloak.adminclient.models.TestLdapConnectionRepresentation
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


interface RealmsAdminApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmAdminEventsDelete
     * Delete all admin events */
    suspend fun realmAdminEventsDelete(realm:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAdminEventsGet
     * Get admin events   Returns all admin events, or filters events based on URL query parameters listed here */
    suspend fun realmAdminEventsGet(realm:kotlin.String?,authClient:kotlin.String?,authIpAddress:kotlin.String?,authRealm:kotlin.String?,authUser:kotlin.String?,dateFrom:kotlin.String?,dateTo:kotlin.String?,first:kotlin.Int?,max:kotlin.Int?,operationTypes:kotlin.Array<kotlin.String>?,resourcePath:kotlin.String?,resourceTypes:kotlin.Array<kotlin.String>?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClearKeysCachePost
     * Clear cache of external public keys (Public keys of clients or Identity providers) */
    suspend fun realmClearKeysCachePost(realm:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClearRealmCachePost
     * Clear realm cache */
    suspend fun realmClearRealmCachePost(realm:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClearUserCachePost
     * Clear user cache */
    suspend fun realmClearUserCachePost(realm:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientDescriptionConverterPost
     * Base path for importing clients under this realm. */
    suspend fun realmClientDescriptionConverterPost(realm:kotlin.String?,body:kotlin.String?,context:OperationRequest):Response<ClientRepresentation>
    /* realmClientPoliciesPoliciesGet
     *  */
    suspend fun realmClientPoliciesPoliciesGet(realm:kotlin.String?,context:OperationRequest):Response<ClientPoliciesRepresentation>
    /* realmClientPoliciesPoliciesPut
     *  */
    suspend fun realmClientPoliciesPoliciesPut(realm:kotlin.String?,clientPoliciesRepresentation:ClientPoliciesRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientPoliciesProfilesGet
     *  */
    suspend fun realmClientPoliciesProfilesGet(realm:kotlin.String?,includeGlobalProfiles:kotlin.Boolean?,context:OperationRequest):Response<ClientProfilesRepresentation>
    /* realmClientPoliciesProfilesPut
     *  */
    suspend fun realmClientPoliciesProfilesPut(realm:kotlin.String?,clientProfilesRepresentation:ClientProfilesRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientSessionStatsGet
     * Get client session stats   Returns a JSON map. */
    suspend fun realmClientSessionStatsGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmCredentialRegistratorsGet
     *  */
    suspend fun realmCredentialRegistratorsGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmDefaultDefaultClientScopesClientScopeIdDelete
     *  */
    suspend fun realmDefaultDefaultClientScopesClientScopeIdDelete(realm:kotlin.String?,clientScopeId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmDefaultDefaultClientScopesClientScopeIdPut
     *  */
    suspend fun realmDefaultDefaultClientScopesClientScopeIdPut(realm:kotlin.String?,clientScopeId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmDefaultDefaultClientScopesGet
     * Get realm default client scopes. */
    suspend fun realmDefaultDefaultClientScopesGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmDefaultGroupsGet
     * Get group hierarchy. */
    suspend fun realmDefaultGroupsGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmDefaultGroupsGroupIdDelete
     *  */
    suspend fun realmDefaultGroupsGroupIdDelete(realm:kotlin.String?,groupId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmDefaultGroupsGroupIdPut
     *  */
    suspend fun realmDefaultGroupsGroupIdPut(realm:kotlin.String?,groupId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmDefaultOptionalClientScopesClientScopeIdDelete
     *  */
    suspend fun realmDefaultOptionalClientScopesClientScopeIdDelete(realm:kotlin.String?,clientScopeId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmDefaultOptionalClientScopesClientScopeIdPut
     *  */
    suspend fun realmDefaultOptionalClientScopesClientScopeIdPut(realm:kotlin.String?,clientScopeId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmDefaultOptionalClientScopesGet
     * Get realm optional client scopes. */
    suspend fun realmDefaultOptionalClientScopesGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmDelete
     * Delete the realm */
    suspend fun realmDelete(realm:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmEventsConfigGet
     * Get the events provider configuration   Returns JSON object with events provider configuration */
    suspend fun realmEventsConfigGet(realm:kotlin.String?,context:OperationRequest):Response<RealmEventsConfigRepresentation>
    /* realmEventsConfigPut
     * Update the events provider   Change the events provider and/or its configuration */
    suspend fun realmEventsConfigPut(realm:kotlin.String?,realmEventsConfigRepresentation:RealmEventsConfigRepresentation?,context:OperationRequest):Response<Void>
    /* realmEventsDelete
     * Delete all events */
    suspend fun realmEventsDelete(realm:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmEventsGet
     * Get events   Returns all events, or filters them based on URL query parameters listed here */
    suspend fun realmEventsGet(realm:kotlin.String?,client:kotlin.String?,dateFrom:kotlin.String?,dateTo:kotlin.String?,first:kotlin.Int?,ipAddress:kotlin.String?,max:kotlin.Int?,type:kotlin.Array<kotlin.String>?,user:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmGet
     * Get the top-level representation of the realm   It will not include nested information like User and Client representations. */
    suspend fun realmGet(realm:kotlin.String?,context:OperationRequest):Response<RealmRepresentation>
    /* realmGroupByPathPathGet
     *  */
    suspend fun realmGroupByPathPathGet(realm:kotlin.String?,path:kotlin.String?,context:OperationRequest):Response<GroupRepresentation>
    /* realmLdapServerCapabilitiesPost
     * Get LDAP supported extensions. */
    suspend fun realmLdapServerCapabilitiesPost(realm:kotlin.String?,testLdapConnectionRepresentation:TestLdapConnectionRepresentation?,context:OperationRequest):Response<Void>
    /* realmLocalizationGet
     *  */
    suspend fun realmLocalizationGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmLocalizationLocaleDelete
     *  */
    suspend fun realmLocalizationLocaleDelete(realm:kotlin.String?,locale:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmLocalizationLocaleGet
     *  */
    suspend fun realmLocalizationLocaleGet(realm:kotlin.String?,locale:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmLocalizationLocaleKeyDelete
     *  */
    suspend fun realmLocalizationLocaleKeyDelete(realm:kotlin.String?,locale:kotlin.String?,key:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmLocalizationLocaleKeyGet
     *  */
    suspend fun realmLocalizationLocaleKeyGet(realm:kotlin.String?,locale:kotlin.String?,key:kotlin.String?,context:OperationRequest):Response<kotlin.String>
    /* realmLocalizationLocaleKeyPut
     *  */
    suspend fun realmLocalizationLocaleKeyPut(realm:kotlin.String?,locale:kotlin.String?,key:kotlin.String?,body:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmLocalizationLocalePatch
     *  */
    suspend fun realmLocalizationLocalePatch(realm:kotlin.String?,locale:kotlin.String?,requestBody:kotlin.collections.Map<kotlin.String, kotlin.Any>?,context:OperationRequest):Response<Void>
    /* realmLocalizationLocalePost
     * Import localization from uploaded JSON file */
    suspend fun realmLocalizationLocalePost(realm:kotlin.String?,locale:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmLogoutAllPost
     * Removes all user sessions. */
    suspend fun realmLogoutAllPost(realm:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmPartialExportPost
     * Partial export of existing realm into a JSON file. */
    suspend fun realmPartialExportPost(realm:kotlin.String?,exportClients:kotlin.Boolean?,exportGroupsAndRoles:kotlin.Boolean?,context:OperationRequest):Response<RealmRepresentation>
    /* realmPartialImportPost
     * Partial import from a JSON file to an existing realm. */
    suspend fun realmPartialImportPost(realm:kotlin.String?,partialImportRepresentation:PartialImportRepresentation?,context:OperationRequest):Response<Void>
    /* realmPushRevocationPost
     * Push the realm’s revocation policy to any client that has an admin url associated with it. */
    suspend fun realmPushRevocationPost(realm:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmPut
     * Update the top-level information of the realm   Any user, roles or client information in the representation  will be ignored. */
    suspend fun realmPut(realm:kotlin.String?,realmRepresentation:RealmRepresentation?,context:OperationRequest):Response<Void>
    /* realmSessionsSessionDelete
     * Remove a specific user session. */
    suspend fun realmSessionsSessionDelete(realm:kotlin.String?,session:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmTestLDAPConnectionPost
     * Test LDAP connection */
    suspend fun realmTestLDAPConnectionPost(realm:kotlin.String?,testLdapConnectionRepresentation:TestLdapConnectionRepresentation?,context:OperationRequest):Response<Void>
    /* realmTestSMTPConnectionPost
     *  */
    suspend fun realmTestSMTPConnectionPost(realm:kotlin.String?,requestBody:kotlin.collections.Map<kotlin.String, kotlin.Any>?,context:OperationRequest):Response<Void>
    /* realmUsersManagementPermissionsGet
     *  */
    suspend fun realmUsersManagementPermissionsGet(realm:kotlin.String?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmUsersManagementPermissionsPut
     *  */
    suspend fun realmUsersManagementPermissionsPut(realm:kotlin.String?,managementPermissionReference:ManagementPermissionReference?,context:OperationRequest):Response<ManagementPermissionReference>
    /* rootPost
     * Import a realm   Imports a realm from a full representation of that realm. */
    suspend fun rootPost(realmRepresentation:RealmRepresentation?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "RealmsAdminApi-service"
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
            for (m in RealmsAdminApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(RealmsAdminApi::class.java, address)
            return routerFactory
        }
    }
}
