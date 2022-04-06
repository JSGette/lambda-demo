#AWS Lambda Java Demo
This is a simple demo to show how AWS Lambda Functions works.
This project also AWS CloudFormation to simplify deployment
and Gradle to build and package executable artifact.

Demo has a simple Request Handler that reads all 
added files in S3 bucket and if it detects plus sign **(+)**
it replaces original content with a sum.
### Requirements
* OpenJDK 11
* AWS CLI
* AWS Account
* Existing S3 Bucket

### Installation
This project already contains deployment script (deploy.sh) 
and CloudFormation template (template.yml) that will create 
a Stack automatically including both lambda function and test s3 bucket.
```
DEPLOYMENT_BUCKET=<name_of_existing_s3_bucket> ./deploy.sh
```

### How to use
Afterwards you can either open AWS Console or use awscli to
upload any text file in a proper format:
```
<NUMBER>+<NUMBER>
```

For instance, if you create a file that contains "1+1" it will be
automatically replaced with new content "2"