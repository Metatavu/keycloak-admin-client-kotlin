package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(RolesByIDApiVerticle())
}

class RolesByIDApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.RolesByIDApiImpl").newInstance() as RolesByIDApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(RolesByIDApi.address)
            .register(RolesByIDApi::class.java,instance)
    }
}