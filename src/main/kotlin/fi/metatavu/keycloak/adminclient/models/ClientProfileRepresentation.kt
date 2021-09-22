/**
* Keycloak Admin REST API
* This is a REST API reference for the Keycloak Admin
*
* The version of the OpenAPI document: 1
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package fi.metatavu.keycloak.adminclient.models

import fi.metatavu.keycloak.adminclient.models.ClientPolicyExecutorRepresentation

        
import com.google.gson.annotations.SerializedName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
/**
 * 
 * @param description 
 * @param executors 
 * @param name 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ClientProfileRepresentation (
    val description: kotlin.String? = null,
    val executors: kotlin.Array<ClientPolicyExecutorRepresentation>? = null,
    val name: kotlin.String? = null
) {

}
