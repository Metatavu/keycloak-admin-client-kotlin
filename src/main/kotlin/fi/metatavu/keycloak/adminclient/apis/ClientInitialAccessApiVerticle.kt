package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(ClientInitialAccessApiVerticle())
}

class ClientInitialAccessApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.ClientInitialAccessApiImpl").newInstance() as ClientInitialAccessApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(ClientInitialAccessApi.address)
            .register(ClientInitialAccessApi::class.java,instance)
    }
}