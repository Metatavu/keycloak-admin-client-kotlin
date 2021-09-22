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

import fi.metatavu.keycloak.adminclient.models.JsonNode

        
import com.google.gson.annotations.SerializedName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
/**
 * 
 * @param condition 
 * @param configuration 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ClientPolicyConditionRepresentation (
    val condition: kotlin.String? = null,
    val configuration: JsonNode? = null
) {

}

