package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(ComponentApiVerticle())
}

class ComponentApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.ComponentApiImpl").newInstance() as ComponentApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(ComponentApi.address)
            .register(ComponentApi::class.java,instance)
    }
}