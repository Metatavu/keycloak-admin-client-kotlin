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
import fi.metatavu.keycloak.adminclient.models.CertificateRepresentation
import fi.metatavu.keycloak.adminclient.models.KeyStoreConfig

class ClientAttributeCertificateApiVertxProxyHandler(private val vertx: Vertx, private val service: ClientAttributeCertificateApi, topLevel: Boolean, private val timeoutSeconds: Long) : ProxyHandler() {
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
        
                "realmClientsIdCertificatesAttrDownloadPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val attr = ApiHandlerUtils.searchStringInJson(params,"attr")
                    if(attr == null){
                        throw IllegalArgumentException("attr is required")
                    }
                    val keyStoreConfigParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (keyStoreConfigParam == null) {
                        throw IllegalArgumentException("keyStoreConfig is required")
                    }
                    val keyStoreConfig = Gson().fromJson(keyStoreConfigParam.encode(), KeyStoreConfig::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdCertificatesAttrDownloadPost(realm,id,attr,keyStoreConfig,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdCertificatesAttrGenerateAndDownloadPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val attr = ApiHandlerUtils.searchStringInJson(params,"attr")
                    if(attr == null){
                        throw IllegalArgumentException("attr is required")
                    }
                    val keyStoreConfigParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (keyStoreConfigParam == null) {
                        throw IllegalArgumentException("keyStoreConfig is required")
                    }
                    val keyStoreConfig = Gson().fromJson(keyStoreConfigParam.encode(), KeyStoreConfig::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdCertificatesAttrGenerateAndDownloadPost(realm,id,attr,keyStoreConfig,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdCertificatesAttrGeneratePost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val attr = ApiHandlerUtils.searchStringInJson(params,"attr")
                    if(attr == null){
                        throw IllegalArgumentException("attr is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdCertificatesAttrGeneratePost(realm,id,attr,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdCertificatesAttrGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val attr = ApiHandlerUtils.searchStringInJson(params,"attr")
                    if(attr == null){
                        throw IllegalArgumentException("attr is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdCertificatesAttrGet(realm,id,attr,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdCertificatesAttrUploadCertificatePost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val attr = ApiHandlerUtils.searchStringInJson(params,"attr")
                    if(attr == null){
                        throw IllegalArgumentException("attr is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdCertificatesAttrUploadCertificatePost(realm,id,attr,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdCertificatesAttrUploadPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val attr = ApiHandlerUtils.searchStringInJson(params,"attr")
                    if(attr == null){
                        throw IllegalArgumentException("attr is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdCertificatesAttrUploadPost(realm,id,attr,context)
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
