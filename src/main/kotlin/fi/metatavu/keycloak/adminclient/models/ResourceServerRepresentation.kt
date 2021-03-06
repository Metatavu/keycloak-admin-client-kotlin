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

import fi.metatavu.keycloak.adminclient.models.PolicyRepresentation
import fi.metatavu.keycloak.adminclient.models.ResourceRepresentation
import fi.metatavu.keycloak.adminclient.models.ScopeRepresentation

        
import com.google.gson.annotations.SerializedName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
/**
 * 
 * @param allowRemoteResourceManagement 
 * @param clientId 
 * @param decisionStrategy 
 * @param id 
 * @param name 
 * @param policies 
 * @param policyEnforcementMode 
 * @param resources 
 * @param scopes 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ResourceServerRepresentation (
    val allowRemoteResourceManagement: kotlin.Boolean? = null,
    val clientId: kotlin.String? = null,
    val decisionStrategy: ResourceServerRepresentation.DecisionStrategy? = null,
    val id: kotlin.String? = null,
    val name: kotlin.String? = null,
    val policies: kotlin.Array<PolicyRepresentation>? = null,
    val policyEnforcementMode: ResourceServerRepresentation.PolicyEnforcementMode? = null,
    val resources: kotlin.Array<ResourceRepresentation>? = null,
    val scopes: kotlin.Array<ScopeRepresentation>? = null
) {

    /**
    * 
    * Values: aFFIRMATIVE,uNANIMOUS,cONSENSUS
    */
    enum class DecisionStrategy(val value: kotlin.String){
    
        aFFIRMATIVE("AFFIRMATIVE"),
    
        uNANIMOUS("UNANIMOUS"),
    
        cONSENSUS("CONSENSUS");
    
    }

    /**
    * 
    * Values: eNFORCING,pERMISSIVE,dISABLED
    */
    enum class PolicyEnforcementMode(val value: kotlin.String){
    
        eNFORCING("ENFORCING"),
    
        pERMISSIVE("PERMISSIVE"),
    
        dISABLED("DISABLED");
    
    }

}

