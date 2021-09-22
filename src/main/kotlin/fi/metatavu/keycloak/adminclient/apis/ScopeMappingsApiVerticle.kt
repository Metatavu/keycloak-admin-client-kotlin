package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(ScopeMappingsApiVerticle())
}

class ScopeMappingsApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.ScopeMappingsApiImpl").newInstance() as ScopeMappingsApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(ScopeMappingsApi.address)
            .register(ScopeMappingsApi::class.java,instance)
    }
}