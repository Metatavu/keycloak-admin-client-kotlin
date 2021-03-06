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
 * @param country 
 * @param formatted 
 * @param locality 
 * @param postalCode 
 * @param region 
 * @param streetAddress 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AddressClaimSet (
    val country: kotlin.String? = null,
    val formatted: kotlin.String? = null,
    val locality: kotlin.String? = null,
    val postalCode: kotlin.String? = null,
    val region: kotlin.String? = null,
    val streetAddress: kotlin.String? = null
) {

}

