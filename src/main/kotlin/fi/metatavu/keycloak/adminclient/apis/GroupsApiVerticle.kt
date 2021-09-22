package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(GroupsApiVerticle())
}

class GroupsApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.GroupsApiImpl").newInstance() as GroupsApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(GroupsApi.address)
            .register(GroupsApi::class.java,instance)
    }
}