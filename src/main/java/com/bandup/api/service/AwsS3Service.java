package com.bandup.api.service;

import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

public interface AwsS3Service {
    String getPreSignedPutUrl(String bucketName, String key, String contentType);
    String getPreSignedGetUrl(String bucketName, String key);
}
