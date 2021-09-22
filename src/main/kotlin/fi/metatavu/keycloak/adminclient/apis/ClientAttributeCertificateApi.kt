package fi.metatavu.keycloak.adminclient.apis

import fi.metatavu.keycloak.adminclient.models.CertificateRepresentation
import fi.metatavu.keycloak.adminclient.models.KeyStoreConfig
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


interface ClientAttributeCertificateApi  {
    fun init(vertx:Vertx,config:JsonObject)
    /* realmClientsIdCertificatesAttrDownloadPost
     * Get a keystore file for the client, containing private key and public certificate */
    suspend fun realmClientsIdCertificatesAttrDownloadPost(realm:kotlin.String?,id:kotlin.String?,attr:kotlin.String?,keyStoreConfig:KeyStoreConfig?,context:OperationRequest):Response<kotlin.ByteArray>
    /* realmClientsIdCertificatesAttrGenerateAndDownloadPost
     * Generate a new keypair and certificate, and get the private key file   Generates a keypair and certificate and serves the private key in a specified keystore format. */
    suspend fun realmClientsIdCertificatesAttrGenerateAndDownloadPost(realm:kotlin.String?,id:kotlin.String?,attr:kotlin.String?,keyStoreConfig:KeyStoreConfig?,context:OperationRequest):Response<kotlin.ByteArray>
    /* realmClientsIdCertificatesAttrGeneratePost
     * Generate a new certificate with new key pair */
    suspend fun realmClientsIdCertificatesAttrGeneratePost(realm:kotlin.String?,id:kotlin.String?,attr:kotlin.String?,context:OperationRequest):Response<CertificateRepresentation>
    /* realmClientsIdCertificatesAttrGet
     * Get key info */
    suspend fun realmClientsIdCertificatesAttrGet(realm:kotlin.String?,id:kotlin.String?,attr:kotlin.String?,context:OperationRequest):Response<CertificateRepresentation>
    /* realmClientsIdCertificatesAttrUploadCertificatePost
     * Upload only certificate, not private key */
    suspend fun realmClientsIdCertificatesAttrUploadCertificatePost(realm:kotlin.String?,id:kotlin.String?,attr:kotlin.String?,context:OperationRequest):Response<CertificateRepresentation>
    /* realmClientsIdCertificatesAttrUploadPost
     * Upload certificate and eventually private key */
    suspend fun realmClientsIdCertificatesAttrUploadPost(realm:kotlin.String?,id:kotlin.String?,attr:kotlin.String?,context:OperationRequest):Response<CertificateRepresentation>
    companion object {
        const val address = "ClientAttributeCertificateApi-service"
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
            for (m in ClientAttributeCertificateApi::class.java.methods) {
                val methodName = m.name
                val op = operations[methodName]
                if (op != null) {
                    val method = op::class.java.getDeclaredMethod("mountRouteToService",String::class.java,String::class.java)
                    method.isAccessible = true
                    method.invoke(op,address,methodName)
                }
            }
            routerFactory.mountServiceInterface(ClientAttributeCertificateApi::class.java, address)
            return routerFactory
        }
    }
}
