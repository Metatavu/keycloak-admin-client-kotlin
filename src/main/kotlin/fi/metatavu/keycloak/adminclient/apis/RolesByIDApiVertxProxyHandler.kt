package fi.metatavu.keycloak.adminclient.apis

import io.vertx.core.Vertx
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.api.OperationRequest
import io.vertx.ext.web.api.OperationResponse
import io.vertx.ext.web.api.generator.ApiHandlerUtils
import io.vertx.serviceproxy.ProxyHandler
import io.vertx.serviceproxy.ServiceException
import io.vertx.serviceproxy.ServiceExceptionMessageCodec
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import io.vertx.kotlin.coroutines.dispatcher
import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import fi.metatavu.keycloak.adminclient.models.ManagementPermissionReference
import fi.metatavu.keycloak.adminclient.models.RoleRepresentation

class RolesByIDApiVertxProxyHandler(private val vertx: Vertx, private val service: RolesByIDApi, topLevel: Boolean, private val timeoutSeconds: Long) : ProxyHandler() {
    private val timerID: Long
    private var lastAccessed: Long = 0
    init {
        try {
            this.vertx.eventBus().registerDefaultCodec(ServiceException::class.java,
            ServiceExceptionMessageCodec())
        } catch (ex: IllegalStateException) {}

        if (timeoutSeconds != (-1).toLong() && !topLevel) {
            var period = timeoutSeconds * 1000 / 2
            if (period > 10000) {
                period = 10000
            }
            this.timerID = vertx.setPeriodic(period) { this.checkTimedOut(it) }
        } else {
            this.timerID = -1
        }
        accessed()
    }
    private fun checkTimedOut(id: Long) {
        val now = System.nanoTime()
        if (now - lastAccessed > timeoutSeconds * 1000000000) {
            close()
        }
    }

    override fun close() {
        if (timerID != (-1).toLong()) {
            vertx.cancelTimer(timerID)
        }
        super.close()
    }

    private fun accessed() {
        this.lastAccessed = System.nanoTime()
    }
    override fun handle(msg: Message<JsonObject>) {
        try {
            val json = msg.body()
            val action = msg.headers().get("action") ?: throw IllegalStateException("action not specified")
            accessed()
            val contextSerialized = json.getJsonObject("context") ?: throw IllegalStateException("Received action $action without OperationRequest \"context\"")
            val context = OperationRequest(contextSerialized)
            when (action) {
        
                "realmRolesByIdRoleIdCompositesClientsClientUuidGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    val clientUuid = ApiHandlerUtils.searchStringInJson(params,"clientUuid")
                    if(clientUuid == null){
                        throw IllegalArgumentException("clientUuid is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdCompositesClientsClientUuidGet(realm,roleId,clientUuid,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesByIdRoleIdCompositesDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdCompositesDelete(realm,roleId,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesByIdRoleIdCompositesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdCompositesGet(realm,roleId,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesByIdRoleIdCompositesPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdCompositesPost(realm,roleId,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesByIdRoleIdCompositesRealmGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdCompositesRealmGet(realm,roleId,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesByIdRoleIdDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdDelete(realm,roleId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesByIdRoleIdGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdGet(realm,roleId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesByIdRoleIdManagementPermissionsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdManagementPermissionsGet(realm,roleId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesByIdRoleIdManagementPermissionsPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    val managementPermissionReferenceParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (managementPermissionReferenceParam == null) {
                        throw IllegalArgumentException("managementPermissionReference is required")
                    }
                    val managementPermissionReference = Gson().fromJson(managementPermissionReferenceParam.encode(), ManagementPermissionReference::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdManagementPermissionsPut(realm,roleId,managementPermissionReference,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesByIdRoleIdPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleId = ApiHandlerUtils.searchStringInJson(params,"role-id")
                    if(roleId == null){
                        throw IllegalArgumentException("roleId is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (roleRepresentationParam == null) {
                        throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation = Gson().fromJson(roleRepresentationParam.encode(), RoleRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesByIdRoleIdPut(realm,roleId,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
            }
        }catch (t: Throwable) {
            msg.reply(ServiceException(500, t.message))
            throw t
        }
    }
}
