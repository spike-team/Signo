#!/bin/bash

BUILD_PATH=$(ls /home/ubuntu/build/*.jar)
JAR_NAME=$(basename $BUILD_PATH)
echo "> name of build file: $JAR_NAME"

echo "> copy the build file"
DEPLOY_PATH=/home/ubuntu/
cp $BUILD_PATH $DEPLOY_PATH
