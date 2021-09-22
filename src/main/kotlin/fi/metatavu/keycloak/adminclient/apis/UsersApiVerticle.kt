package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(UsersApiVerticle())
}

class UsersApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.UsersApiImpl").newInstance() as UsersApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(UsersApi.address)
            .register(UsersApi::class.java,instance)
    }
}