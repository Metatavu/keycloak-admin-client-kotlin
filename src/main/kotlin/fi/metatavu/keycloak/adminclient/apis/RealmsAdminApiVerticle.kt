package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(RealmsAdminApiVerticle())
}

class RealmsAdminApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.RealmsAdminApiImpl").newInstance() as RealmsAdminApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(RealmsAdminApi.address)
            .register(RealmsAdminApi::class.java,instance)
    }
}