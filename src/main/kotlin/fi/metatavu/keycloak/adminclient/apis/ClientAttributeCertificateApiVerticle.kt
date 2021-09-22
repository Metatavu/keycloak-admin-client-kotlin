package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(ClientAttributeCertificateApiVerticle())
}

class ClientAttributeCertificateApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.ClientAttributeCertificateApiImpl").newInstance() as ClientAttributeCertificateApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(ClientAttributeCertificateApi.address)
            .register(ClientAttributeCertificateApi::class.java,instance)
    }
}