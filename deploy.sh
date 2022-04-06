#!/bin/bash
./gradlew buildZip

aws cloudformation package --template-file template.yml \
                           --s3-bucket $DEPLOYMENT_BUCKET \
                           --output-template-file out.yml

aws cloudformation deploy --template-file out.yml \
                          --stack-name lambda-demo \
                          --capabilities CAPABILITY_IAM
