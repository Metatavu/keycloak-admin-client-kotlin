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

import fi.metatavu.keycloak.adminclient.models.RoleRepresentationMinusComposites

        
import com.google.gson.annotations.SerializedName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
/**
 * 
 * @param attributes 
 * @param clientRole 
 * @param composite 
 * @param composites 
 * @param containerId 
 * @param description 
 * @param id 
 * @param name 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class RoleRepresentation (
    val attributes: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val clientRole: kotlin.Boolean? = null,
    val composite: kotlin.Boolean? = null,
    val composites: RoleRepresentationMinusComposites? = null,
    val containerId: kotlin.String? = null,
    val description: kotlin.String? = null,
    val id: kotlin.String? = null,
    val name: kotlin.String? = null
) {

}
