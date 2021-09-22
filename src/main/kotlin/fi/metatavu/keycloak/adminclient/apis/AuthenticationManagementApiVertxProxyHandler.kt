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
import fi.metatavu.keycloak.adminclient.models.AuthenticationExecutionInfoRepresentation
import fi.metatavu.keycloak.adminclient.models.AuthenticationExecutionRepresentation
import fi.metatavu.keycloak.adminclient.models.AuthenticationFlowRepresentation
import fi.metatavu.keycloak.adminclient.models.AuthenticatorConfigInfoRepresentation
import fi.metatavu.keycloak.adminclient.models.AuthenticatorConfigRepresentation
import fi.metatavu.keycloak.adminclient.models.RequiredActionProviderRepresentation

class AuthenticationManagementApiVertxProxyHandler(private val vertx: Vertx, private val service: AuthenticationManagementApi, topLevel: Boolean, private val timeoutSeconds: Long) : ProxyHandler() {
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
        
                "realmAuthenticationAuthenticatorProvidersGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationAuthenticatorProvidersGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationClientAuthenticatorProvidersGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationClientAuthenticatorProvidersGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationConfigDescriptionProviderIdGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val providerId = ApiHandlerUtils.searchStringInJson(params,"providerId")
                    if(providerId == null){
                        throw IllegalArgumentException("providerId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationConfigDescriptionProviderIdGet(realm,providerId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationConfigIdDelete" -> {
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
                        val result = service.realmAuthenticationConfigIdDelete(realm,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationConfigIdGet" -> {
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
                        val result = service.realmAuthenticationConfigIdGet(realm,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationConfigIdPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val authenticatorConfigRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (authenticatorConfigRepresentationParam == null) {
                        throw IllegalArgumentException("authenticatorConfigRepresentation is required")
                    }
                    val authenticatorConfigRepresentation = Gson().fromJson(authenticatorConfigRepresentationParam.encode(), AuthenticatorConfigRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationConfigIdPut(realm,id,authenticatorConfigRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationExecutionsExecutionIdConfigPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val executionId = ApiHandlerUtils.searchStringInJson(params,"executionId")
                    if(executionId == null){
                        throw IllegalArgumentException("executionId is required")
                    }
                    val authenticatorConfigRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (authenticatorConfigRepresentationParam == null) {
                        throw IllegalArgumentException("authenticatorConfigRepresentation is required")
                    }
                    val authenticatorConfigRepresentation = Gson().fromJson(authenticatorConfigRepresentationParam.encode(), AuthenticatorConfigRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationExecutionsExecutionIdConfigPost(realm,executionId,authenticatorConfigRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationExecutionsExecutionIdDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val executionId = ApiHandlerUtils.searchStringInJson(params,"executionId")
                    if(executionId == null){
                        throw IllegalArgumentException("executionId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationExecutionsExecutionIdDelete(realm,executionId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationExecutionsExecutionIdGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val executionId = ApiHandlerUtils.searchStringInJson(params,"executionId")
                    if(executionId == null){
                        throw IllegalArgumentException("executionId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationExecutionsExecutionIdGet(realm,executionId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationExecutionsExecutionIdLowerPriorityPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val executionId = ApiHandlerUtils.searchStringInJson(params,"executionId")
                    if(executionId == null){
                        throw IllegalArgumentException("executionId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationExecutionsExecutionIdLowerPriorityPost(realm,executionId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationExecutionsExecutionIdRaisePriorityPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val executionId = ApiHandlerUtils.searchStringInJson(params,"executionId")
                    if(executionId == null){
                        throw IllegalArgumentException("executionId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationExecutionsExecutionIdRaisePriorityPost(realm,executionId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationExecutionsPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val authenticationExecutionRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (authenticationExecutionRepresentationParam == null) {
                        throw IllegalArgumentException("authenticationExecutionRepresentation is required")
                    }
                    val authenticationExecutionRepresentation = Gson().fromJson(authenticationExecutionRepresentationParam.encode(), AuthenticationExecutionRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationExecutionsPost(realm,authenticationExecutionRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsFlowAliasCopyPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val flowAlias = ApiHandlerUtils.searchStringInJson(params,"flowAlias")
                    if(flowAlias == null){
                        throw IllegalArgumentException("flowAlias is required")
                    }
                    val requestBodyParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (requestBodyParam == null) {
                        throw IllegalArgumentException("requestBody is required")
                    }
                    val requestBody = Gson().fromJson(requestBodyParam.encode(), kotlin.collections.Map<kotlin.String, kotlin.Any>::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFlowsFlowAliasCopyPost(realm,flowAlias,requestBody,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsFlowAliasExecutionsExecutionPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val flowAlias = ApiHandlerUtils.searchStringInJson(params,"flowAlias")
                    if(flowAlias == null){
                        throw IllegalArgumentException("flowAlias is required")
                    }
                    val requestBodyParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (requestBodyParam == null) {
                        throw IllegalArgumentException("requestBody is required")
                    }
                    val requestBody = Gson().fromJson(requestBodyParam.encode(), kotlin.collections.Map<kotlin.String, kotlin.Any>::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFlowsFlowAliasExecutionsExecutionPost(realm,flowAlias,requestBody,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsFlowAliasExecutionsFlowPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val flowAlias = ApiHandlerUtils.searchStringInJson(params,"flowAlias")
                    if(flowAlias == null){
                        throw IllegalArgumentException("flowAlias is required")
                    }
                    val requestBodyParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (requestBodyParam == null) {
                        throw IllegalArgumentException("requestBody is required")
                    }
                    val requestBody = Gson().fromJson(requestBodyParam.encode(), kotlin.collections.Map<kotlin.String, kotlin.Any>::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFlowsFlowAliasExecutionsFlowPost(realm,flowAlias,requestBody,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsFlowAliasExecutionsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val flowAlias = ApiHandlerUtils.searchStringInJson(params,"flowAlias")
                    if(flowAlias == null){
                        throw IllegalArgumentException("flowAlias is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFlowsFlowAliasExecutionsGet(realm,flowAlias,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsFlowAliasExecutionsPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val flowAlias = ApiHandlerUtils.searchStringInJson(params,"flowAlias")
                    if(flowAlias == null){
                        throw IllegalArgumentException("flowAlias is required")
                    }
                    val authenticationExecutionInfoRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (authenticationExecutionInfoRepresentationParam == null) {
                        throw IllegalArgumentException("authenticationExecutionInfoRepresentation is required")
                    }
                    val authenticationExecutionInfoRepresentation = Gson().fromJson(authenticationExecutionInfoRepresentationParam.encode(), AuthenticationExecutionInfoRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFlowsFlowAliasExecutionsPut(realm,flowAlias,authenticationExecutionInfoRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFlowsGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsIdDelete" -> {
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
                        val result = service.realmAuthenticationFlowsIdDelete(realm,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsIdGet" -> {
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
                        val result = service.realmAuthenticationFlowsIdGet(realm,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsIdPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val authenticationFlowRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (authenticationFlowRepresentationParam == null) {
                        throw IllegalArgumentException("authenticationFlowRepresentation is required")
                    }
                    val authenticationFlowRepresentation = Gson().fromJson(authenticationFlowRepresentationParam.encode(), AuthenticationFlowRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFlowsIdPut(realm,id,authenticationFlowRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFlowsPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val authenticationFlowRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (authenticationFlowRepresentationParam == null) {
                        throw IllegalArgumentException("authenticationFlowRepresentation is required")
                    }
                    val authenticationFlowRepresentation = Gson().fromJson(authenticationFlowRepresentationParam.encode(), AuthenticationFlowRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFlowsPost(realm,authenticationFlowRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFormActionProvidersGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFormActionProvidersGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationFormProvidersGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationFormProvidersGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationPerClientConfigDescriptionGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationPerClientConfigDescriptionGet(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationRegisterRequiredActionPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val requestBodyParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (requestBodyParam == null) {
                        throw IllegalArgumentException("requestBody is required")
                    }
                    val requestBody = Gson().fromJson(requestBodyParam.encode(), kotlin.collections.Map<kotlin.String, kotlin.Any>::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationRegisterRequiredActionPost(realm,requestBody,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationRequiredActionsAliasDelete" -> {
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
                        val result = service.realmAuthenticationRequiredActionsAliasDelete(realm,alias,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationRequiredActionsAliasGet" -> {
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
                        val result = service.realmAuthenticationRequiredActionsAliasGet(realm,alias,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationRequiredActionsAliasLowerPriorityPost" -> {
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
                        val result = service.realmAuthenticationRequiredActionsAliasLowerPriorityPost(realm,alias,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationRequiredActionsAliasPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val alias = ApiHandlerUtils.searchStringInJson(params,"alias")
                    if(alias == null){
                        throw IllegalArgumentException("alias is required")
                    }
                    val requiredActionProviderRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (requiredActionProviderRepresentationParam == null) {
                        throw IllegalArgumentException("requiredActionProviderRepresentation is required")
                    }
                    val requiredActionProviderRepresentation = Gson().fromJson(requiredActionProviderRepresentationParam.encode(), RequiredActionProviderRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationRequiredActionsAliasPut(realm,alias,requiredActionProviderRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationRequiredActionsAliasRaisePriorityPost" -> {
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
                        val result = service.realmAuthenticationRequiredActionsAliasRaisePriorityPost(realm,alias,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationRequiredActionsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationRequiredActionsGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAuthenticationUnregisteredRequiredActionsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAuthenticationUnregisteredRequiredActionsGet(realm,context)
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
