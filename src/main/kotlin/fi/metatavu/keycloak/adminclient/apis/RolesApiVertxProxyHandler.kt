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

class RolesApiVertxProxyHandler(private val vertx: Vertx, private val service: RolesApi, topLevel: Boolean, private val timeoutSeconds: Long) : ProxyHandler() {
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
        
                "realmClientsIdRolesGet" -> {
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
                        val result = service.realmClientsIdRolesGet(realm,id,briefRepresentation,first,max,search,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (roleRepresentationParam == null) {
                        throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation = Gson().fromJson(roleRepresentationParam.encode(), RoleRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesPost(realm,id,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameCompositesClientsClientUuidGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val clientUuid = ApiHandlerUtils.searchStringInJson(params,"clientUuid")
                    if(clientUuid == null){
                        throw IllegalArgumentException("clientUuid is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameCompositesClientsClientUuidGet(realm,id,roleName,clientUuid,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameCompositesDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameCompositesDelete(realm,id,roleName,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameCompositesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameCompositesGet(realm,id,roleName,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameCompositesPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameCompositesPost(realm,id,roleName,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameCompositesRealmGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameCompositesRealmGet(realm,id,roleName,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameDelete(realm,id,roleName,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameGet(realm,id,roleName,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameGroupsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val briefRepresentation = ApiHandlerUtils.searchStringInJson(params,"briefRepresentation")?.toBoolean()
                    val first = ApiHandlerUtils.searchIntegerInJson(params,"first")
                    val max = ApiHandlerUtils.searchIntegerInJson(params,"max")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameGroupsGet(realm,id,roleName,briefRepresentation,first,max,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameManagementPermissionsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameManagementPermissionsGet(realm,id,roleName,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameManagementPermissionsPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val managementPermissionReferenceParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (managementPermissionReferenceParam == null) {
                        throw IllegalArgumentException("managementPermissionReference is required")
                    }
                    val managementPermissionReference = Gson().fromJson(managementPermissionReferenceParam.encode(), ManagementPermissionReference::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameManagementPermissionsPut(realm,id,roleName,managementPermissionReference,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNamePut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (roleRepresentationParam == null) {
                        throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation = Gson().fromJson(roleRepresentationParam.encode(), RoleRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNamePut(realm,id,roleName,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmClientsIdRolesRoleNameUsersGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val id = ApiHandlerUtils.searchStringInJson(params,"id")
                    if(id == null){
                        throw IllegalArgumentException("id is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val first = ApiHandlerUtils.searchIntegerInJson(params,"first")
                    val max = ApiHandlerUtils.searchIntegerInJson(params,"max")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmClientsIdRolesRoleNameUsersGet(realm,id,roleName,first,max,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val briefRepresentation = ApiHandlerUtils.searchStringInJson(params,"briefRepresentation")?.toBoolean()
                    val first = ApiHandlerUtils.searchIntegerInJson(params,"first")
                    val max = ApiHandlerUtils.searchIntegerInJson(params,"max")
                    val search = ApiHandlerUtils.searchStringInJson(params,"search")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesGet(realm,briefRepresentation,first,max,search,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (roleRepresentationParam == null) {
                        throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation = Gson().fromJson(roleRepresentationParam.encode(), RoleRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesPost(realm,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameCompositesClientsClientUuidGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val clientUuid = ApiHandlerUtils.searchStringInJson(params,"clientUuid")
                    if(clientUuid == null){
                        throw IllegalArgumentException("clientUuid is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameCompositesClientsClientUuidGet(realm,roleName,clientUuid,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameCompositesDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameCompositesDelete(realm,roleName,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameCompositesGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameCompositesGet(realm,roleName,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameCompositesPost" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonArrayInJson(params,"body")
                    if(roleRepresentationParam == null){
                         throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation:kotlin.Array<RoleRepresentation> = Gson().fromJson(roleRepresentationParam.encode()
                            , object : TypeToken<kotlin.collections.List<RoleRepresentation>>(){}.type)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameCompositesPost(realm,roleName,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameCompositesRealmGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameCompositesRealmGet(realm,roleName,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameDelete" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameDelete(realm,roleName,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameGet(realm,roleName,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameGroupsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val briefRepresentation = ApiHandlerUtils.searchStringInJson(params,"briefRepresentation")?.toBoolean()
                    val first = ApiHandlerUtils.searchIntegerInJson(params,"first")
                    val max = ApiHandlerUtils.searchIntegerInJson(params,"max")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameGroupsGet(realm,roleName,briefRepresentation,first,max,context)
                        val payload = JsonArray(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameManagementPermissionsGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameManagementPermissionsGet(realm,roleName,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameManagementPermissionsPut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val managementPermissionReferenceParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (managementPermissionReferenceParam == null) {
                        throw IllegalArgumentException("managementPermissionReference is required")
                    }
                    val managementPermissionReference = Gson().fromJson(managementPermissionReferenceParam.encode(), ManagementPermissionReference::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameManagementPermissionsPut(realm,roleName,managementPermissionReference,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNamePut" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val roleRepresentationParam = ApiHandlerUtils.searchJsonObjectInJson(params,"body")
                    if (roleRepresentationParam == null) {
                        throw IllegalArgumentException("roleRepresentation is required")
                    }
                    val roleRepresentation = Gson().fromJson(roleRepresentationParam.encode(), RoleRepresentation::class.java)
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNamePut(realm,roleName,roleRepresentation,context)
                        val payload = JsonObject(Json.encode(result.payload)).toBuffer()
                        val res = OperationResponse(result.statusCode,result.statusMessage,payload,result.headers)
                        msg.reply(res.toJson())
                    }.invokeOnCompletion{
                        it?.let{ throw it }
                    }
                }
        
                "realmRolesRoleNameUsersGet" -> {
                    val params = context.params
                    val realm = ApiHandlerUtils.searchStringInJson(params,"realm")
                    if(realm == null){
                        throw IllegalArgumentException("realm is required")
                    }
                    val roleName = ApiHandlerUtils.searchStringInJson(params,"role-name")
                    if(roleName == null){
                        throw IllegalArgumentException("roleName is required")
                    }
                    val first = ApiHandlerUtils.searchIntegerInJson(params,"first")
                    val max = ApiHandlerUtils.searchIntegerInJson(params,"max")
                    GlobalScope.launch(vertx.dispatcher()){
                        val result = service.realmRolesRoleNameUsersGet(realm,roleName,first,max,context)
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
