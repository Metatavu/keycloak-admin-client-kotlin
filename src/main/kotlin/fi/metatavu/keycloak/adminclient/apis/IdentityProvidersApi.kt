package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.IdentityProviderMapperRepresentation
import fi.metatavu.keycloak.adminclient.models.IdentityProviderRepresentation
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


interface IdentityProvidersApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmIdentityProviderImportConfigPost
     * Import identity provider from uploaded JSON file */
    suspend fun realmIdentityProviderImportConfigPost(realm:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    /* realmIdentityProviderInstancesAliasDelete
     * Delete the identity provider */
    suspend fun realmIdentityProviderInstancesAliasDelete(realm:kotlin.String?,alias:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmIdentityProviderInstancesAliasExportGet
     * Export public broker configuration for identity provider */
    suspend fun realmIdentityProviderInstancesAliasExportGet(realm:kotlin.String?,alias:kotlin.String?,format:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmIdentityProviderInstancesAliasGet
     * Get the identity provider */
    suspend fun realmIdentityProviderInstancesAliasGet(realm:kotlin.String?,alias:kotlin.String?,context:OperationRequest):Response<IdentityProviderRepresentation>
    /* realmIdentityProviderInstancesAliasManagementPermissionsGet
     * Return object stating whether client Authorization permissions have been initialized or not and a reference */
    suspend fun realmIdentityProviderInstancesAliasManagementPermissionsGet(realm:kotlin.String?,alias:kotlin.String?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmIdentityProviderInstancesAliasManagementPermissionsPut
     * Return object stating whether client Authorization permissions have been initialized or not and a reference */
    suspend fun realmIdentityProviderInstancesAliasManagementPermissionsPut(realm:kotlin.String?,alias:kotlin.String?,managementPermissionReference:ManagementPermissionReference?,context:OperationRequest):Response<ManagementPermissionReference>
    /* realmIdentityProviderInstancesAliasMapperTypesGet
     * Get mapper types for identity provider */
    suspend fun realmIdentityProviderInstancesAliasMapperTypesGet(realm:kotlin.String?,alias:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmIdentityProviderInstancesAliasMappersGet
     * Get mappers for identity provider */
    suspend fun realmIdentityProviderInstancesAliasMappersGet(realm:kotlin.String?,alias:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmIdentityProviderInstancesAliasMappersIdDelete
     * Delete a mapper for the identity provider */
    suspend fun realmIdentityProviderInstancesAliasMappersIdDelete(realm:kotlin.String?,alias:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmIdentityProviderInstancesAliasMappersIdGet
     * Get mapper by id for the identity provider */
    suspend fun realmIdentityProviderInstancesAliasMappersIdGet(realm:kotlin.String?,alias:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<IdentityProviderMapperRepresentation>
    /* realmIdentityProviderInstancesAliasMappersIdPut
     * Update a mapper for the identity provider */
    suspend fun realmIdentityProviderInstancesAliasMappersIdPut(realm:kotlin.String?,alias:kotlin.String?,id:kotlin.String?,identityProviderMapperRepresentation:IdentityProviderMapperRepresentation?,context:OperationRequest):Response<Void>
    /* realmIdentityProviderInstancesAliasMappersPost
     * Add a mapper to identity provider */
    suspend fun realmIdentityProviderInstancesAliasMappersPost(realm:kotlin.String?,alias:kotlin.String?,identityProviderMapperRepresentation:IdentityProviderMapperRepresentation?,context:OperationRequest):Response<Void>
    /* realmIdentityProviderInstancesAliasPut
     * Update the identity provider */
    suspend fun realmIdentityProviderInstancesAliasPut(realm:kotlin.String?,alias:kotlin.String?,identityProviderRepresentation:IdentityProviderRepresentation?,context:OperationRequest):Response<Void>
    /* realmIdentityProviderInstancesGet
     * Get identity providers */
    suspend fun realmIdentityProviderInstancesGet(realm:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmIdentityProviderInstancesPost
     * Create a new identity provider */
    suspend fun realmIdentityProviderInstancesPost(realm:kotlin.String?,identityProviderRepresentation:IdentityProviderRepresentation?,context:OperationRequest):Response<Void>
    /* realmIdentityProviderProvidersProviderIdGet
     * Get identity providers */
    suspend fun realmIdentityProviderProvidersProviderIdGet(realm:kotlin.String?,providerId:kotlin.String?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "IdentityProvidersApi-service"
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
            for (m in IdentityProvidersApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(IdentityProvidersApi::class.java, address)
            return routerFactory
        }
    }
}
