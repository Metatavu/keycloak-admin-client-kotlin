package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(KeyApiVerticle())
}

class KeyApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.KeyApiImpl").newInstance() as KeyApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(KeyApi.address)
            .register(KeyApi::class.java,instance)
    }
}