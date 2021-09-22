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

import fi.metatavu.keycloak.adminclient.models.RoleRepresentation

        
import com.google.gson.annotations.SerializedName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
/**
 * 
 * @param clientMappings 
 * @param realmMappings 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MappingsRepresentation (
    val clientMappings: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val realmMappings: kotlin.Array<RoleRepresentation>? = null
) {

}
