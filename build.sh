#!/bin/bash

echo "Building resource-models"
cd resource-models && ./gradlew clean build publishToMavenLocal && cd ..
echo "Building medical-records-service"
cd medical-records-service && ./gradlew clean build && cd ..
echo "Building patient-service"
cd patient-service && ./gradlew clean build && cd ..
cd health-care-app && ./gradlew clean build && cd ..