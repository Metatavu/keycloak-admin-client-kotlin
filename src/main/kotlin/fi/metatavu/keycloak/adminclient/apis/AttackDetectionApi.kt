package fi.metatavu.keycloak.adminclient.apis

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


interface AttackDetectionApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmAttackDetectionBruteForceUsersDelete
     * Clear any user login failures for all users   This can release temporary disabled users */
    suspend fun realmAttackDetectionBruteForceUsersDelete(realm:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAttackDetectionBruteForceUsersUserIdDelete
     * Clear any user login failures for the user   This can release temporary disabled user */
    suspend fun realmAttackDetectionBruteForceUsersUserIdDelete(realm:kotlin.String?,userId:kotlin.String?,context:OperationRequest):Response<Void>
    /* realmAttackDetectionBruteForceUsersUserIdGet
     * Get status of a username in brute force detection */
    suspend fun realmAttackDetectionBruteForceUsersUserIdGet(realm:kotlin.String?,userId:kotlin.String?,context:OperationRequest):Response<kotlin.collections.Map<kotlin.String, kotlin.Any>>
    companion object {
        const val address = "AttackDetectionApi-service"
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
            for (m in AttackDetectionApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(AttackDetectionApi::class.java, address)
            return routerFactory
        }
    }
}
