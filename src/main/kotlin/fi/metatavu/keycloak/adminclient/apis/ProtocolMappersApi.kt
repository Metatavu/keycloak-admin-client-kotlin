package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.ProtocolMapperRepresentation
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


interface ProtocolMappersApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmClientScopesId1ProtocolMappersModelsId2Delete
     * Delete the mapper */
    suspend fun realmClientScopesId1ProtocolMappersModelsId2Delete(realm:kotlin.String?,id1:kotlin.String?,id2:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientScopesId1ProtocolMappersModelsId2Get
     * Get mapper by id */
    suspend fun realmClientScopesId1ProtocolMappersModelsId2Get(realm:kotlin.String?,id1:kotlin.String?,id2:kotlin.String?,context:OperationRequest):Response<ProtocolMapperRepresentation>
    /* realmClientScopesId1ProtocolMappersModelsId2Put
     * Update the mapper */
    suspend fun realmClientScopesId1ProtocolMappersModelsId2Put(realm:kotlin.String?,id1:kotlin.String?,id2:kotlin.String?,protocolMapperRepresentation:ProtocolMapperRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientScopesIdProtocolMappersAddModelsPost
     * Create multiple mappers */
    suspend fun realmClientScopesIdProtocolMappersAddModelsPost(realm:kotlin.String?,id:kotlin.String?,protocolMapperRepresentation:kotlin.Array<ProtocolMapperRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientScopesIdProtocolMappersModelsGet
     * Get mappers */
    suspend fun realmClientScopesIdProtocolMappersModelsGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientScopesIdProtocolMappersModelsPost
     * Create a mapper */
    suspend fun realmClientScopesIdProtocolMappersModelsPost(realm:kotlin.String?,id:kotlin.String?,protocolMapperRepresentation:ProtocolMapperRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientScopesIdProtocolMappersProtocolProtocolGet
     * Get mappers by name for a specific protocol */
    suspend fun realmClientScopesIdProtocolMappersProtocolProtocolGet(realm:kotlin.String?,id:kotlin.String?,protocol:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsId1ProtocolMappersModelsId2Delete
     * Delete the mapper */
    suspend fun realmClientsId1ProtocolMappersModelsId2Delete(realm:kotlin.String?,id1:kotlin.String?,id2:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmClientsId1ProtocolMappersModelsId2Get
     * Get mapper by id */
    suspend fun realmClientsId1ProtocolMappersModelsId2Get(realm:kotlin.String?,id1:kotlin.String?,id2:kotlin.String?,context:OperationRequest):Response<ProtocolMapperRepresentation>
    /* realmClientsId1ProtocolMappersModelsId2Put
     * Update the mapper */
    suspend fun realmClientsId1ProtocolMappersModelsId2Put(realm:kotlin.String?,id1:kotlin.String?,id2:kotlin.String?,protocolMapperRepresentation:ProtocolMapperRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientsIdProtocolMappersAddModelsPost
     * Create multiple mappers */
    suspend fun realmClientsIdProtocolMappersAddModelsPost(realm:kotlin.String?,id:kotlin.String?,protocolMapperRepresentation:kotlin.Array<ProtocolMapperRepresentation>?,context:OperationRequest):Response<Void>
    /* realmClientsIdProtocolMappersModelsGet
     * Get mappers */
    suspend fun realmClientsIdProtocolMappersModelsGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmClientsIdProtocolMappersModelsPost
     * Create a mapper */
    suspend fun realmClientsIdProtocolMappersModelsPost(realm:kotlin.String?,id:kotlin.String?,protocolMapperRepresentation:ProtocolMapperRepresentation?,context:OperationRequest):Response<Void>
    /* realmClientsIdProtocolMappersProtocolProtocolGet
     * Get mappers by name for a specific protocol */
    suspend fun realmClientsIdProtocolMappersProtocolProtocolGet(realm:kotlin.String?,id:kotlin.String?,protocol:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    companion object {
        const val address = "ProtocolMappersApi-service"
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
            for (m in ProtocolMappersApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(ProtocolMappersApi::class.java, address)
            return routerFactory
        }
    }
}
