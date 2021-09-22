# fi.metatavu.keycloak.adminclient - Kotlin Server library for Keycloak Admin REST API

## Requires

* Kotlin 1.3.10
* Maven 3.3

## Build

```
mvn clean package
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.

    <a name="documentation-for-api-endpoints"></a>
    ## Documentation for API Endpoints

    All URIs are relative to *http://localhost*

    Class | Method | HTTP request | Description
    ------------ | ------------- | ------------- | -------------
    *AttackDetectionApi* | [**realmAttackDetectionBruteForceUsersDelete**](docs/AttackDetectionApi.md#realmattackdetectionbruteforceusersdelete) | **DELETE** /{realm}/attack-detection/brute-force/users | Clear any user login failures for all users   This can release temporary disabled users
    *AttackDetectionApi* | [**realmAttackDetectionBruteForceUsersUserIdDelete**](docs/AttackDetectionApi.md#realmattackdetectionbruteforceusersuseriddelete) | **DELETE** /{realm}/attack-detection/brute-force/users/{userId} | Clear any user login failures for the user   This can release temporary disabled user
    *AttackDetectionApi* | [**realmAttackDetectionBruteForceUsersUserIdGet**](docs/AttackDetectionApi.md#realmattackdetectionbruteforceusersuseridget) | **GET** /{realm}/attack-detection/brute-force/users/{userId} | Get status of a username in brute force detection
    *AuthenticationManagementApi* | [**realmAuthenticationAuthenticatorProvidersGet**](docs/AuthenticationManagementApi.md#realmauthenticationauthenticatorprovidersget) | **GET** /{realm}/authentication/authenticator-providers | Get authenticator providers   Returns a stream of authenticator providers.
    *AuthenticationManagementApi* | [**realmAuthenticationClientAuthenticatorProvidersGet**](docs/AuthenticationManagementApi.md#realmauthenticationclientauthenticatorprovidersget) | **GET** /{realm}/authentication/client-authenticator-providers | Get client authenticator providers   Returns a stream of client authenticator providers.
    *AuthenticationManagementApi* | [**realmAuthenticationConfigDescriptionProviderIdGet**](docs/AuthenticationManagementApi.md#realmauthenticationconfigdescriptionprovideridget) | **GET** /{realm}/authentication/config-description/{providerId} | Get authenticator provider’s configuration description
    *AuthenticationManagementApi* | [**realmAuthenticationConfigIdDelete**](docs/AuthenticationManagementApi.md#realmauthenticationconfigiddelete) | **DELETE** /{realm}/authentication/config/{id} | Delete authenticator configuration
    *AuthenticationManagementApi* | [**realmAuthenticationConfigIdGet**](docs/AuthenticationManagementApi.md#realmauthenticationconfigidget) | **GET** /{realm}/authentication/config/{id} | Get authenticator configuration
    *AuthenticationManagementApi* | [**realmAuthenticationConfigIdPut**](docs/AuthenticationManagementApi.md#realmauthenticationconfigidput) | **PUT** /{realm}/authentication/config/{id} | Update authenticator configuration
    *AuthenticationManagementApi* | [**realmAuthenticationExecutionsExecutionIdConfigPost**](docs/AuthenticationManagementApi.md#realmauthenticationexecutionsexecutionidconfigpost) | **POST** /{realm}/authentication/executions/{executionId}/config | Update execution with new configuration
    *AuthenticationManagementApi* | [**realmAuthenticationExecutionsExecutionIdDelete**](docs/AuthenticationManagementApi.md#realmauthenticationexecutionsexecutioniddelete) | **DELETE** /{realm}/authentication/executions/{executionId} | Delete execution
    *AuthenticationManagementApi* | [**realmAuthenticationExecutionsExecutionIdGet**](docs/AuthenticationManagementApi.md#realmauthenticationexecutionsexecutionidget) | **GET** /{realm}/authentication/executions/{executionId} | Get Single Execution
    *AuthenticationManagementApi* | [**realmAuthenticationExecutionsExecutionIdLowerPriorityPost**](docs/AuthenticationManagementApi.md#realmauthenticationexecutionsexecutionidlowerprioritypost) | **POST** /{realm}/authentication/executions/{executionId}/lower-priority | Lower execution’s priority
    *AuthenticationManagementApi* | [**realmAuthenticationExecutionsExecutionIdRaisePriorityPost**](docs/AuthenticationManagementApi.md#realmauthenticationexecutionsexecutionidraiseprioritypost) | **POST** /{realm}/authentication/executions/{executionId}/raise-priority | Raise execution’s priority
    *AuthenticationManagementApi* | [**realmAuthenticationExecutionsPost**](docs/AuthenticationManagementApi.md#realmauthenticationexecutionspost) | **POST** /{realm}/authentication/executions | Add new authentication execution
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsFlowAliasCopyPost**](docs/AuthenticationManagementApi.md#realmauthenticationflowsflowaliascopypost) | **POST** /{realm}/authentication/flows/{flowAlias}/copy | Copy existing authentication flow under a new name   The new name is given as 'newName' attribute of the passed JSON object
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsFlowAliasExecutionsExecutionPost**](docs/AuthenticationManagementApi.md#realmauthenticationflowsflowaliasexecutionsexecutionpost) | **POST** /{realm}/authentication/flows/{flowAlias}/executions/execution | Add new authentication execution to a flow
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsFlowAliasExecutionsFlowPost**](docs/AuthenticationManagementApi.md#realmauthenticationflowsflowaliasexecutionsflowpost) | **POST** /{realm}/authentication/flows/{flowAlias}/executions/flow | Add new flow with new execution to existing flow
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsFlowAliasExecutionsGet**](docs/AuthenticationManagementApi.md#realmauthenticationflowsflowaliasexecutionsget) | **GET** /{realm}/authentication/flows/{flowAlias}/executions | Get authentication executions for a flow
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsFlowAliasExecutionsPut**](docs/AuthenticationManagementApi.md#realmauthenticationflowsflowaliasexecutionsput) | **PUT** /{realm}/authentication/flows/{flowAlias}/executions | Update authentication executions of a Flow
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsGet**](docs/AuthenticationManagementApi.md#realmauthenticationflowsget) | **GET** /{realm}/authentication/flows | Get authentication flows   Returns a stream of authentication flows.
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsIdDelete**](docs/AuthenticationManagementApi.md#realmauthenticationflowsiddelete) | **DELETE** /{realm}/authentication/flows/{id} | Delete an authentication flow
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsIdGet**](docs/AuthenticationManagementApi.md#realmauthenticationflowsidget) | **GET** /{realm}/authentication/flows/{id} | Get authentication flow for id
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsIdPut**](docs/AuthenticationManagementApi.md#realmauthenticationflowsidput) | **PUT** /{realm}/authentication/flows/{id} | Update an authentication flow
    *AuthenticationManagementApi* | [**realmAuthenticationFlowsPost**](docs/AuthenticationManagementApi.md#realmauthenticationflowspost) | **POST** /{realm}/authentication/flows | Create a new authentication flow
    *AuthenticationManagementApi* | [**realmAuthenticationFormActionProvidersGet**](docs/AuthenticationManagementApi.md#realmauthenticationformactionprovidersget) | **GET** /{realm}/authentication/form-action-providers | Get form action providers   Returns a stream of form action providers.
    *AuthenticationManagementApi* | [**realmAuthenticationFormProvidersGet**](docs/AuthenticationManagementApi.md#realmauthenticationformprovidersget) | **GET** /{realm}/authentication/form-providers | Get form providers   Returns a stream of form providers.
    *AuthenticationManagementApi* | [**realmAuthenticationPerClientConfigDescriptionGet**](docs/AuthenticationManagementApi.md#realmauthenticationperclientconfigdescriptionget) | **GET** /{realm}/authentication/per-client-config-description | Get configuration descriptions for all clients
    *AuthenticationManagementApi* | [**realmAuthenticationRegisterRequiredActionPost**](docs/AuthenticationManagementApi.md#realmauthenticationregisterrequiredactionpost) | **POST** /{realm}/authentication/register-required-action | Register a new required actions
    *AuthenticationManagementApi* | [**realmAuthenticationRequiredActionsAliasDelete**](docs/AuthenticationManagementApi.md#realmauthenticationrequiredactionsaliasdelete) | **DELETE** /{realm}/authentication/required-actions/{alias} | Delete required action
    *AuthenticationManagementApi* | [**realmAuthenticationRequiredActionsAliasGet**](docs/AuthenticationManagementApi.md#realmauthenticationrequiredactionsaliasget) | **GET** /{realm}/authentication/required-actions/{alias} | Get required action for alias
    *AuthenticationManagementApi* | [**realmAuthenticationRequiredActionsAliasLowerPriorityPost**](docs/AuthenticationManagementApi.md#realmauthenticationrequiredactionsaliaslowerprioritypost) | **POST** /{realm}/authentication/required-actions/{alias}/lower-priority | Lower required action’s priority
    *AuthenticationManagementApi* | [**realmAuthenticationRequiredActionsAliasPut**](docs/AuthenticationManagementApi.md#realmauthenticationrequiredactionsaliasput) | **PUT** /{realm}/authentication/required-actions/{alias} | Update required action
    *AuthenticationManagementApi* | [**realmAuthenticationRequiredActionsAliasRaisePriorityPost**](docs/AuthenticationManagementApi.md#realmauthenticationrequiredactionsaliasraiseprioritypost) | **POST** /{realm}/authentication/required-actions/{alias}/raise-priority | Raise required action’s priority
    *AuthenticationManagementApi* | [**realmAuthenticationRequiredActionsGet**](docs/AuthenticationManagementApi.md#realmauthenticationrequiredactionsget) | **GET** /{realm}/authentication/required-actions | Get required actions   Returns a stream of required actions.
    *AuthenticationManagementApi* | [**realmAuthenticationUnregisteredRequiredActionsGet**](docs/AuthenticationManagementApi.md#realmauthenticationunregisteredrequiredactionsget) | **GET** /{realm}/authentication/unregistered-required-actions | Get unregistered required actions   Returns a stream of unregistered required actions.
    *ClientAttributeCertificateApi* | [**realmClientsIdCertificatesAttrDownloadPost**](docs/ClientAttributeCertificateApi.md#realmclientsidcertificatesattrdownloadpost) | **POST** /{realm}/clients/{id}/certificates/{attr}/download | Get a keystore file for the client, containing private key and public certificate
    *ClientAttributeCertificateApi* | [**realmClientsIdCertificatesAttrGenerateAndDownloadPost**](docs/ClientAttributeCertificateApi.md#realmclientsidcertificatesattrgenerateanddownloadpost) | **POST** /{realm}/clients/{id}/certificates/{attr}/generate-and-download | Generate a new keypair and certificate, and get the private key file   Generates a keypair and certificate and serves the private key in a specified keystore format.
    *ClientAttributeCertificateApi* | [**realmClientsIdCertificatesAttrGeneratePost**](docs/ClientAttributeCertificateApi.md#realmclientsidcertificatesattrgeneratepost) | **POST** /{realm}/clients/{id}/certificates/{attr}/generate | Generate a new certificate with new key pair
    *ClientAttributeCertificateApi* | [**realmClientsIdCertificatesAttrGet**](docs/ClientAttributeCertificateApi.md#realmclientsidcertificatesattrget) | **GET** /{realm}/clients/{id}/certificates/{attr} | Get key info
    *ClientAttributeCertificateApi* | [**realmClientsIdCertificatesAttrUploadCertificatePost**](docs/ClientAttributeCertificateApi.md#realmclientsidcertificatesattruploadcertificatepost) | **POST** /{realm}/clients/{id}/certificates/{attr}/upload-certificate | Upload only certificate, not private key
    *ClientAttributeCertificateApi* | [**realmClientsIdCertificatesAttrUploadPost**](docs/ClientAttributeCertificateApi.md#realmclientsidcertificatesattruploadpost) | **POST** /{realm}/clients/{id}/certificates/{attr}/upload | Upload certificate and eventually private key
    *ClientInitialAccessApi* | [**realmClientsInitialAccessGet**](docs/ClientInitialAccessApi.md#realmclientsinitialaccessget) | **GET** /{realm}/clients-initial-access | 
    *ClientInitialAccessApi* | [**realmClientsInitialAccessIdDelete**](docs/ClientInitialAccessApi.md#realmclientsinitialaccessiddelete) | **DELETE** /{realm}/clients-initial-access/{id} | 
    *ClientInitialAccessApi* | [**realmClientsInitialAccessPost**](docs/ClientInitialAccessApi.md#realmclientsinitialaccesspost) | **POST** /{realm}/clients-initial-access | Create a new initial access token.
    *ClientRegistrationPolicyApi* | [**realmClientRegistrationPolicyProvidersGet**](docs/ClientRegistrationPolicyApi.md#realmclientregistrationpolicyprovidersget) | **GET** /{realm}/client-registration-policy/providers | Base path for retrieve providers with the configProperties properly filled
    *ClientRoleMappingsApi* | [**realmGroupsIdRoleMappingsClientsClientAvailableGet**](docs/ClientRoleMappingsApi.md#realmgroupsidrolemappingsclientsclientavailableget) | **GET** /{realm}/groups/{id}/role-mappings/clients/{client}/available | Get available client-level roles that can be mapped to the user
    *ClientRoleMappingsApi* | [**realmGroupsIdRoleMappingsClientsClientCompositeGet**](docs/ClientRoleMappingsApi.md#realmgroupsidrolemappingsclientsclientcompositeget) | **GET** /{realm}/groups/{id}/role-mappings/clients/{client}/composite | Get effective client-level role mappings   This recurses any composite roles
    *ClientRoleMappingsApi* | [**realmGroupsIdRoleMappingsClientsClientDelete**](docs/ClientRoleMappingsApi.md#realmgroupsidrolemappingsclientsclientdelete) | **DELETE** /{realm}/groups/{id}/role-mappings/clients/{client} | Delete client-level roles from user role mapping
    *ClientRoleMappingsApi* | [**realmGroupsIdRoleMappingsClientsClientGet**](docs/ClientRoleMappingsApi.md#realmgroupsidrolemappingsclientsclientget) | **GET** /{realm}/groups/{id}/role-mappings/clients/{client} | Get client-level role mappings for the user, and the app
    *ClientRoleMappingsApi* | [**realmGroupsIdRoleMappingsClientsClientPost**](docs/ClientRoleMappingsApi.md#realmgroupsidrolemappingsclientsclientpost) | **POST** /{realm}/groups/{id}/role-mappings/clients/{client} | Add client-level roles to the user role mapping
    *ClientRoleMappingsApi* | [**realmUsersIdRoleMappingsClientsClientAvailableGet**](docs/ClientRoleMappingsApi.md#realmusersidrolemappingsclientsclientavailableget) | **GET** /{realm}/users/{id}/role-mappings/clients/{client}/available | Get available client-level roles that can be mapped to the user
    *ClientRoleMappingsApi* | [**realmUsersIdRoleMappingsClientsClientCompositeGet**](docs/ClientRoleMappingsApi.md#realmusersidrolemappingsclientsclientcompositeget) | **GET** /{realm}/users/{id}/role-mappings/clients/{client}/composite | Get effective client-level role mappings   This recurses any composite roles
    *ClientRoleMappingsApi* | [**realmUsersIdRoleMappingsClientsClientDelete**](docs/ClientRoleMappingsApi.md#realmusersidrolemappingsclientsclientdelete) | **DELETE** /{realm}/users/{id}/role-mappings/clients/{client} | Delete client-level roles from user role mapping
    *ClientRoleMappingsApi* | [**realmUsersIdRoleMappingsClientsClientGet**](docs/ClientRoleMappingsApi.md#realmusersidrolemappingsclientsclientget) | **GET** /{realm}/users/{id}/role-mappings/clients/{client} | Get client-level role mappings for the user, and the app
    *ClientRoleMappingsApi* | [**realmUsersIdRoleMappingsClientsClientPost**](docs/ClientRoleMappingsApi.md#realmusersidrolemappingsclientsclientpost) | **POST** /{realm}/users/{id}/role-mappings/clients/{client} | Add client-level roles to the user role mapping
    *ClientScopesApi* | [**realmClientScopesGet**](docs/ClientScopesApi.md#realmclientscopesget) | **GET** /{realm}/client-scopes | Get client scopes belonging to the realm   Returns a list of client scopes belonging to the realm
    *ClientScopesApi* | [**realmClientScopesIdDelete**](docs/ClientScopesApi.md#realmclientscopesiddelete) | **DELETE** /{realm}/client-scopes/{id} | Delete the client scope
    *ClientScopesApi* | [**realmClientScopesIdGet**](docs/ClientScopesApi.md#realmclientscopesidget) | **GET** /{realm}/client-scopes/{id} | Get representation of the client scope
    *ClientScopesApi* | [**realmClientScopesIdPut**](docs/ClientScopesApi.md#realmclientscopesidput) | **PUT** /{realm}/client-scopes/{id} | Update the client scope
    *ClientScopesApi* | [**realmClientScopesPost**](docs/ClientScopesApi.md#realmclientscopespost) | **POST** /{realm}/client-scopes | Create a new client scope   Client Scope’s name must be unique!
    *ClientsApi* | [**realmClientsGet**](docs/ClientsApi.md#realmclientsget) | **GET** /{realm}/clients | Get clients belonging to the realm   Returns a list of clients belonging to the realm
    *ClientsApi* | [**realmClientsIdClientSecretGet**](docs/ClientsApi.md#realmclientsidclientsecretget) | **GET** /{realm}/clients/{id}/client-secret | Get the client secret
    *ClientsApi* | [**realmClientsIdClientSecretPost**](docs/ClientsApi.md#realmclientsidclientsecretpost) | **POST** /{realm}/clients/{id}/client-secret | Generate a new secret for the client
    *ClientsApi* | [**realmClientsIdDefaultClientScopesClientScopeIdDelete**](docs/ClientsApi.md#realmclientsiddefaultclientscopesclientscopeiddelete) | **DELETE** /{realm}/clients/{id}/default-client-scopes/{clientScopeId} | 
    *ClientsApi* | [**realmClientsIdDefaultClientScopesClientScopeIdPut**](docs/ClientsApi.md#realmclientsiddefaultclientscopesclientscopeidput) | **PUT** /{realm}/clients/{id}/default-client-scopes/{clientScopeId} | 
    *ClientsApi* | [**realmClientsIdDefaultClientScopesGet**](docs/ClientsApi.md#realmclientsiddefaultclientscopesget) | **GET** /{realm}/clients/{id}/default-client-scopes | Get default client scopes.
    *ClientsApi* | [**realmClientsIdDelete**](docs/ClientsApi.md#realmclientsiddelete) | **DELETE** /{realm}/clients/{id} | Delete the client
    *ClientsApi* | [**realmClientsIdEvaluateScopesGenerateExampleAccessTokenGet**](docs/ClientsApi.md#realmclientsidevaluatescopesgenerateexampleaccesstokenget) | **GET** /{realm}/clients/{id}/evaluate-scopes/generate-example-access-token | Create JSON with payload of example access token
    *ClientsApi* | [**realmClientsIdEvaluateScopesGenerateExampleIdTokenGet**](docs/ClientsApi.md#realmclientsidevaluatescopesgenerateexampleidtokenget) | **GET** /{realm}/clients/{id}/evaluate-scopes/generate-example-id-token | Create JSON with payload of example id token
    *ClientsApi* | [**realmClientsIdEvaluateScopesGenerateExampleUserinfoGet**](docs/ClientsApi.md#realmclientsidevaluatescopesgenerateexampleuserinfoget) | **GET** /{realm}/clients/{id}/evaluate-scopes/generate-example-userinfo | Create JSON with payload of example user info
    *ClientsApi* | [**realmClientsIdEvaluateScopesProtocolMappersGet**](docs/ClientsApi.md#realmclientsidevaluatescopesprotocolmappersget) | **GET** /{realm}/clients/{id}/evaluate-scopes/protocol-mappers | Return list of all protocol mappers, which will be used when generating tokens issued for particular client.
    *ClientsApi* | [**realmClientsIdEvaluateScopesScopeMappingsRoleContainerIdGrantedGet**](docs/ClientsApi.md#realmclientsidevaluatescopesscopemappingsrolecontaineridgrantedget) | **GET** /{realm}/clients/{id}/evaluate-scopes/scope-mappings/{roleContainerId}/granted | Get effective scope mapping of all roles of particular role container, which this client is defacto allowed to have in the accessToken issued for him.
    *ClientsApi* | [**realmClientsIdEvaluateScopesScopeMappingsRoleContainerIdNotGrantedGet**](docs/ClientsApi.md#realmclientsidevaluatescopesscopemappingsrolecontaineridnotgrantedget) | **GET** /{realm}/clients/{id}/evaluate-scopes/scope-mappings/{roleContainerId}/not-granted | Get roles, which this client doesn’t have scope for and can’t have them in the accessToken issued for him.
    *ClientsApi* | [**realmClientsIdGet**](docs/ClientsApi.md#realmclientsidget) | **GET** /{realm}/clients/{id} | Get representation of the client
    *ClientsApi* | [**realmClientsIdInstallationProvidersProviderIdGet**](docs/ClientsApi.md#realmclientsidinstallationprovidersprovideridget) | **GET** /{realm}/clients/{id}/installation/providers/{providerId} | 
    *ClientsApi* | [**realmClientsIdManagementPermissionsGet**](docs/ClientsApi.md#realmclientsidmanagementpermissionsget) | **GET** /{realm}/clients/{id}/management/permissions | Return object stating whether client Authorization permissions have been initialized or not and a reference
    *ClientsApi* | [**realmClientsIdManagementPermissionsPut**](docs/ClientsApi.md#realmclientsidmanagementpermissionsput) | **PUT** /{realm}/clients/{id}/management/permissions | Return object stating whether client Authorization permissions have been initialized or not and a reference
    *ClientsApi* | [**realmClientsIdNodesNodeDelete**](docs/ClientsApi.md#realmclientsidnodesnodedelete) | **DELETE** /{realm}/clients/{id}/nodes/{node} | Unregister a cluster node from the client
    *ClientsApi* | [**realmClientsIdNodesPost**](docs/ClientsApi.md#realmclientsidnodespost) | **POST** /{realm}/clients/{id}/nodes | Register a cluster node with the client   Manually register cluster node to this client - usually it’s not needed to call this directly as adapter should handle  by sending registration request to Keycloak
    *ClientsApi* | [**realmClientsIdOfflineSessionCountGet**](docs/ClientsApi.md#realmclientsidofflinesessioncountget) | **GET** /{realm}/clients/{id}/offline-session-count | Get application offline session count   Returns a number of offline user sessions associated with this client   {      \"count\": number  }
    *ClientsApi* | [**realmClientsIdOfflineSessionsGet**](docs/ClientsApi.md#realmclientsidofflinesessionsget) | **GET** /{realm}/clients/{id}/offline-sessions | Get offline sessions for client   Returns a list of offline user sessions associated with this client
    *ClientsApi* | [**realmClientsIdOptionalClientScopesClientScopeIdDelete**](docs/ClientsApi.md#realmclientsidoptionalclientscopesclientscopeiddelete) | **DELETE** /{realm}/clients/{id}/optional-client-scopes/{clientScopeId} | 
    *ClientsApi* | [**realmClientsIdOptionalClientScopesClientScopeIdPut**](docs/ClientsApi.md#realmclientsidoptionalclientscopesclientscopeidput) | **PUT** /{realm}/clients/{id}/optional-client-scopes/{clientScopeId} | 
    *ClientsApi* | [**realmClientsIdOptionalClientScopesGet**](docs/ClientsApi.md#realmclientsidoptionalclientscopesget) | **GET** /{realm}/clients/{id}/optional-client-scopes | Get optional client scopes.
    *ClientsApi* | [**realmClientsIdPushRevocationPost**](docs/ClientsApi.md#realmclientsidpushrevocationpost) | **POST** /{realm}/clients/{id}/push-revocation | Push the client’s revocation policy to its admin URL   If the client has an admin URL, push revocation policy to it.
    *ClientsApi* | [**realmClientsIdPut**](docs/ClientsApi.md#realmclientsidput) | **PUT** /{realm}/clients/{id} | Update the client
    *ClientsApi* | [**realmClientsIdRegistrationAccessTokenPost**](docs/ClientsApi.md#realmclientsidregistrationaccesstokenpost) | **POST** /{realm}/clients/{id}/registration-access-token | Generate a new registration access token for the client
    *ClientsApi* | [**realmClientsIdServiceAccountUserGet**](docs/ClientsApi.md#realmclientsidserviceaccountuserget) | **GET** /{realm}/clients/{id}/service-account-user | Get a user dedicated to the service account
    *ClientsApi* | [**realmClientsIdSessionCountGet**](docs/ClientsApi.md#realmclientsidsessioncountget) | **GET** /{realm}/clients/{id}/session-count | Get application session count   Returns a number of user sessions associated with this client   {      \"count\": number  }
    *ClientsApi* | [**realmClientsIdTestNodesAvailableGet**](docs/ClientsApi.md#realmclientsidtestnodesavailableget) | **GET** /{realm}/clients/{id}/test-nodes-available | Test if registered cluster nodes are available   Tests availability by sending 'ping' request to all cluster nodes.
    *ClientsApi* | [**realmClientsIdUserSessionsGet**](docs/ClientsApi.md#realmclientsidusersessionsget) | **GET** /{realm}/clients/{id}/user-sessions | Get user sessions for client   Returns a list of user sessions associated with this client
    *ClientsApi* | [**realmClientsPost**](docs/ClientsApi.md#realmclientspost) | **POST** /{realm}/clients | Create a new client   Client’s client_id must be unique!
    *ComponentApi* | [**realmComponentsGet**](docs/ComponentApi.md#realmcomponentsget) | **GET** /{realm}/components | 
    *ComponentApi* | [**realmComponentsIdDelete**](docs/ComponentApi.md#realmcomponentsiddelete) | **DELETE** /{realm}/components/{id} | 
    *ComponentApi* | [**realmComponentsIdGet**](docs/ComponentApi.md#realmcomponentsidget) | **GET** /{realm}/components/{id} | 
    *ComponentApi* | [**realmComponentsIdPut**](docs/ComponentApi.md#realmcomponentsidput) | **PUT** /{realm}/components/{id} | 
    *ComponentApi* | [**realmComponentsIdSubComponentTypesGet**](docs/ComponentApi.md#realmcomponentsidsubcomponenttypesget) | **GET** /{realm}/components/{id}/sub-component-types | List of subcomponent types that are available to configure for a particular parent component.
    *ComponentApi* | [**realmComponentsPost**](docs/ComponentApi.md#realmcomponentspost) | **POST** /{realm}/components | 
    *GroupsApi* | [**realmGroupsCountGet**](docs/GroupsApi.md#realmgroupscountget) | **GET** /{realm}/groups/count | Returns the groups counts.
    *GroupsApi* | [**realmGroupsGet**](docs/GroupsApi.md#realmgroupsget) | **GET** /{realm}/groups | Get group hierarchy.
    *GroupsApi* | [**realmGroupsIdChildrenPost**](docs/GroupsApi.md#realmgroupsidchildrenpost) | **POST** /{realm}/groups/{id}/children | Set or create child.
    *GroupsApi* | [**realmGroupsIdDelete**](docs/GroupsApi.md#realmgroupsiddelete) | **DELETE** /{realm}/groups/{id} | 
    *GroupsApi* | [**realmGroupsIdGet**](docs/GroupsApi.md#realmgroupsidget) | **GET** /{realm}/groups/{id} | 
    *GroupsApi* | [**realmGroupsIdManagementPermissionsGet**](docs/GroupsApi.md#realmgroupsidmanagementpermissionsget) | **GET** /{realm}/groups/{id}/management/permissions | Return object stating whether client Authorization permissions have been initialized or not and a reference
    *GroupsApi* | [**realmGroupsIdManagementPermissionsPut**](docs/GroupsApi.md#realmgroupsidmanagementpermissionsput) | **PUT** /{realm}/groups/{id}/management/permissions | Return object stating whether client Authorization permissions have been initialized or not and a reference
    *GroupsApi* | [**realmGroupsIdMembersGet**](docs/GroupsApi.md#realmgroupsidmembersget) | **GET** /{realm}/groups/{id}/members | Get users   Returns a stream of users, filtered according to query parameters
    *GroupsApi* | [**realmGroupsIdPut**](docs/GroupsApi.md#realmgroupsidput) | **PUT** /{realm}/groups/{id} | Update group, ignores subgroups.
    *GroupsApi* | [**realmGroupsPost**](docs/GroupsApi.md#realmgroupspost) | **POST** /{realm}/groups | create or add a top level realm groupSet or create child.
    *IdentityProvidersApi* | [**realmIdentityProviderImportConfigPost**](docs/IdentityProvidersApi.md#realmidentityproviderimportconfigpost) | **POST** /{realm}/identity-provider/import-config | Import identity provider from uploaded JSON file
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasDelete**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasdelete) | **DELETE** /{realm}/identity-provider/instances/{alias} | Delete the identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasExportGet**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasexportget) | **GET** /{realm}/identity-provider/instances/{alias}/export | Export public broker configuration for identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasGet**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasget) | **GET** /{realm}/identity-provider/instances/{alias} | Get the identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasManagementPermissionsGet**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasmanagementpermissionsget) | **GET** /{realm}/identity-provider/instances/{alias}/management/permissions | Return object stating whether client Authorization permissions have been initialized or not and a reference
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasManagementPermissionsPut**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasmanagementpermissionsput) | **PUT** /{realm}/identity-provider/instances/{alias}/management/permissions | Return object stating whether client Authorization permissions have been initialized or not and a reference
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasMapperTypesGet**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasmappertypesget) | **GET** /{realm}/identity-provider/instances/{alias}/mapper-types | Get mapper types for identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasMappersGet**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasmappersget) | **GET** /{realm}/identity-provider/instances/{alias}/mappers | Get mappers for identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasMappersIdDelete**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasmappersiddelete) | **DELETE** /{realm}/identity-provider/instances/{alias}/mappers/{id} | Delete a mapper for the identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasMappersIdGet**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasmappersidget) | **GET** /{realm}/identity-provider/instances/{alias}/mappers/{id} | Get mapper by id for the identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasMappersIdPut**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasmappersidput) | **PUT** /{realm}/identity-provider/instances/{alias}/mappers/{id} | Update a mapper for the identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasMappersPost**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasmapperspost) | **POST** /{realm}/identity-provider/instances/{alias}/mappers | Add a mapper to identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesAliasPut**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesaliasput) | **PUT** /{realm}/identity-provider/instances/{alias} | Update the identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesGet**](docs/IdentityProvidersApi.md#realmidentityproviderinstancesget) | **GET** /{realm}/identity-provider/instances | Get identity providers
    *IdentityProvidersApi* | [**realmIdentityProviderInstancesPost**](docs/IdentityProvidersApi.md#realmidentityproviderinstancespost) | **POST** /{realm}/identity-provider/instances | Create a new identity provider
    *IdentityProvidersApi* | [**realmIdentityProviderProvidersProviderIdGet**](docs/IdentityProvidersApi.md#realmidentityproviderprovidersprovideridget) | **GET** /{realm}/identity-provider/providers/{provider_id} | Get identity providers
    *KeyApi* | [**realmKeysGet**](docs/KeyApi.md#realmkeysget) | **GET** /{realm}/keys | 
    *ProtocolMappersApi* | [**realmClientScopesId1ProtocolMappersModelsId2Delete**](docs/ProtocolMappersApi.md#realmclientscopesid1protocolmappersmodelsid2delete) | **DELETE** /{realm}/client-scopes/{id1}/protocol-mappers/models/{id2} | Delete the mapper
    *ProtocolMappersApi* | [**realmClientScopesId1ProtocolMappersModelsId2Get**](docs/ProtocolMappersApi.md#realmclientscopesid1protocolmappersmodelsid2get) | **GET** /{realm}/client-scopes/{id1}/protocol-mappers/models/{id2} | Get mapper by id
    *ProtocolMappersApi* | [**realmClientScopesId1ProtocolMappersModelsId2Put**](docs/ProtocolMappersApi.md#realmclientscopesid1protocolmappersmodelsid2put) | **PUT** /{realm}/client-scopes/{id1}/protocol-mappers/models/{id2} | Update the mapper
    *ProtocolMappersApi* | [**realmClientScopesIdProtocolMappersAddModelsPost**](docs/ProtocolMappersApi.md#realmclientscopesidprotocolmappersaddmodelspost) | **POST** /{realm}/client-scopes/{id}/protocol-mappers/add-models | Create multiple mappers
    *ProtocolMappersApi* | [**realmClientScopesIdProtocolMappersModelsGet**](docs/ProtocolMappersApi.md#realmclientscopesidprotocolmappersmodelsget) | **GET** /{realm}/client-scopes/{id}/protocol-mappers/models | Get mappers
    *ProtocolMappersApi* | [**realmClientScopesIdProtocolMappersModelsPost**](docs/ProtocolMappersApi.md#realmclientscopesidprotocolmappersmodelspost) | **POST** /{realm}/client-scopes/{id}/protocol-mappers/models | Create a mapper
    *ProtocolMappersApi* | [**realmClientScopesIdProtocolMappersProtocolProtocolGet**](docs/ProtocolMappersApi.md#realmclientscopesidprotocolmappersprotocolprotocolget) | **GET** /{realm}/client-scopes/{id}/protocol-mappers/protocol/{protocol} | Get mappers by name for a specific protocol
    *ProtocolMappersApi* | [**realmClientsId1ProtocolMappersModelsId2Delete**](docs/ProtocolMappersApi.md#realmclientsid1protocolmappersmodelsid2delete) | **DELETE** /{realm}/clients/{id1}/protocol-mappers/models/{id2} | Delete the mapper
    *ProtocolMappersApi* | [**realmClientsId1ProtocolMappersModelsId2Get**](docs/ProtocolMappersApi.md#realmclientsid1protocolmappersmodelsid2get) | **GET** /{realm}/clients/{id1}/protocol-mappers/models/{id2} | Get mapper by id
    *ProtocolMappersApi* | [**realmClientsId1ProtocolMappersModelsId2Put**](docs/ProtocolMappersApi.md#realmclientsid1protocolmappersmodelsid2put) | **PUT** /{realm}/clients/{id1}/protocol-mappers/models/{id2} | Update the mapper
    *ProtocolMappersApi* | [**realmClientsIdProtocolMappersAddModelsPost**](docs/ProtocolMappersApi.md#realmclientsidprotocolmappersaddmodelspost) | **POST** /{realm}/clients/{id}/protocol-mappers/add-models | Create multiple mappers
    *ProtocolMappersApi* | [**realmClientsIdProtocolMappersModelsGet**](docs/ProtocolMappersApi.md#realmclientsidprotocolmappersmodelsget) | **GET** /{realm}/clients/{id}/protocol-mappers/models | Get mappers
    *ProtocolMappersApi* | [**realmClientsIdProtocolMappersModelsPost**](docs/ProtocolMappersApi.md#realmclientsidprotocolmappersmodelspost) | **POST** /{realm}/clients/{id}/protocol-mappers/models | Create a mapper
    *ProtocolMappersApi* | [**realmClientsIdProtocolMappersProtocolProtocolGet**](docs/ProtocolMappersApi.md#realmclientsidprotocolmappersprotocolprotocolget) | **GET** /{realm}/clients/{id}/protocol-mappers/protocol/{protocol} | Get mappers by name for a specific protocol
    *RealmsAdminApi* | [**realmAdminEventsDelete**](docs/RealmsAdminApi.md#realmadmineventsdelete) | **DELETE** /{realm}/admin-events | Delete all admin events
    *RealmsAdminApi* | [**realmAdminEventsGet**](docs/RealmsAdminApi.md#realmadmineventsget) | **GET** /{realm}/admin-events | Get admin events   Returns all admin events, or filters events based on URL query parameters listed here
    *RealmsAdminApi* | [**realmClearKeysCachePost**](docs/RealmsAdminApi.md#realmclearkeyscachepost) | **POST** /{realm}/clear-keys-cache | Clear cache of external public keys (Public keys of clients or Identity providers)
    *RealmsAdminApi* | [**realmClearRealmCachePost**](docs/RealmsAdminApi.md#realmclearrealmcachepost) | **POST** /{realm}/clear-realm-cache | Clear realm cache
    *RealmsAdminApi* | [**realmClearUserCachePost**](docs/RealmsAdminApi.md#realmclearusercachepost) | **POST** /{realm}/clear-user-cache | Clear user cache
    *RealmsAdminApi* | [**realmClientDescriptionConverterPost**](docs/RealmsAdminApi.md#realmclientdescriptionconverterpost) | **POST** /{realm}/client-description-converter | Base path for importing clients under this realm.
    *RealmsAdminApi* | [**realmClientPoliciesPoliciesGet**](docs/RealmsAdminApi.md#realmclientpoliciespoliciesget) | **GET** /{realm}/client-policies/policies | 
    *RealmsAdminApi* | [**realmClientPoliciesPoliciesPut**](docs/RealmsAdminApi.md#realmclientpoliciespoliciesput) | **PUT** /{realm}/client-policies/policies | 
    *RealmsAdminApi* | [**realmClientPoliciesProfilesGet**](docs/RealmsAdminApi.md#realmclientpoliciesprofilesget) | **GET** /{realm}/client-policies/profiles | 
    *RealmsAdminApi* | [**realmClientPoliciesProfilesPut**](docs/RealmsAdminApi.md#realmclientpoliciesprofilesput) | **PUT** /{realm}/client-policies/profiles | 
    *RealmsAdminApi* | [**realmClientSessionStatsGet**](docs/RealmsAdminApi.md#realmclientsessionstatsget) | **GET** /{realm}/client-session-stats | Get client session stats   Returns a JSON map.
    *RealmsAdminApi* | [**realmCredentialRegistratorsGet**](docs/RealmsAdminApi.md#realmcredentialregistratorsget) | **GET** /{realm}/credential-registrators | 
    *RealmsAdminApi* | [**realmDefaultDefaultClientScopesClientScopeIdDelete**](docs/RealmsAdminApi.md#realmdefaultdefaultclientscopesclientscopeiddelete) | **DELETE** /{realm}/default-default-client-scopes/{clientScopeId} | 
    *RealmsAdminApi* | [**realmDefaultDefaultClientScopesClientScopeIdPut**](docs/RealmsAdminApi.md#realmdefaultdefaultclientscopesclientscopeidput) | **PUT** /{realm}/default-default-client-scopes/{clientScopeId} | 
    *RealmsAdminApi* | [**realmDefaultDefaultClientScopesGet**](docs/RealmsAdminApi.md#realmdefaultdefaultclientscopesget) | **GET** /{realm}/default-default-client-scopes | Get realm default client scopes.
    *RealmsAdminApi* | [**realmDefaultGroupsGet**](docs/RealmsAdminApi.md#realmdefaultgroupsget) | **GET** /{realm}/default-groups | Get group hierarchy.
    *RealmsAdminApi* | [**realmDefaultGroupsGroupIdDelete**](docs/RealmsAdminApi.md#realmdefaultgroupsgroupiddelete) | **DELETE** /{realm}/default-groups/{groupId} | 
    *RealmsAdminApi* | [**realmDefaultGroupsGroupIdPut**](docs/RealmsAdminApi.md#realmdefaultgroupsgroupidput) | **PUT** /{realm}/default-groups/{groupId} | 
    *RealmsAdminApi* | [**realmDefaultOptionalClientScopesClientScopeIdDelete**](docs/RealmsAdminApi.md#realmdefaultoptionalclientscopesclientscopeiddelete) | **DELETE** /{realm}/default-optional-client-scopes/{clientScopeId} | 
    *RealmsAdminApi* | [**realmDefaultOptionalClientScopesClientScopeIdPut**](docs/RealmsAdminApi.md#realmdefaultoptionalclientscopesclientscopeidput) | **PUT** /{realm}/default-optional-client-scopes/{clientScopeId} | 
    *RealmsAdminApi* | [**realmDefaultOptionalClientScopesGet**](docs/RealmsAdminApi.md#realmdefaultoptionalclientscopesget) | **GET** /{realm}/default-optional-client-scopes | Get realm optional client scopes.
    *RealmsAdminApi* | [**realmDelete**](docs/RealmsAdminApi.md#realmdelete) | **DELETE** /{realm} | Delete the realm
    *RealmsAdminApi* | [**realmEventsConfigGet**](docs/RealmsAdminApi.md#realmeventsconfigget) | **GET** /{realm}/events/config | Get the events provider configuration   Returns JSON object with events provider configuration
    *RealmsAdminApi* | [**realmEventsConfigPut**](docs/RealmsAdminApi.md#realmeventsconfigput) | **PUT** /{realm}/events/config | Update the events provider   Change the events provider and/or its configuration
    *RealmsAdminApi* | [**realmEventsDelete**](docs/RealmsAdminApi.md#realmeventsdelete) | **DELETE** /{realm}/events | Delete all events
    *RealmsAdminApi* | [**realmEventsGet**](docs/RealmsAdminApi.md#realmeventsget) | **GET** /{realm}/events | Get events   Returns all events, or filters them based on URL query parameters listed here
    *RealmsAdminApi* | [**realmGet**](docs/RealmsAdminApi.md#realmget) | **GET** /{realm} | Get the top-level representation of the realm   It will not include nested information like User and Client representations.
    *RealmsAdminApi* | [**realmGroupByPathPathGet**](docs/RealmsAdminApi.md#realmgroupbypathpathget) | **GET** /{realm}/group-by-path/{path} | 
    *RealmsAdminApi* | [**realmLdapServerCapabilitiesPost**](docs/RealmsAdminApi.md#realmldapservercapabilitiespost) | **POST** /{realm}/ldap-server-capabilities | Get LDAP supported extensions.
    *RealmsAdminApi* | [**realmLocalizationGet**](docs/RealmsAdminApi.md#realmlocalizationget) | **GET** /{realm}/localization | 
    *RealmsAdminApi* | [**realmLocalizationLocaleDelete**](docs/RealmsAdminApi.md#realmlocalizationlocaledelete) | **DELETE** /{realm}/localization/{locale} | 
    *RealmsAdminApi* | [**realmLocalizationLocaleGet**](docs/RealmsAdminApi.md#realmlocalizationlocaleget) | **GET** /{realm}/localization/{locale} | 
    *RealmsAdminApi* | [**realmLocalizationLocaleKeyDelete**](docs/RealmsAdminApi.md#realmlocalizationlocalekeydelete) | **DELETE** /{realm}/localization/{locale}/{key} | 
    *RealmsAdminApi* | [**realmLocalizationLocaleKeyGet**](docs/RealmsAdminApi.md#realmlocalizationlocalekeyget) | **GET** /{realm}/localization/{locale}/{key} | 
    *RealmsAdminApi* | [**realmLocalizationLocaleKeyPut**](docs/RealmsAdminApi.md#realmlocalizationlocalekeyput) | **PUT** /{realm}/localization/{locale}/{key} | 
    *RealmsAdminApi* | [**realmLocalizationLocalePatch**](docs/RealmsAdminApi.md#realmlocalizationlocalepatch) | **PATCH** /{realm}/localization/{locale} | 
    *RealmsAdminApi* | [**realmLocalizationLocalePost**](docs/RealmsAdminApi.md#realmlocalizationlocalepost) | **POST** /{realm}/localization/{locale} | Import localization from uploaded JSON file
    *RealmsAdminApi* | [**realmLogoutAllPost**](docs/RealmsAdminApi.md#realmlogoutallpost) | **POST** /{realm}/logout-all | Removes all user sessions.
    *RealmsAdminApi* | [**realmPartialExportPost**](docs/RealmsAdminApi.md#realmpartialexportpost) | **POST** /{realm}/partial-export | Partial export of existing realm into a JSON file.
    *RealmsAdminApi* | [**realmPartialImportPost**](docs/RealmsAdminApi.md#realmpartialimportpost) | **POST** /{realm}/partialImport | Partial import from a JSON file to an existing realm.
    *RealmsAdminApi* | [**realmPushRevocationPost**](docs/RealmsAdminApi.md#realmpushrevocationpost) | **POST** /{realm}/push-revocation | Push the realm’s revocation policy to any client that has an admin url associated with it.
    *RealmsAdminApi* | [**realmPut**](docs/RealmsAdminApi.md#realmput) | **PUT** /{realm} | Update the top-level information of the realm   Any user, roles or client information in the representation  will be ignored.
    *RealmsAdminApi* | [**realmSessionsSessionDelete**](docs/RealmsAdminApi.md#realmsessionssessiondelete) | **DELETE** /{realm}/sessions/{session} | Remove a specific user session.
    *RealmsAdminApi* | [**realmTestLDAPConnectionPost**](docs/RealmsAdminApi.md#realmtestldapconnectionpost) | **POST** /{realm}/testLDAPConnection | Test LDAP connection
    *RealmsAdminApi* | [**realmTestSMTPConnectionPost**](docs/RealmsAdminApi.md#realmtestsmtpconnectionpost) | **POST** /{realm}/testSMTPConnection | 
    *RealmsAdminApi* | [**realmUsersManagementPermissionsGet**](docs/RealmsAdminApi.md#realmusersmanagementpermissionsget) | **GET** /{realm}/users-management-permissions | 
    *RealmsAdminApi* | [**realmUsersManagementPermissionsPut**](docs/RealmsAdminApi.md#realmusersmanagementpermissionsput) | **PUT** /{realm}/users-management-permissions | 
    *RealmsAdminApi* | [**rootPost**](docs/RealmsAdminApi.md#rootpost) | **POST** / | Import a realm   Imports a realm from a full representation of that realm.
    *RoleMapperApi* | [**realmGroupsIdRoleMappingsGet**](docs/RoleMapperApi.md#realmgroupsidrolemappingsget) | **GET** /{realm}/groups/{id}/role-mappings | Get role mappings
    *RoleMapperApi* | [**realmGroupsIdRoleMappingsRealmAvailableGet**](docs/RoleMapperApi.md#realmgroupsidrolemappingsrealmavailableget) | **GET** /{realm}/groups/{id}/role-mappings/realm/available | Get realm-level roles that can be mapped
    *RoleMapperApi* | [**realmGroupsIdRoleMappingsRealmCompositeGet**](docs/RoleMapperApi.md#realmgroupsidrolemappingsrealmcompositeget) | **GET** /{realm}/groups/{id}/role-mappings/realm/composite | Get effective realm-level role mappings   This will recurse all composite roles to get the result.
    *RoleMapperApi* | [**realmGroupsIdRoleMappingsRealmDelete**](docs/RoleMapperApi.md#realmgroupsidrolemappingsrealmdelete) | **DELETE** /{realm}/groups/{id}/role-mappings/realm | Delete realm-level role mappings
    *RoleMapperApi* | [**realmGroupsIdRoleMappingsRealmGet**](docs/RoleMapperApi.md#realmgroupsidrolemappingsrealmget) | **GET** /{realm}/groups/{id}/role-mappings/realm | Get realm-level role mappings
    *RoleMapperApi* | [**realmGroupsIdRoleMappingsRealmPost**](docs/RoleMapperApi.md#realmgroupsidrolemappingsrealmpost) | **POST** /{realm}/groups/{id}/role-mappings/realm | Add realm-level role mappings to the user
    *RoleMapperApi* | [**realmUsersIdRoleMappingsGet**](docs/RoleMapperApi.md#realmusersidrolemappingsget) | **GET** /{realm}/users/{id}/role-mappings | Get role mappings
    *RoleMapperApi* | [**realmUsersIdRoleMappingsRealmAvailableGet**](docs/RoleMapperApi.md#realmusersidrolemappingsrealmavailableget) | **GET** /{realm}/users/{id}/role-mappings/realm/available | Get realm-level roles that can be mapped
    *RoleMapperApi* | [**realmUsersIdRoleMappingsRealmCompositeGet**](docs/RoleMapperApi.md#realmusersidrolemappingsrealmcompositeget) | **GET** /{realm}/users/{id}/role-mappings/realm/composite | Get effective realm-level role mappings   This will recurse all composite roles to get the result.
    *RoleMapperApi* | [**realmUsersIdRoleMappingsRealmDelete**](docs/RoleMapperApi.md#realmusersidrolemappingsrealmdelete) | **DELETE** /{realm}/users/{id}/role-mappings/realm | Delete realm-level role mappings
    *RoleMapperApi* | [**realmUsersIdRoleMappingsRealmGet**](docs/RoleMapperApi.md#realmusersidrolemappingsrealmget) | **GET** /{realm}/users/{id}/role-mappings/realm | Get realm-level role mappings
    *RoleMapperApi* | [**realmUsersIdRoleMappingsRealmPost**](docs/RoleMapperApi.md#realmusersidrolemappingsrealmpost) | **POST** /{realm}/users/{id}/role-mappings/realm | Add realm-level role mappings to the user
    *RolesApi* | [**realmClientsIdRolesGet**](docs/RolesApi.md#realmclientsidrolesget) | **GET** /{realm}/clients/{id}/roles | Get all roles for the realm or client
    *RolesApi* | [**realmClientsIdRolesPost**](docs/RolesApi.md#realmclientsidrolespost) | **POST** /{realm}/clients/{id}/roles | Create a new role for the realm or client
    *RolesApi* | [**realmClientsIdRolesRoleNameCompositesClientsClientUuidGet**](docs/RolesApi.md#realmclientsidrolesrolenamecompositesclientsclientuuidget) | **GET** /{realm}/clients/{id}/roles/{role-name}/composites/clients/{clientUuid} | Get client-level roles for the client that are in the role’s composite
    *RolesApi* | [**realmClientsIdRolesRoleNameCompositesDelete**](docs/RolesApi.md#realmclientsidrolesrolenamecompositesdelete) | **DELETE** /{realm}/clients/{id}/roles/{role-name}/composites | Remove roles from the role’s composite
    *RolesApi* | [**realmClientsIdRolesRoleNameCompositesGet**](docs/RolesApi.md#realmclientsidrolesrolenamecompositesget) | **GET** /{realm}/clients/{id}/roles/{role-name}/composites | Get composites of the role
    *RolesApi* | [**realmClientsIdRolesRoleNameCompositesPost**](docs/RolesApi.md#realmclientsidrolesrolenamecompositespost) | **POST** /{realm}/clients/{id}/roles/{role-name}/composites | Add a composite to the role
    *RolesApi* | [**realmClientsIdRolesRoleNameCompositesRealmGet**](docs/RolesApi.md#realmclientsidrolesrolenamecompositesrealmget) | **GET** /{realm}/clients/{id}/roles/{role-name}/composites/realm | Get realm-level roles of the role’s composite
    *RolesApi* | [**realmClientsIdRolesRoleNameDelete**](docs/RolesApi.md#realmclientsidrolesrolenamedelete) | **DELETE** /{realm}/clients/{id}/roles/{role-name} | Delete a role by name
    *RolesApi* | [**realmClientsIdRolesRoleNameGet**](docs/RolesApi.md#realmclientsidrolesrolenameget) | **GET** /{realm}/clients/{id}/roles/{role-name} | Get a role by name
    *RolesApi* | [**realmClientsIdRolesRoleNameGroupsGet**](docs/RolesApi.md#realmclientsidrolesrolenamegroupsget) | **GET** /{realm}/clients/{id}/roles/{role-name}/groups | Returns a stream of groups that have the specified role name
    *RolesApi* | [**realmClientsIdRolesRoleNameManagementPermissionsGet**](docs/RolesApi.md#realmclientsidrolesrolenamemanagementpermissionsget) | **GET** /{realm}/clients/{id}/roles/{role-name}/management/permissions | Return object stating whether role Authorization permissions have been initialized or not and a reference
    *RolesApi* | [**realmClientsIdRolesRoleNameManagementPermissionsPut**](docs/RolesApi.md#realmclientsidrolesrolenamemanagementpermissionsput) | **PUT** /{realm}/clients/{id}/roles/{role-name}/management/permissions | Return object stating whether role Authorization permissions have been initialized or not and a reference
    *RolesApi* | [**realmClientsIdRolesRoleNamePut**](docs/RolesApi.md#realmclientsidrolesrolenameput) | **PUT** /{realm}/clients/{id}/roles/{role-name} | Update a role by name
    *RolesApi* | [**realmClientsIdRolesRoleNameUsersGet**](docs/RolesApi.md#realmclientsidrolesrolenameusersget) | **GET** /{realm}/clients/{id}/roles/{role-name}/users | Returns a stream of users that have the specified role name.
    *RolesApi* | [**realmRolesGet**](docs/RolesApi.md#realmrolesget) | **GET** /{realm}/roles | Get all roles for the realm or client
    *RolesApi* | [**realmRolesPost**](docs/RolesApi.md#realmrolespost) | **POST** /{realm}/roles | Create a new role for the realm or client
    *RolesApi* | [**realmRolesRoleNameCompositesClientsClientUuidGet**](docs/RolesApi.md#realmrolesrolenamecompositesclientsclientuuidget) | **GET** /{realm}/roles/{role-name}/composites/clients/{clientUuid} | Get client-level roles for the client that are in the role’s composite
    *RolesApi* | [**realmRolesRoleNameCompositesDelete**](docs/RolesApi.md#realmrolesrolenamecompositesdelete) | **DELETE** /{realm}/roles/{role-name}/composites | Remove roles from the role’s composite
    *RolesApi* | [**realmRolesRoleNameCompositesGet**](docs/RolesApi.md#realmrolesrolenamecompositesget) | **GET** /{realm}/roles/{role-name}/composites | Get composites of the role
    *RolesApi* | [**realmRolesRoleNameCompositesPost**](docs/RolesApi.md#realmrolesrolenamecompositespost) | **POST** /{realm}/roles/{role-name}/composites | Add a composite to the role
    *RolesApi* | [**realmRolesRoleNameCompositesRealmGet**](docs/RolesApi.md#realmrolesrolenamecompositesrealmget) | **GET** /{realm}/roles/{role-name}/composites/realm | Get realm-level roles of the role’s composite
    *RolesApi* | [**realmRolesRoleNameDelete**](docs/RolesApi.md#realmrolesrolenamedelete) | **DELETE** /{realm}/roles/{role-name} | Delete a role by name
    *RolesApi* | [**realmRolesRoleNameGet**](docs/RolesApi.md#realmrolesrolenameget) | **GET** /{realm}/roles/{role-name} | Get a role by name
    *RolesApi* | [**realmRolesRoleNameGroupsGet**](docs/RolesApi.md#realmrolesrolenamegroupsget) | **GET** /{realm}/roles/{role-name}/groups | Returns a stream of groups that have the specified role name
    *RolesApi* | [**realmRolesRoleNameManagementPermissionsGet**](docs/RolesApi.md#realmrolesrolenamemanagementpermissionsget) | **GET** /{realm}/roles/{role-name}/management/permissions | Return object stating whether role Authorization permissions have been initialized or not and a reference
    *RolesApi* | [**realmRolesRoleNameManagementPermissionsPut**](docs/RolesApi.md#realmrolesrolenamemanagementpermissionsput) | **PUT** /{realm}/roles/{role-name}/management/permissions | Return object stating whether role Authorization permissions have been initialized or not and a reference
    *RolesApi* | [**realmRolesRoleNamePut**](docs/RolesApi.md#realmrolesrolenameput) | **PUT** /{realm}/roles/{role-name} | Update a role by name
    *RolesApi* | [**realmRolesRoleNameUsersGet**](docs/RolesApi.md#realmrolesrolenameusersget) | **GET** /{realm}/roles/{role-name}/users | Returns a stream of users that have the specified role name.
    *RolesByIDApi* | [**realmRolesByIdRoleIdCompositesClientsClientUuidGet**](docs/RolesByIDApi.md#realmrolesbyidroleidcompositesclientsclientuuidget) | **GET** /{realm}/roles-by-id/{role-id}/composites/clients/{clientUuid} | Get client-level roles for the client that are in the role’s composite
    *RolesByIDApi* | [**realmRolesByIdRoleIdCompositesDelete**](docs/RolesByIDApi.md#realmrolesbyidroleidcompositesdelete) | **DELETE** /{realm}/roles-by-id/{role-id}/composites | Remove a set of roles from the role’s composite
    *RolesByIDApi* | [**realmRolesByIdRoleIdCompositesGet**](docs/RolesByIDApi.md#realmrolesbyidroleidcompositesget) | **GET** /{realm}/roles-by-id/{role-id}/composites | Get role’s children   Returns a set of role’s children provided the role is a composite.
    *RolesByIDApi* | [**realmRolesByIdRoleIdCompositesPost**](docs/RolesByIDApi.md#realmrolesbyidroleidcompositespost) | **POST** /{realm}/roles-by-id/{role-id}/composites | Make the role a composite role by associating some child roles
    *RolesByIDApi* | [**realmRolesByIdRoleIdCompositesRealmGet**](docs/RolesByIDApi.md#realmrolesbyidroleidcompositesrealmget) | **GET** /{realm}/roles-by-id/{role-id}/composites/realm | Get realm-level roles that are in the role’s composite
    *RolesByIDApi* | [**realmRolesByIdRoleIdDelete**](docs/RolesByIDApi.md#realmrolesbyidroleiddelete) | **DELETE** /{realm}/roles-by-id/{role-id} | Delete the role
    *RolesByIDApi* | [**realmRolesByIdRoleIdGet**](docs/RolesByIDApi.md#realmrolesbyidroleidget) | **GET** /{realm}/roles-by-id/{role-id} | Get a specific role’s representation
    *RolesByIDApi* | [**realmRolesByIdRoleIdManagementPermissionsGet**](docs/RolesByIDApi.md#realmrolesbyidroleidmanagementpermissionsget) | **GET** /{realm}/roles-by-id/{role-id}/management/permissions | Return object stating whether role Authoirzation permissions have been initialized or not and a reference
    *RolesByIDApi* | [**realmRolesByIdRoleIdManagementPermissionsPut**](docs/RolesByIDApi.md#realmrolesbyidroleidmanagementpermissionsput) | **PUT** /{realm}/roles-by-id/{role-id}/management/permissions | Return object stating whether role Authoirzation permissions have been initialized or not and a reference
    *RolesByIDApi* | [**realmRolesByIdRoleIdPut**](docs/RolesByIDApi.md#realmrolesbyidroleidput) | **PUT** /{realm}/roles-by-id/{role-id} | Update the role
    *RootApi* | [**rootGet**](docs/RootApi.md#rootget) | **GET** / | Get themes, social providers, auth providers, and event listeners available on this server
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsClientsClientAvailableGet**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsclientsclientavailableget) | **GET** /{realm}/client-scopes/{id}/scope-mappings/clients/{client}/available | The available client-level roles   Returns the roles for the client that can be associated with the client’s scope
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsClientsClientCompositeGet**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsclientsclientcompositeget) | **GET** /{realm}/client-scopes/{id}/scope-mappings/clients/{client}/composite | Get effective client roles   Returns the roles for the client that are associated with the client’s scope.
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsClientsClientDelete**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsclientsclientdelete) | **DELETE** /{realm}/client-scopes/{id}/scope-mappings/clients/{client} | Remove client-level roles from the client’s scope.
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsClientsClientGet**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsclientsclientget) | **GET** /{realm}/client-scopes/{id}/scope-mappings/clients/{client} | Get the roles associated with a client’s scope   Returns roles for the client.
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsClientsClientPost**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsclientsclientpost) | **POST** /{realm}/client-scopes/{id}/scope-mappings/clients/{client} | Add client-level roles to the client’s scope
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsRealmAvailableGet**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsrealmavailableget) | **GET** /{realm}/client-scopes/{id}/scope-mappings/realm/available | Get realm-level roles that are available to attach to this client’s scope
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsRealmCompositeGet**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsrealmcompositeget) | **GET** /{realm}/client-scopes/{id}/scope-mappings/realm/composite | Get effective realm-level roles associated with the client’s scope   What this does is recurse  any composite roles associated with the client’s scope and adds the roles to this lists.
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsRealmDelete**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsrealmdelete) | **DELETE** /{realm}/client-scopes/{id}/scope-mappings/realm | Remove a set of realm-level roles from the client’s scope
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsRealmGet**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsrealmget) | **GET** /{realm}/client-scopes/{id}/scope-mappings/realm | Get realm-level roles associated with the client’s scope
    *ScopeMappingsApi* | [**realmClientScopesIdScopeMappingsRealmPost**](docs/ScopeMappingsApi.md#realmclientscopesidscopemappingsrealmpost) | **POST** /{realm}/client-scopes/{id}/scope-mappings/realm | Add a set of realm-level roles to the client’s scope
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsClientsClientAvailableGet**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsclientsclientavailableget) | **GET** /{realm}/clients/{id}/scope-mappings/clients/{client}/available | The available client-level roles   Returns the roles for the client that can be associated with the client’s scope
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsClientsClientCompositeGet**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsclientsclientcompositeget) | **GET** /{realm}/clients/{id}/scope-mappings/clients/{client}/composite | Get effective client roles   Returns the roles for the client that are associated with the client’s scope.
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsClientsClientDelete**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsclientsclientdelete) | **DELETE** /{realm}/clients/{id}/scope-mappings/clients/{client} | Remove client-level roles from the client’s scope.
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsClientsClientGet**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsclientsclientget) | **GET** /{realm}/clients/{id}/scope-mappings/clients/{client} | Get the roles associated with a client’s scope   Returns roles for the client.
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsClientsClientPost**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsclientsclientpost) | **POST** /{realm}/clients/{id}/scope-mappings/clients/{client} | Add client-level roles to the client’s scope
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsRealmAvailableGet**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsrealmavailableget) | **GET** /{realm}/clients/{id}/scope-mappings/realm/available | Get realm-level roles that are available to attach to this client’s scope
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsRealmCompositeGet**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsrealmcompositeget) | **GET** /{realm}/clients/{id}/scope-mappings/realm/composite | Get effective realm-level roles associated with the client’s scope   What this does is recurse  any composite roles associated with the client’s scope and adds the roles to this lists.
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsRealmDelete**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsrealmdelete) | **DELETE** /{realm}/clients/{id}/scope-mappings/realm | Remove a set of realm-level roles from the client’s scope
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsRealmGet**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsrealmget) | **GET** /{realm}/clients/{id}/scope-mappings/realm | Get realm-level roles associated with the client’s scope
    *ScopeMappingsApi* | [**realmClientsIdScopeMappingsRealmPost**](docs/ScopeMappingsApi.md#realmclientsidscopemappingsrealmpost) | **POST** /{realm}/clients/{id}/scope-mappings/realm | Add a set of realm-level roles to the client’s scope
    *UserStorageProviderApi* | [**idNameGet**](docs/UserStorageProviderApi.md#idnameget) | **GET** /{id}/name | Need this for admin console to display simple name of provider when displaying client detail   KEYCLOAK-4328
    *UserStorageProviderApi* | [**realmUserStorageIdNameGet**](docs/UserStorageProviderApi.md#realmuserstorageidnameget) | **GET** /{realm}/user-storage/{id}/name | Need this for admin console to display simple name of provider when displaying user detail   KEYCLOAK-4328
    *UserStorageProviderApi* | [**realmUserStorageIdRemoveImportedUsersPost**](docs/UserStorageProviderApi.md#realmuserstorageidremoveimporteduserspost) | **POST** /{realm}/user-storage/{id}/remove-imported-users | Remove imported users
    *UserStorageProviderApi* | [**realmUserStorageIdSyncPost**](docs/UserStorageProviderApi.md#realmuserstorageidsyncpost) | **POST** /{realm}/user-storage/{id}/sync | Trigger sync of users   Action can be \"triggerFullSync\" or \"triggerChangedUsersSync\"
    *UserStorageProviderApi* | [**realmUserStorageIdUnlinkUsersPost**](docs/UserStorageProviderApi.md#realmuserstorageidunlinkuserspost) | **POST** /{realm}/user-storage/{id}/unlink-users | Unlink imported users from a storage provider
    *UserStorageProviderApi* | [**realmUserStorageParentIdMappersIdSyncPost**](docs/UserStorageProviderApi.md#realmuserstorageparentidmappersidsyncpost) | **POST** /{realm}/user-storage/{parentId}/mappers/{id}/sync | Trigger sync of mapper data related to ldap mapper (roles, groups, …​)   direction is \"fedToKeycloak\" or \"keycloakToFed\"
    *UsersApi* | [**realmUsersCountGet**](docs/UsersApi.md#realmuserscountget) | **GET** /{realm}/users/count | Returns the number of users that match the given criteria.
    *UsersApi* | [**realmUsersGet**](docs/UsersApi.md#realmusersget) | **GET** /{realm}/users | Get users   Returns a stream of users, filtered according to query parameters
    *UsersApi* | [**realmUsersIdConfiguredUserStorageCredentialTypesGet**](docs/UsersApi.md#realmusersidconfigureduserstoragecredentialtypesget) | **GET** /{realm}/users/{id}/configured-user-storage-credential-types | Return credential types, which are provided by the user storage where user is stored.
    *UsersApi* | [**realmUsersIdConsentsClientDelete**](docs/UsersApi.md#realmusersidconsentsclientdelete) | **DELETE** /{realm}/users/{id}/consents/{client} | Revoke consent and offline tokens for particular client from user
    *UsersApi* | [**realmUsersIdConsentsGet**](docs/UsersApi.md#realmusersidconsentsget) | **GET** /{realm}/users/{id}/consents | Get consents granted by the user
    *UsersApi* | [**realmUsersIdCredentialsCredentialIdDelete**](docs/UsersApi.md#realmusersidcredentialscredentialiddelete) | **DELETE** /{realm}/users/{id}/credentials/{credentialId} | Remove a credential for a user
    *UsersApi* | [**realmUsersIdCredentialsCredentialIdMoveAfterNewPreviousCredentialIdPost**](docs/UsersApi.md#realmusersidcredentialscredentialidmoveafternewpreviouscredentialidpost) | **POST** /{realm}/users/{id}/credentials/{credentialId}/moveAfter/{newPreviousCredentialId} | Move a credential to a position behind another credential
    *UsersApi* | [**realmUsersIdCredentialsCredentialIdMoveToFirstPost**](docs/UsersApi.md#realmusersidcredentialscredentialidmovetofirstpost) | **POST** /{realm}/users/{id}/credentials/{credentialId}/moveToFirst | Move a credential to a first position in the credentials list of the user
    *UsersApi* | [**realmUsersIdCredentialsCredentialIdUserLabelPut**](docs/UsersApi.md#realmusersidcredentialscredentialiduserlabelput) | **PUT** /{realm}/users/{id}/credentials/{credentialId}/userLabel | Update a credential label for a user
    *UsersApi* | [**realmUsersIdCredentialsGet**](docs/UsersApi.md#realmusersidcredentialsget) | **GET** /{realm}/users/{id}/credentials | 
    *UsersApi* | [**realmUsersIdDelete**](docs/UsersApi.md#realmusersiddelete) | **DELETE** /{realm}/users/{id} | Delete the user
    *UsersApi* | [**realmUsersIdDisableCredentialTypesPut**](docs/UsersApi.md#realmusersiddisablecredentialtypesput) | **PUT** /{realm}/users/{id}/disable-credential-types | Disable all credentials for a user of a specific type
    *UsersApi* | [**realmUsersIdExecuteActionsEmailPut**](docs/UsersApi.md#realmusersidexecuteactionsemailput) | **PUT** /{realm}/users/{id}/execute-actions-email | Send a update account email to the user   An email contains a link the user can click to perform a set of required actions.
    *UsersApi* | [**realmUsersIdFederatedIdentityGet**](docs/UsersApi.md#realmusersidfederatedidentityget) | **GET** /{realm}/users/{id}/federated-identity | Get social logins associated with the user
    *UsersApi* | [**realmUsersIdFederatedIdentityProviderDelete**](docs/UsersApi.md#realmusersidfederatedidentityproviderdelete) | **DELETE** /{realm}/users/{id}/federated-identity/{provider} | Remove a social login provider from user
    *UsersApi* | [**realmUsersIdFederatedIdentityProviderPost**](docs/UsersApi.md#realmusersidfederatedidentityproviderpost) | **POST** /{realm}/users/{id}/federated-identity/{provider} | Add a social login provider to the user
    *UsersApi* | [**realmUsersIdGet**](docs/UsersApi.md#realmusersidget) | **GET** /{realm}/users/{id} | Get representation of the user
    *UsersApi* | [**realmUsersIdGroupsCountGet**](docs/UsersApi.md#realmusersidgroupscountget) | **GET** /{realm}/users/{id}/groups/count | 
    *UsersApi* | [**realmUsersIdGroupsGet**](docs/UsersApi.md#realmusersidgroupsget) | **GET** /{realm}/users/{id}/groups | 
    *UsersApi* | [**realmUsersIdGroupsGroupIdDelete**](docs/UsersApi.md#realmusersidgroupsgroupiddelete) | **DELETE** /{realm}/users/{id}/groups/{groupId} | 
    *UsersApi* | [**realmUsersIdGroupsGroupIdPut**](docs/UsersApi.md#realmusersidgroupsgroupidput) | **PUT** /{realm}/users/{id}/groups/{groupId} | 
    *UsersApi* | [**realmUsersIdImpersonationPost**](docs/UsersApi.md#realmusersidimpersonationpost) | **POST** /{realm}/users/{id}/impersonation | Impersonate the user
    *UsersApi* | [**realmUsersIdLogoutPost**](docs/UsersApi.md#realmusersidlogoutpost) | **POST** /{realm}/users/{id}/logout | Remove all user sessions associated with the user   Also send notification to all clients that have an admin URL to invalidate the sessions for the particular user.
    *UsersApi* | [**realmUsersIdOfflineSessionsClientUuidGet**](docs/UsersApi.md#realmusersidofflinesessionsclientuuidget) | **GET** /{realm}/users/{id}/offline-sessions/{clientUuid} | Get offline sessions associated with the user and client
    *UsersApi* | [**realmUsersIdPut**](docs/UsersApi.md#realmusersidput) | **PUT** /{realm}/users/{id} | Update the user
    *UsersApi* | [**realmUsersIdResetPasswordPut**](docs/UsersApi.md#realmusersidresetpasswordput) | **PUT** /{realm}/users/{id}/reset-password | Set up a new password for the user.
    *UsersApi* | [**realmUsersIdSendVerifyEmailPut**](docs/UsersApi.md#realmusersidsendverifyemailput) | **PUT** /{realm}/users/{id}/send-verify-email | Send an email-verification email to the user   An email contains a link the user can click to verify their email address.
    *UsersApi* | [**realmUsersIdSessionsGet**](docs/UsersApi.md#realmusersidsessionsget) | **GET** /{realm}/users/{id}/sessions | Get sessions associated with the user
    *UsersApi* | [**realmUsersPost**](docs/UsersApi.md#realmuserspost) | **POST** /{realm}/users | Create a new user   Username must be unique.
    *UsersApi* | [**realmUsersProfileGet**](docs/UsersApi.md#realmusersprofileget) | **GET** /{realm}/users/profile | 
    *UsersApi* | [**realmUsersProfilePut**](docs/UsersApi.md#realmusersprofileput) | **PUT** /{realm}/users/profile | 
    

    <a name="documentation-for-models"></a>
    ## Documentation for Models

         - [fi.metatavu.keycloak.adminclient.models.AccessToken](docs/AccessToken.md)
         - [fi.metatavu.keycloak.adminclient.models.AccessTokenMinusAccess](docs/AccessTokenMinusAccess.md)
         - [fi.metatavu.keycloak.adminclient.models.AccessTokenMinusAuthorization](docs/AccessTokenMinusAuthorization.md)
         - [fi.metatavu.keycloak.adminclient.models.AccessTokenMinusCertConf](docs/AccessTokenMinusCertConf.md)
         - [fi.metatavu.keycloak.adminclient.models.AddressClaimSet](docs/AddressClaimSet.md)
         - [fi.metatavu.keycloak.adminclient.models.AuthenticationExecutionExportRepresentation](docs/AuthenticationExecutionExportRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.AuthenticationExecutionInfoRepresentation](docs/AuthenticationExecutionInfoRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.AuthenticationExecutionRepresentation](docs/AuthenticationExecutionRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.AuthenticationFlowRepresentation](docs/AuthenticationFlowRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.AuthenticatorConfigInfoRepresentation](docs/AuthenticatorConfigInfoRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.AuthenticatorConfigRepresentation](docs/AuthenticatorConfigRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.CertificateRepresentation](docs/CertificateRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientInitialAccessCreatePresentation](docs/ClientInitialAccessCreatePresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientInitialAccessPresentation](docs/ClientInitialAccessPresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientMappingsRepresentation](docs/ClientMappingsRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientPoliciesRepresentation](docs/ClientPoliciesRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientPolicyConditionRepresentation](docs/ClientPolicyConditionRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientPolicyExecutorRepresentation](docs/ClientPolicyExecutorRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientPolicyRepresentation](docs/ClientPolicyRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientProfileRepresentation](docs/ClientProfileRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientProfilesRepresentation](docs/ClientProfilesRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientRepresentation](docs/ClientRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientScopeEvaluateResourceMinusProtocolMapperEvaluationRepresentation](docs/ClientScopeEvaluateResourceMinusProtocolMapperEvaluationRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ClientScopeRepresentation](docs/ClientScopeRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ComponentExportRepresentation](docs/ComponentExportRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ComponentRepresentation](docs/ComponentRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ConfigPropertyRepresentation](docs/ConfigPropertyRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.CredentialRepresentation](docs/CredentialRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.FederatedIdentityRepresentation](docs/FederatedIdentityRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.GlobalRequestResult](docs/GlobalRequestResult.md)
         - [fi.metatavu.keycloak.adminclient.models.GroupRepresentation](docs/GroupRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.IDToken](docs/IDToken.md)
         - [fi.metatavu.keycloak.adminclient.models.IdentityProviderMapperRepresentation](docs/IdentityProviderMapperRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.IdentityProviderRepresentation](docs/IdentityProviderRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.JsonNode](docs/JsonNode.md)
         - [fi.metatavu.keycloak.adminclient.models.KeyStoreConfig](docs/KeyStoreConfig.md)
         - [fi.metatavu.keycloak.adminclient.models.KeysMetadataRepresentation](docs/KeysMetadataRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.KeysMetadataRepresentationMinusKeyMetadataRepresentation](docs/KeysMetadataRepresentationMinusKeyMetadataRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ManagementPermissionReference](docs/ManagementPermissionReference.md)
         - [fi.metatavu.keycloak.adminclient.models.MappingsRepresentation](docs/MappingsRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.MemoryInfoRepresentation](docs/MemoryInfoRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.MultivaluedHashMap](docs/MultivaluedHashMap.md)
         - [fi.metatavu.keycloak.adminclient.models.PartialImportRepresentation](docs/PartialImportRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.PasswordPolicyTypeRepresentation](docs/PasswordPolicyTypeRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.Permission](docs/Permission.md)
         - [fi.metatavu.keycloak.adminclient.models.PolicyRepresentation](docs/PolicyRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ProfileInfoRepresentation](docs/ProfileInfoRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ProtocolMapperRepresentation](docs/ProtocolMapperRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ProviderRepresentation](docs/ProviderRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.RealmEventsConfigRepresentation](docs/RealmEventsConfigRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.RealmRepresentation](docs/RealmRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.RequiredActionProviderRepresentation](docs/RequiredActionProviderRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ResourceRepresentation](docs/ResourceRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ResourceServerRepresentation](docs/ResourceServerRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.RoleRepresentation](docs/RoleRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.RoleRepresentationMinusComposites](docs/RoleRepresentationMinusComposites.md)
         - [fi.metatavu.keycloak.adminclient.models.RolesRepresentation](docs/RolesRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ScopeMappingRepresentation](docs/ScopeMappingRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ScopeRepresentation](docs/ScopeRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.ServerInfoRepresentation](docs/ServerInfoRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.SpiInfoRepresentation](docs/SpiInfoRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.SynchronizationResult](docs/SynchronizationResult.md)
         - [fi.metatavu.keycloak.adminclient.models.SystemInfoRepresentation](docs/SystemInfoRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.TestLdapConnectionRepresentation](docs/TestLdapConnectionRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.UserConsentRepresentation](docs/UserConsentRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.UserFederationMapperRepresentation](docs/UserFederationMapperRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.UserFederationProviderRepresentation](docs/UserFederationProviderRepresentation.md)
         - [fi.metatavu.keycloak.adminclient.models.UserRepresentation](docs/UserRepresentation.md)
        

<a name="documentation-for-authorization"></a>
## Documentation for Authorization

    <a name="access_token"></a>
    ### access_token

        - **Type**: HTTP basic authentication
    
