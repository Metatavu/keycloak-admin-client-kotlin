package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(AuthenticationManagementApiVerticle())
}

class AuthenticationManagementApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.AuthenticationManagementApiImpl").newInstance() as AuthenticationManagementApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(AuthenticationManagementApi.address)
            .register(AuthenticationManagementApi::class.java,instance)
    }
}