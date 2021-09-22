package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(RoleMapperApiVerticle())
}

class RoleMapperApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.RoleMapperApiImpl").newInstance() as RoleMapperApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(RoleMapperApi.address)
            .register(RoleMapperApi::class.java,instance)
    }
}