package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(RootApiVerticle())
}

class RootApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.RootApiImpl").newInstance() as RootApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(RootApi.address)
            .register(RootApi::class.java,instance)
    }
}