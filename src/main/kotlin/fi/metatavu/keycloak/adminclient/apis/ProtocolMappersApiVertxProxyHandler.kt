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
import fi.metatavu.keycloak.adminclient.models.ProtocolMapperRepresentation

class ProtocolMappersApiVertxProxyHandler(private val vertx: Vertx, private val service: ProtocolMappersApi, topLevel: Boolean, private val timeoutSeconds: Long) : ProxyHandler() {
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
        
                "realmClientScopesId1ProtocolMappersModelsId2Delete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id1 = ApiHandlerUtils.searchStringInJson(params,"id1")
                    if(id1 == null){
                        throw IllegalArgumentException("id1 is required")
                    }
                    val id2 = ApiHandlerUtils.searchStringInJson(params,"id2")
                    if(id2 == null){
                        throw IllegalArgumentException("id2 is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesId1ProtocolMappersModelsId2Delete(realm,id1,id2,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesId1ProtocolMappersModelsId2Get" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id1 = ApiHandlerUtils.searchStringInJson(params,"id1")
                    if(id1 == null){
                        throw IllegalArgumentException("id1 is required")
                    }
                    val id2 = ApiHandlerUtils.searchStringInJson(params,"id2")
                    if(id2 == null){
                        throw IllegalArgumentException("id2 is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesId1ProtocolMappersModelsId2Get(realm,id1,id2,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesId1ProtocolMappersModelsId2Put" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id1 = ApiHandlerUtils.searchStringInJson(params,"id1")
                    if(id1 == null){
                        throw IllegalArgumentException("id1 is required")
                    }
                    val id2 = ApiHandlerUtils.searchStringInJson(params,"id2")
                    if(id2 == null){
                        throw IllegalArgumentException("id2 is required")
                    }
                    val protocolMapperRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (protocolMapperRepresentationParam == null) {
                        throw IllegalArgumentException("protocolMapperRepresentation is required")
                    }
                    val protocolMapperRepresentation = Gson().fromJson(protocolMapperRepresentationParam.encode(), ProtocolMapperRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesId1ProtocolMappersModelsId2Put(realm,id1,id2,protocolMapperRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdProtocolMappersAddModelsPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val protocolMapperRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(protocolMapperRepresentationParam == null){
                         throw IllegalArgumentException("protocolMapperRepresentation is required")
                    }
                    val protocolMapperRepresentation:kotlin.Array<ProtocolMapperRepresentation> = Gson().fromJson(protocolMapperRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<ProtocolMapperRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdProtocolMappersAddModelsPost(realm,id,protocolMapperRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdProtocolMappersModelsGet" -> {
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
                        val result = service.realmClientScopesIdProtocolMappersModelsGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdProtocolMappersModelsPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val protocolMapperRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (protocolMapperRepresentationParam == null) {
                        throw IllegalArgumentException("protocolMapperRepresentation is required")
                    }
                    val protocolMapperRepresentation = Gson().fromJson(protocolMapperRepresentationParam.encode(), ProtocolMapperRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdProtocolMappersModelsPost(realm,id,protocolMapperRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientScopesIdProtocolMappersProtocolProtocolGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val protocol = ApiHandlerUtils.searchStringInJson(params,"protocol")
                    if(protocol == null){
                        throw IllegalArgumentException("protocol is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientScopesIdProtocolMappersProtocolProtocolGet(realm,id,protocol,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsId1ProtocolMappersModelsId2Delete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id1 = ApiHandlerUtils.searchStringInJson(params,"id1")
                    if(id1 == null){
                        throw IllegalArgumentException("id1 is required")
                    }
                    val id2 = ApiHandlerUtils.searchStringInJson(params,"id2")
                    if(id2 == null){
                        throw IllegalArgumentException("id2 is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsId1ProtocolMappersModelsId2Delete(realm,id1,id2,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsId1ProtocolMappersModelsId2Get" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id1 = ApiHandlerUtils.searchStringInJson(params,"id1")
                    if(id1 == null){
                        throw IllegalArgumentException("id1 is required")
                    }
                    val id2 = ApiHandlerUtils.searchStringInJson(params,"id2")
                    if(id2 == null){
                        throw IllegalArgumentException("id2 is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsId1ProtocolMappersModelsId2Get(realm,id1,id2,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsId1ProtocolMappersModelsId2Put" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id1 = ApiHandlerUtils.searchStringInJson(params,"id1")
                    if(id1 == null){
                        throw IllegalArgumentException("id1 is required")
                    }
                    val id2 = ApiHandlerUtils.searchStringInJson(params,"id2")
                    if(id2 == null){
                        throw IllegalArgumentException("id2 is required")
                    }
                    val protocolMapperRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (protocolMapperRepresentationParam == null) {
                        throw IllegalArgumentException("protocolMapperRepresentation is required")
                    }
                    val protocolMapperRepresentation = Gson().fromJson(protocolMapperRepresentationParam.encode(), ProtocolMapperRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsId1ProtocolMappersModelsId2Put(realm,id1,id2,protocolMapperRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdProtocolMappersAddModelsPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val protocolMapperRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(protocolMapperRepresentationParam == null){
                         throw IllegalArgumentException("protocolMapperRepresentation is required")
                    }
                    val protocolMapperRepresentation:kotlin.Array<ProtocolMapperRepresentation> = Gson().fromJson(protocolMapperRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<ProtocolMapperRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdProtocolMappersAddModelsPost(realm,id,protocolMapperRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdProtocolMappersModelsGet" -> {
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
                        val result = service.realmClientsIdProtocolMappersModelsGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdProtocolMappersModelsPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val protocolMapperRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (protocolMapperRepresentationParam == null) {
                        throw IllegalArgumentException("protocolMapperRepresentation is required")
                    }
                    val protocolMapperRepresentation = Gson().fromJson(protocolMapperRepresentationParam.encode(), ProtocolMapperRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdProtocolMappersModelsPost(realm,id,protocolMapperRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdProtocolMappersProtocolProtocolGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val protocol = ApiHandlerUtils.searchStringInJson(params,"protocol")
                    if(protocol == null){
                        throw IllegalArgumentException("protocol is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdProtocolMappersProtocolProtocolGet(realm,id,protocol,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
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
