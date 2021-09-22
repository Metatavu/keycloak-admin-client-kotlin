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
import fi.metatavu.keycloak.adminclient.models.CredentialRepresentation
import fi.metatavu.keycloak.adminclient.models.FederatedIdentityRepresentation
import fi.metatavu.keycloak.adminclient.models.UserRepresentation

class UsersApiVertxProxyHandler(private val vertx: Vertx, private val service: UsersApi, topLevel: Boolean, private val timeoutSeconds: Long) : ProxyHandler() {
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
        
                "realmUsersCountGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val email = ApiHandlerUtils.searchStringInJson(params,"email")
                    val emailVerified = ApiHandlerUtils.searchStringInJson(params,"emailVerified")?.toBoolean()
                    val firstName = ApiHandlerUtils.searchStringInJson(params,"firstName")
                    val lastName = ApiHandlerUtils.searchStringInJson(params,"lastName")
                    val search = ApiHandlerUtils.searchStringInJson(params,"search")
                    val username = ApiHandlerUtils.searchStringInJson(params,"username")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersCountGet(realm,email,emailVerified,firstName,lastName,search,username,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val briefRepresentation = ApiHandlerUtils.searchStringInJson(params,"briefRepresentation")?.toBoolean()
                    val email = ApiHandlerUtils.searchStringInJson(params,"email")
                    val emailVerified = ApiHandlerUtils.searchStringInJson(params,"emailVerified")?.toBoolean()
                    val enabled = ApiHandlerUtils.searchStringInJson(params,"enabled")?.toBoolean()
                    val exact = ApiHandlerUtils.searchStringInJson(params,"exact")?.toBoolean()
                    val first = ApiHandlerUtils.searchIntegerInJson(params,"first")
                    val firstName = ApiHandlerUtils.searchStringInJson(params,"firstName")
                    val idpAlias = ApiHandlerUtils.searchStringInJson(params,"idpAlias")
                    val idpUserId = ApiHandlerUtils.searchStringInJson(params,"idpUserId")
                    val lastName = ApiHandlerUtils.searchStringInJson(params,"lastName")
                    val max = ApiHandlerUtils.searchIntegerInJson(params,"max")
                    val search = ApiHandlerUtils.searchStringInJson(params,"search")
                    val username = ApiHandlerUtils.searchStringInJson(params,"username")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersGet(realm,briefRepresentation,email,emailVerified,enabled,exact,first,firstName,idpAlias,idpUserId,lastName,max,search,username,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdConfiguredUserStorageCredentialTypesGet" -> {
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
                        val result = service.realmUsersIdConfiguredUserStorageCredentialTypesGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdConsentsClientDelete" -> {
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
                        val result = service.realmUsersIdConsentsClientDelete(realm,id,client,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdConsentsGet" -> {
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
                        val result = service.realmUsersIdConsentsGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdCredentialsCredentialIdDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val credentialId = ApiHandlerUtils.searchStringInJson(params,"credentialId")
                    if(credentialId == null){
                        throw IllegalArgumentException("credentialId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdCredentialsCredentialIdDelete(realm,id,credentialId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdCredentialsCredentialIdMoveAfterNewPreviousCredentialIdPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val credentialId = ApiHandlerUtils.searchStringInJson(params,"credentialId")
                    if(credentialId == null){
                        throw IllegalArgumentException("credentialId is required")
                    }
                    val newPreviousCredentialId = ApiHandlerUtils.searchStringInJson(params,"newPreviousCredentialId")
                    if(newPreviousCredentialId == null){
                        throw IllegalArgumentException("newPreviousCredentialId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdCredentialsCredentialIdMoveAfterNewPreviousCredentialIdPost(realm,id,credentialId,newPreviousCredentialId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdCredentialsCredentialIdMoveToFirstPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val credentialId = ApiHandlerUtils.searchStringInJson(params,"credentialId")
                    if(credentialId == null){
                        throw IllegalArgumentException("credentialId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdCredentialsCredentialIdMoveToFirstPost(realm,id,credentialId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdCredentialsCredentialIdUserLabelPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val credentialId = ApiHandlerUtils.searchStringInJson(params,"credentialId")
                    if(credentialId == null){
                        throw IllegalArgumentException("credentialId is required")
                    }
                    val body = ApiHandlerUtils.searchStringInJson(params,"body")
                    if(body == null){
                        throw IllegalArgumentException("body is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdCredentialsCredentialIdUserLabelPut(realm,id,credentialId,body,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdCredentialsGet" -> {
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
                        val result = service.realmUsersIdCredentialsGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdDelete" -> {
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
                        val result = service.realmUsersIdDelete(realm,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdDisableCredentialTypesPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val requestBodyParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(requestBodyParam == null){
                         throw IllegalArgumentException("requestBody is required")
                    }
                    val requestBody:kotlin.Array<kotlin.String> = Gson().fromJson(requestBodyParam.encode()
                            , object : TypeToken<kotlin.collections.List<kotlin.String>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdDisableCredentialTypesPut(realm,id,requestBody,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdExecuteActionsEmailPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val requestBodyParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(requestBodyParam == null){
                         throw IllegalArgumentException("requestBody is required")
                    }
                    val requestBody:kotlin.Array<kotlin.String> = Gson().fromJson(requestBodyParam.encode()
                            , object : TypeToken<kotlin.collections.List<kotlin.String>>(){}.type)
                    val clientId = ApiHandlerUtils.searchStringInJson(params,"client_id")
                    val lifespan = ApiHandlerUtils.searchIntegerInJson(params,"lifespan")
                    val redirectUri = ApiHandlerUtils.searchStringInJson(params,"redirect_uri")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdExecuteActionsEmailPut(realm,id,requestBody,clientId,lifespan,redirectUri,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdFederatedIdentityGet" -> {
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
                        val result = service.realmUsersIdFederatedIdentityGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdFederatedIdentityProviderDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val provider = ApiHandlerUtils.searchStringInJson(params,"provider")
                    if(provider == null){
                        throw IllegalArgumentException("provider is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdFederatedIdentityProviderDelete(realm,id,provider,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdFederatedIdentityProviderPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val provider = ApiHandlerUtils.searchStringInJson(params,"provider")
                    if(provider == null){
                        throw IllegalArgumentException("provider is required")
                    }
                    val federatedIdentityRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (federatedIdentityRepresentationParam == null) {
                        throw IllegalArgumentException("federatedIdentityRepresentation is required")
                    }
                    val federatedIdentityRepresentation = Gson().fromJson(federatedIdentityRepresentationParam.encode(), FederatedIdentityRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdFederatedIdentityProviderPost(realm,id,provider,federatedIdentityRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdGet" -> {
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
                        val result = service.realmUsersIdGet(realm,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdGroupsCountGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val search = ApiHandlerUtils.searchStringInJson(params,"search")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdGroupsCountGet(realm,id,search,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdGroupsGet" -> {
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
                    val first = ApiHandlerUtils.searchIntegerInJson(params,"first")
                    val max = ApiHandlerUtils.searchIntegerInJson(params,"max")
                    val search = ApiHandlerUtils.searchStringInJson(params,"search")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdGroupsGet(realm,id,briefRepresentation,first,max,search,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdGroupsGroupIdDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val groupId = ApiHandlerUtils.searchStringInJson(params,"groupId")
                    if(groupId == null){
                        throw IllegalArgumentException("groupId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdGroupsGroupIdDelete(realm,id,groupId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdGroupsGroupIdPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val groupId = ApiHandlerUtils.searchStringInJson(params,"groupId")
                    if(groupId == null){
                        throw IllegalArgumentException("groupId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdGroupsGroupIdPut(realm,id,groupId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdImpersonationPost" -> {
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
                        val result = service.realmUsersIdImpersonationPost(realm,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdLogoutPost" -> {
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
                        val result = service.realmUsersIdLogoutPost(realm,id,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdOfflineSessionsClientUuidGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val clientUuid = ApiHandlerUtils.searchStringInJson(params,"clientUuid")
                    if(clientUuid == null){
                        throw IllegalArgumentException("clientUuid is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdOfflineSessionsClientUuidGet(realm,id,clientUuid,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val userRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (userRepresentationParam == null) {
                        throw IllegalArgumentException("userRepresentation is required")
                    }
                    val userRepresentation = Gson().fromJson(userRepresentationParam.encode(), UserRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdPut(realm,id,userRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdResetPasswordPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val credentialRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (credentialRepresentationParam == null) {
                        throw IllegalArgumentException("credentialRepresentation is required")
                    }
                    val credentialRepresentation = Gson().fromJson(credentialRepresentationParam.encode(), CredentialRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdResetPasswordPut(realm,id,credentialRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdSendVerifyEmailPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val clientId = ApiHandlerUtils.searchStringInJson(params,"client_id")
                    val redirectUri = ApiHandlerUtils.searchStringInJson(params,"redirect_uri")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersIdSendVerifyEmailPut(realm,id,clientId,redirectUri,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersIdSessionsGet" -> {
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
                        val result = service.realmUsersIdSessionsGet(realm,id,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val userRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (userRepresentationParam == null) {
                        throw IllegalArgumentException("userRepresentation is required")
                    }
                    val userRepresentation = Gson().fromJson(userRepresentationParam.encode(), UserRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersPost(realm,userRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersProfileGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersProfileGet(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersProfilePut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val body = ApiHandlerUtils.searchStringInJson(params,"body")
                    if(body == null){
                        throw IllegalArgumentException("body is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersProfilePut(realm,body,context)
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
