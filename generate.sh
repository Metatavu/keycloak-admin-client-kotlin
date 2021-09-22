#!/bin/bash

VERSION="0.0.1"

openapi-generator-cli generate \
  -i https://raw.githubusercontent.com/ccouzens/keycloak-openapi/main/keycloak/14.0.json \
  -g kotlin-vertx \
  --additional-properties artifactVersion=$VERSION,artifactId=keycloak-admin-client-kotlin,groupId=fi.metatavu.keycloak.adminclient,packageName=fi.metatavu.keycloak.adminclient,serializationLibrary=jackson