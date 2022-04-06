package com.hft.example;

import com.amazonaws.regions.InMemoryRegionImpl;
import com.amazonaws.regions.Region;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LambdaHandler implements RequestHandler<S3Event, String> {
    @Override
    public String handleRequest(S3Event s3event, Context context) {
        LambdaLogger logger = context.getLogger();
        S3EventNotification.S3EventNotificationRecord record = s3event.getRecords().get(0);
        String bucket = record.getS3().getBucket().getName();
        logger.log("Bucket Event detected: " + bucket);
        String objectKey = record.getS3().getObject().getUrlDecodedKey();
        logger.log("New file has been added: " + objectKey);
        AmazonS3 s3Client = AmazonS3Client.builder()
                .withRegion(record.getAwsRegion())
                .build();
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucket, objectKey));
        InputStream objectData = s3Object.getObjectContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(objectData));
        try {
            String content = reader.readLine();
            if (!content.contains("+")) {
                logger.log("No plus sign detected. Doing nothing...");
            } else {
                int a = Integer.parseInt(content.split("[+]")[0]);
                int b = Integer.parseInt(content.split("[+]")[1]);
                logger.log("Detected numbers are: " + a + " and " + b);
                b += a;
                logger.log("SUM: " + b);
                InputStream is = new ByteArrayInputStream(Integer.toString(b).
                        getBytes(StandardCharsets.UTF_8));
                ObjectMetadata metadata = new ObjectMetadata();
                s3Client.putObject(bucket, objectKey, is, metadata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK.";
    }
}
