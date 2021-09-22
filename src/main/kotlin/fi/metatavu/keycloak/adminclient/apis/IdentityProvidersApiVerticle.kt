package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(IdentityProvidersApiVerticle())
}

class IdentityProvidersApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.IdentityProvidersApiImpl").newInstance() as IdentityProvidersApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(IdentityProvidersApi.address)
            .register(IdentityProvidersApi::class.java,instance)
    }
}