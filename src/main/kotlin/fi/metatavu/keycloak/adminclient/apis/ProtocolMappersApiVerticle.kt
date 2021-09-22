package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(ProtocolMappersApiVerticle())
}

class ProtocolMappersApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.ProtocolMappersApiImpl").newInstance() as ProtocolMappersApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(ProtocolMappersApi.address)
            .register(ProtocolMappersApi::class.java,instance)
    }
}