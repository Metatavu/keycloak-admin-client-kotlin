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
 * @param disabledFeatures 
 * @param experimentalFeatures 
 * @param name 
 * @param previewFeatures 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ProfileInfoRepresentation (
    val disabledFeatures: kotlin.Array<kotlin.String>? = null,
    val experimentalFeatures: kotlin.Array<kotlin.String>? = null,
    val name: kotlin.String? = null,
    val previewFeatures: kotlin.Array<kotlin.String>? = null
) {

}

