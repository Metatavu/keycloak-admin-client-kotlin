package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(AttackDetectionApiVerticle())
}

class AttackDetectionApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.AttackDetectionApiImpl").newInstance() as AttackDetectionApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(AttackDetectionApi.address)
            .register(AttackDetectionApi::class.java,instance)
    }
}