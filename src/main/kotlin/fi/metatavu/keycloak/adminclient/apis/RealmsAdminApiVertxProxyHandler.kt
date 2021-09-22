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
import fi.metatavu.keycloak.adminclient.models.ClientPoliciesRepresentation
import fi.metatavu.keycloak.adminclient.models.ClientProfilesRepresentation
import fi.metatavu.keycloak.adminclient.models.ClientRepresentation
import fi.metatavu.keycloak.adminclient.models.GroupRepresentation
import fi.metatavu.keycloak.adminclient.models.ManagementPermissionReference
import fi.metatavu.keycloak.adminclient.models.PartialImportRepresentation
import fi.metatavu.keycloak.adminclient.models.RealmEventsConfigRepresentation
import fi.metatavu.keycloak.adminclient.models.RealmRepresentation
import fi.metatavu.keycloak.adminclient.models.TestLdapConnectionRepresentation

class RealmsAdminApiVertxProxyHandler(private val vertx: Vertx, private val service: RealmsAdminApi, topLevel: Boolean, private val timeoutSeconds: Long) : ProxyHandler() {
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
        
                "realmAdminEventsDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAdminEventsDelete(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmAdminEventsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val authClient = ApiHandlerUtils.searchStringInJson(params,"authClient")
                    val authIpAddress = ApiHandlerUtils.searchStringInJson(params,"authIpAddress")
                    val authRealm = ApiHandlerUtils.searchStringInJson(params,"authRealm")
                    val authUser = ApiHandlerUtils.searchStringInJson(params,"authUser")
                    val dateFrom = ApiHandlerUtils.searchStringInJson(params,"dateFrom")
                    val dateTo = ApiHandlerUtils.searchStringInJson(params,"dateTo")
                    val first = ApiHandlerUtils.searchIntegerInJson(params,"first")
                    val max = ApiHandlerUtils.searchIntegerInJson(params,"max")
                    val operationTypesParam = ApiHandlerUtils.searchJsonArrayInJson(params,"operationTypes")
                    val operationTypes:kotlin.Array<kotlin.String>? = if(operationTypesParam == null) null
                            else Gson().fromJson(operationTypesParam.encode(),
                            , object : TypeToken<kotlin.collections.List<kotlin.String>>(){}.type)
                    val resourcePath = ApiHandlerUtils.searchStringInJson(params,"resourcePath")
                    val resourceTypesParam = ApiHandlerUtils.searchJsonArrayInJson(params,"resourceTypes")
                    val resourceTypes:kotlin.Array<kotlin.String>? = if(resourceTypesParam == null) null
                            else Gson().fromJson(resourceTypesParam.encode(),
                            , object : TypeToken<kotlin.collections.List<kotlin.String>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmAdminEventsGet(realm,authClient,authIpAddress,authRealm,authUser,dateFrom,dateTo,first,max,operationTypes,resourcePath,resourceTypes,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClearKeysCachePost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClearKeysCachePost(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClearRealmCachePost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClearRealmCachePost(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClearUserCachePost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClearUserCachePost(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientDescriptionConverterPost" -> {
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
                        val result = service.realmClientDescriptionConverterPost(realm,body,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientPoliciesPoliciesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientPoliciesPoliciesGet(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientPoliciesPoliciesPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val clientPoliciesRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (clientPoliciesRepresentationParam == null) {
                        throw IllegalArgumentException("clientPoliciesRepresentation is required")
                    }
                    val clientPoliciesRepresentation = Gson().fromJson(clientPoliciesRepresentationParam.encode(), ClientPoliciesRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientPoliciesPoliciesPut(realm,clientPoliciesRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientPoliciesProfilesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val includeGlobalProfiles = ApiHandlerUtils.searchStringInJson(params,"include-global-profiles")?.toBoolean()
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientPoliciesProfilesGet(realm,includeGlobalProfiles,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientPoliciesProfilesPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val clientProfilesRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (clientProfilesRepresentationParam == null) {
                        throw IllegalArgumentException("clientProfilesRepresentation is required")
                    }
                    val clientProfilesRepresentation = Gson().fromJson(clientProfilesRepresentationParam.encode(), ClientProfilesRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientPoliciesProfilesPut(realm,clientProfilesRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientSessionStatsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientSessionStatsGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmCredentialRegistratorsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmCredentialRegistratorsGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDefaultDefaultClientScopesClientScopeIdDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val clientScopeId = ApiHandlerUtils.searchStringInJson(params,"clientScopeId")
                    if(clientScopeId == null){
                        throw IllegalArgumentException("clientScopeId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDefaultDefaultClientScopesClientScopeIdDelete(realm,clientScopeId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDefaultDefaultClientScopesClientScopeIdPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val clientScopeId = ApiHandlerUtils.searchStringInJson(params,"clientScopeId")
                    if(clientScopeId == null){
                        throw IllegalArgumentException("clientScopeId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDefaultDefaultClientScopesClientScopeIdPut(realm,clientScopeId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDefaultDefaultClientScopesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDefaultDefaultClientScopesGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDefaultGroupsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDefaultGroupsGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDefaultGroupsGroupIdDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val groupId = ApiHandlerUtils.searchStringInJson(params,"groupId")
                    if(groupId == null){
                        throw IllegalArgumentException("groupId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDefaultGroupsGroupIdDelete(realm,groupId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDefaultGroupsGroupIdPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val groupId = ApiHandlerUtils.searchStringInJson(params,"groupId")
                    if(groupId == null){
                        throw IllegalArgumentException("groupId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDefaultGroupsGroupIdPut(realm,groupId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDefaultOptionalClientScopesClientScopeIdDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val clientScopeId = ApiHandlerUtils.searchStringInJson(params,"clientScopeId")
                    if(clientScopeId == null){
                        throw IllegalArgumentException("clientScopeId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDefaultOptionalClientScopesClientScopeIdDelete(realm,clientScopeId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDefaultOptionalClientScopesClientScopeIdPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val clientScopeId = ApiHandlerUtils.searchStringInJson(params,"clientScopeId")
                    if(clientScopeId == null){
                        throw IllegalArgumentException("clientScopeId is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDefaultOptionalClientScopesClientScopeIdPut(realm,clientScopeId,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDefaultOptionalClientScopesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDefaultOptionalClientScopesGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmDelete(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmEventsConfigGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmEventsConfigGet(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmEventsConfigPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val realmEventsConfigRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (realmEventsConfigRepresentationParam == null) {
                        throw IllegalArgumentException("realmEventsConfigRepresentation is required")
                    }
                    val realmEventsConfigRepresentation = Gson().fromJson(realmEventsConfigRepresentationParam.encode(), RealmEventsConfigRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmEventsConfigPut(realm,realmEventsConfigRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmEventsDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmEventsDelete(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmEventsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val client = ApiHandlerUtils.searchStringInJson(params,"client")
                    val dateFrom = ApiHandlerUtils.searchStringInJson(params,"dateFrom")
                    val dateTo = ApiHandlerUtils.searchStringInJson(params,"dateTo")
                    val first = ApiHandlerUtils.searchIntegerInJson(params,"first")
                    val ipAddress = ApiHandlerUtils.searchStringInJson(params,"ipAddress")
                    val max = ApiHandlerUtils.searchIntegerInJson(params,"max")
                    val typeParam = ApiHandlerUtils.searchJsonArrayInJson(params,"type")
                    val type:kotlin.Array<kotlin.String>? = if(typeParam == null) null
                            else Gson().fromJson(typeParam.encode(),
                            , object : TypeToken<kotlin.collections.List<kotlin.String>>(){}.type)
                    val user = ApiHandlerUtils.searchStringInJson(params,"user")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmEventsGet(realm,client,dateFrom,dateTo,first,ipAddress,max,type,user,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmGet(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmGroupByPathPathGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val path = ApiHandlerUtils.searchStringInJson(params,"path")
                    if(path == null){
                        throw IllegalArgumentException("path is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmGroupByPathPathGet(realm,path,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLdapServerCapabilitiesPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val testLdapConnectionRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (testLdapConnectionRepresentationParam == null) {
                        throw IllegalArgumentException("testLdapConnectionRepresentation is required")
                    }
                    val testLdapConnectionRepresentation = Gson().fromJson(testLdapConnectionRepresentationParam.encode(), TestLdapConnectionRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLdapServerCapabilitiesPost(realm,testLdapConnectionRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLocalizationGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLocalizationGet(realm,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLocalizationLocaleDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val locale = ApiHandlerUtils.searchStringInJson(params,"locale")
                    if(locale == null){
                        throw IllegalArgumentException("locale is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLocalizationLocaleDelete(realm,locale,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLocalizationLocaleGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val locale = ApiHandlerUtils.searchStringInJson(params,"locale")
                    if(locale == null){
                        throw IllegalArgumentException("locale is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLocalizationLocaleGet(realm,locale,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLocalizationLocaleKeyDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val locale = ApiHandlerUtils.searchStringInJson(params,"locale")
                    if(locale == null){
                        throw IllegalArgumentException("locale is required")
                    }
                    val key = ApiHandlerUtils.searchStringInJson(params,"key")
                    if(key == null){
                        throw IllegalArgumentException("key is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLocalizationLocaleKeyDelete(realm,locale,key,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLocalizationLocaleKeyGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val locale = ApiHandlerUtils.searchStringInJson(params,"locale")
                    if(locale == null){
                        throw IllegalArgumentException("locale is required")
                    }
                    val key = ApiHandlerUtils.searchStringInJson(params,"key")
                    if(key == null){
                        throw IllegalArgumentException("key is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLocalizationLocaleKeyGet(realm,locale,key,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLocalizationLocaleKeyPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val locale = ApiHandlerUtils.searchStringInJson(params,"locale")
                    if(locale == null){
                        throw IllegalArgumentException("locale is required")
                    }
                    val key = ApiHandlerUtils.searchStringInJson(params,"key")
                    if(key == null){
                        throw IllegalArgumentException("key is required")
                    }
                    val body = ApiHandlerUtils.searchStringInJson(params,"body")
                    if(body == null){
                        throw IllegalArgumentException("body is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLocalizationLocaleKeyPut(realm,locale,key,body,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLocalizationLocalePatch" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val locale = ApiHandlerUtils.searchStringInJson(params,"locale")
                    if(locale == null){
                        throw IllegalArgumentException("locale is required")
                    }
                    val requestBodyParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (requestBodyParam == null) {
                        throw IllegalArgumentException("requestBody is required")
                    }
                    val requestBody = Gson().fromJson(requestBodyParam.encode(), kotlin.collections.Map<kotlin.String, kotlin.Any>::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLocalizationLocalePatch(realm,locale,requestBody,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLocalizationLocalePost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val locale = ApiHandlerUtils.searchStringInJson(params,"locale")
                    if(locale == null){
                        throw IllegalArgumentException("locale is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLocalizationLocalePost(realm,locale,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmLogoutAllPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmLogoutAllPost(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmPartialExportPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val exportClients = ApiHandlerUtils.searchStringInJson(params,"exportClients")?.toBoolean()
                    val exportGroupsAndRoles = ApiHandlerUtils.searchStringInJson(params,"exportGroupsAndRoles")?.toBoolean()
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmPartialExportPost(realm,exportClients,exportGroupsAndRoles,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmPartialImportPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val partialImportRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (partialImportRepresentationParam == null) {
                        throw IllegalArgumentException("partialImportRepresentation is required")
                    }
                    val partialImportRepresentation = Gson().fromJson(partialImportRepresentationParam.encode(), PartialImportRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmPartialImportPost(realm,partialImportRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmPushRevocationPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmPushRevocationPost(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val realmRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (realmRepresentationParam == null) {
                        throw IllegalArgumentException("realmRepresentation is required")
                    }
                    val realmRepresentation = Gson().fromJson(realmRepresentationParam.encode(), RealmRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmPut(realm,realmRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmSessionsSessionDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val session = ApiHandlerUtils.searchStringInJson(params,"session")
                    if(session == null){
                        throw IllegalArgumentException("session is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmSessionsSessionDelete(realm,session,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmTestLDAPConnectionPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val testLdapConnectionRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (testLdapConnectionRepresentationParam == null) {
                        throw IllegalArgumentException("testLdapConnectionRepresentation is required")
                    }
                    val testLdapConnectionRepresentation = Gson().fromJson(testLdapConnectionRepresentationParam.encode(), TestLdapConnectionRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmTestLDAPConnectionPost(realm,testLdapConnectionRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmTestSMTPConnectionPost" -> {
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
                        val result = service.realmTestSMTPConnectionPost(realm,requestBody,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersManagementPermissionsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersManagementPermissionsGet(realm,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmUsersManagementPermissionsPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val managementPermissionReferenceParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (managementPermissionReferenceParam == null) {
                        throw IllegalArgumentException("managementPermissionReference is required")
                    }
                    val managementPermissionReference = Gson().fromJson(managementPermissionReferenceParam.encode(), ManagementPermissionReference::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmUsersManagementPermissionsPut(realm,managementPermissionReference,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "rootPost" -> {
                    val params = context.params
                    val realmRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (realmRepresentationParam == null) {
                        throw IllegalArgumentException("realmRepresentation is required")
                    }
                    val realmRepresentation = Gson().fromJson(realmRepresentationParam.encode(), RealmRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.rootPost(realmRepresentation,context)
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
