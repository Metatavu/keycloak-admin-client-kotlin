package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(RolesApiVerticle())
}

class RolesApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.RolesApiImpl").newInstance() as RolesApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(RolesApi.address)
            .register(RolesApi::class.java,instance)
    }
}