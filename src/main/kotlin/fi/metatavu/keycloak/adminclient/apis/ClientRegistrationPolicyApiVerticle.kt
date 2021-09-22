package fi.metatavu.keycloak.adminclient.apis
import io.vertx.core.Vertx
import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder

fun main(){
    Vertx.vertx().deployVerticle(ClientRegistrationPolicyApiVerticle())
}

class ClientRegistrationPolicyApiVerticle:AbstractVerticle() {

    override fun start() {
        val instance = (javaClass.classLoader.loadClass("fi.metatavu.keycloak.adminclient.apis.ClientRegistrationPolicyApiImpl").newInstance() as ClientRegistrationPolicyApi)
        instance.init(vertx,config())
        ServiceBinder(vertx)
            .setAddress(ClientRegistrationPolicyApi.address)
            .register(ClientRegistrationPolicyApi::class.java,instance)
    }
}