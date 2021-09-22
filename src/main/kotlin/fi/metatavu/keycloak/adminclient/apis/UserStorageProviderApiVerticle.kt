package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(UserStorageProviderApiVerticle())
}

class UserStorageProviderApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.UserStorageProviderApiImpl").newInstance() as UserStorageProviderApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(UserStorageProviderApi.address)
            .register(UserStorageProviderApi::class.java,instance)
    }
}