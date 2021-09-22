package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(ClientsApiVerticle())
}

class ClientsApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.ClientsApiImpl").newInstance() as ClientsApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(ClientsApi.address)
            .register(ClientsApi::class.java,instance)
    }
}