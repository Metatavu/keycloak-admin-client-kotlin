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
import fi.metatavu.keycloak.adminclient.models.IdentityProviderMapperRepresentation
import fi.metatavu.keycloak.adminclient.models.IdentityProviderRepresentation
import fi.metatavu.keycloak.adminclient.models.ManagementPermissionReference

class IdentityProvidersApiVertxProxyHandler(private val vertx: Vertx, private val service: IdentityProvidersApi, topLevel: Boolean, private val timeoutSeconds: Long) : ProxyHandler() {
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
        
                "realmIdentityProviderImportConfigPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderImportConfigPost(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasDelete(realm,alias,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasExportGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    val format = ApiHandlerUtils.searchStringInJson(params,"format")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasExportGet(realm,alias,format,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasGet(realm,alias,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasManagementPermissionsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasManagementPermissionsGet(realm,alias,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasManagementPermissionsPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    val managementPermissionReferenceParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (managementPermissionReferenceParam == null) {
                        throw IllegalArgumentException("managementPermissionReference is required")
                    }
                    val managementPermissionReference = Gson().fromJson(managementPermissionReferenceParam.encode(), ManagementPermissionReference::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasManagementPermissionsPut(realm,alias,managementPermissionReference,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasMapperTypesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasMapperTypesGet(realm,alias,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasMappersGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasMappersGet(realm,alias,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasMappersIdDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasMappersIdDelete(realm,alias,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasMappersIdGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasMappersIdGet(realm,alias,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasMappersIdPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val identityProviderMapperRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (identityProviderMapperRepresentationParam == null) {
                        throw IllegalArgumentException("identityProviderMapperRepresentation is required")
                    }
                    val identityProviderMapperRepresentation = Gson().fromJson(identityProviderMapperRepresentationParam.encode(), IdentityProviderMapperRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasMappersIdPut(realm,alias,id,identityProviderMapperRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasMappersPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    val identityProviderMapperRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (identityProviderMapperRepresentationParam == null) {
                        throw IllegalArgumentException("identityProviderMapperRepresentation is required")
                    }
                    val identityProviderMapperRepresentation = Gson().fromJson(identityProviderMapperRepresentationParam.encode(), IdentityProviderMapperRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasMappersPost(realm,alias,identityProviderMapperRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesAliasPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    val identityProviderRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (identityProviderRepresentationParam == null) {
                        throw IllegalArgumentException("identityProviderRepresentation is required")
                    }
                    val identityProviderRepresentation = Gson().fromJson(identityProviderRepresentationParam.encode(), IdentityProviderRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesAliasPut(realm,alias,identityProviderRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderInstancesPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val identityProviderRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (identityProviderRepresentationParam == null) {
                        throw IllegalArgumentException("identityProviderRepresentation is required")
                    }
                    val identityProviderRepresentation = Gson().fromJson(identityProviderRepresentationParam.encode(), IdentityProviderRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderInstancesPost(realm,identityProviderRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmIdentityProviderProvidersProviderIdGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val providerId = ApiHandlerUtils.searchStringInJson(params,"provider_id")
                    if(providerId == null){
                        throw IllegalArgumentException("providerId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmIdentityProviderProvidersProviderIdGet(realm,providerId,context)
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
