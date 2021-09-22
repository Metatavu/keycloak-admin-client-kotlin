package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.ComponentRepresentation
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


interface ComponentApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmComponentsGet
     *  */
    suspend fun realmComponentsGet(realm:kotlin.String?,name:kotlin.String?,parent:kotlin.String?,type:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmComponentsIdDelete
     *  */
    suspend fun realmComponentsIdDelete(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmComponentsIdGet
     *  */
    suspend fun realmComponentsIdGet(realm:kotlin.String?,id:kotlin.String?,context:OperationRequest):Response<ComponentRepresentation>
    /* realmComponentsIdPut
     *  */
    suspend fun realmComponentsIdPut(realm:kotlin.String?,id:kotlin.String?,componentRepresentation:ComponentRepresentation?,context:OperationRequest):Response<Void>
    /* realmComponentsIdSubComponentTypesGet
     * List of subcomponent types that are available to configure for a particular parent component. */
    suspend fun realmComponentsIdSubComponentTypesGet(realm:kotlin.String?,id:kotlin.String?,type:kotlin.String?,context:OperationRequest):Response<kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>>
    /* realmComponentsPost
     *  */
    suspend fun realmComponentsPost(realm:kotlin.String?,componentRepresentation:ComponentRepresentation?,context:OperationRequest):Response<Void>
    companion object {
        const val address = "ComponentApi-service"
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
            for (m in ComponentApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(ComponentApi::class.java, address)
            return routerFactory
        }
    }
}
