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

import fi.metatavu.keycloak.adminclient.models.ScopeRepresentation

        
import com.google.gson.annotations.SerializedName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
/**
 * 
 * @param id 
 * @param attributes 
 * @param displayName 
 * @param iconUri 
 * @param name 
 * @param ownerManagedAccess 
 * @param scopes 
 * @param type 
 * @param uris 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ResourceRepresentation (
    val id: kotlin.String? = null,
    val attributes: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val displayName: kotlin.String? = null,
    val iconUri: kotlin.String? = null,
    val name: kotlin.String? = null,
    val ownerManagedAccess: kotlin.Boolean? = null,
    val scopes: kotlin.Array<ScopeRepresentation>? = null,
    val type: kotlin.String? = null,
    val uris: kotlin.Array<kotlin.String>? = null
) {

}

