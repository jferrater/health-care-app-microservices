#!/bin/bash

cd medical-records-service && ./gradlew clean build && cd ..
cd patient-service && ./gradlew clean build && cd ..