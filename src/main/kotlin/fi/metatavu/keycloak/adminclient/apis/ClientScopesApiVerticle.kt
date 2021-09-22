package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(ClientScopesApiVerticle())
}

class ClientScopesApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.ClientScopesApiImpl").newInstance() as ClientScopesApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(ClientScopesApi.address)
            .register(ClientScopesApi::class.java,instance)
    }
}