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


        
import com.google.gson.annotations.SerializedName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
/**
 * 
 * @param config 
 * @param id 
 * @param name 
 * @param protocol 
 * @param protocolMapper 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ProtocolMapperRepresentation (
    val config: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val id: kotlin.String? = null,
    val name: kotlin.String? = null,
    val protocol: kotlin.String? = null,
    val protocolMapper: kotlin.String? = null
) {

}

