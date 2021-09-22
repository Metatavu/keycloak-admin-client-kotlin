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

import fi.metatavu.keycloak.adminclient.models.MemoryInfoRepresentation
import fi.metatavu.keycloak.adminclient.models.PasswordPolicyTypeRepresentation
import fi.metatavu.keycloak.adminclient.models.ProfileInfoRepresentation
import fi.metatavu.keycloak.adminclient.models.SystemInfoRepresentation

        
import com.google.gson.annotations.SerializedName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
/**
 * 
 * @param builtinProtocolMappers 
 * @param clientImporters 
 * @param clientInstallations 
 * @param componentTypes 
 * @param enums 
 * @param identityProviders 
 * @param memoryInfo 
 * @param passwordPolicies 
 * @param profileInfo 
 * @param protocolMapperTypes 
 * @param providers 
 * @param socialProviders 
 * @param systemInfo 
 * @param themes 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ServerInfoRepresentation (
    val builtinProtocolMappers: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val clientImporters: kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>? = null,
    val clientInstallations: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val componentTypes: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val enums: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val identityProviders: kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>? = null,
    val memoryInfo: MemoryInfoRepresentation? = null,
    val passwordPolicies: kotlin.Array<PasswordPolicyTypeRepresentation>? = null,
    val profileInfo: ProfileInfoRepresentation? = null,
    val protocolMapperTypes: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val providers: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val socialProviders: kotlin.Array<kotlin.collections.Map<kotlin.String, kotlin.Any>>? = null,
    val systemInfo: SystemInfoRepresentation? = null,
    val themes: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null
) {

}
