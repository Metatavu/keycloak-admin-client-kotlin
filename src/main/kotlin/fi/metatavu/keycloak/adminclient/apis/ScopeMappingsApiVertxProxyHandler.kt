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
import fi.metatavu.keycloak.adminclient.models.RoleRepresentation

class ScopeMappingsApiVertxProxyHandler(private val vertx: Vertx, private val service: ScopeMappingsApi, topLevel: Boolean, private val timeoutSeconds: Long) : ProxyHandler() {
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
        
                "realmClientScopesIdScopeMappingsClientsClientAvailableGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsClientsClientAvailableGet(realm,id,client,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdScopeMappingsClientsClientCompositeGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    val briefRepresentation = ApiHandlerUtils.searchStringInJson(params,"briefRepresentation")?.toBoolean()
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsClientsClientCompositeGet(realm,id,client,briefRepresentation,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdScopeMappingsClientsClientDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsClientsClientDelete(realm,id,client,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdScopeMappingsClientsClientGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsClientsClientGet(realm,id,client,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdScopeMappingsClientsClientPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsClientsClientPost(realm,id,client,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdScopeMappingsRealmAvailableGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsRealmAvailableGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdScopeMappingsRealmCompositeGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val briefRepresentation = ApiHandlerUtils.searchStringInJson(params,"briefRepresentation")?.toBoolean()
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsRealmCompositeGet(realm,id,briefRepresentation,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdScopeMappingsRealmDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsRealmDelete(realm,id,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdScopeMappingsRealmGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsRealmGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdScopeMappingsRealmPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdScopeMappingsRealmPost(realm,id,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsClientsClientAvailableGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsClientsClientAvailableGet(realm,id,client,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsClientsClientCompositeGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    val briefRepresentation = ApiHandlerUtils.searchStringInJson(params,"briefRepresentation")?.toBoolean()
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsClientsClientCompositeGet(realm,id,client,briefRepresentation,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsClientsClientDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsClientsClientDelete(realm,id,client,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsClientsClientGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsClientsClientGet(realm,id,client,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsClientsClientPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    if(client == null){
                        throw IllegalArgumentException("client is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsClientsClientPost(realm,id,client,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsRealmAvailableGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsRealmAvailableGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsRealmCompositeGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val briefRepresentation = ApiHandlerUtils.searchStringInJson(params,"briefRepresentation")?.toBoolean()
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsRealmCompositeGet(realm,id,briefRepresentation,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsRealmDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsRealmDelete(realm,id,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsRealmGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsRealmGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdScopeMappingsRealmPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdScopeMappingsRealmPost(realm,id,roleRepresentation,context)
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
