package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(ClientRoleMappingsApiVerticle())
}

class ClientRoleMappingsApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.ClientRoleMappingsApiImpl").newInstance() as ClientRoleMappingsApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(ClientRoleMappingsApi.address)
            .register(ClientRoleMappingsApi::class.java,instance)
    }
}