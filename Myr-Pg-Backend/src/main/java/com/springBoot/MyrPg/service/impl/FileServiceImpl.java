package com.springBoot.MyrPg.service.impl;

import com.springBoot.MyrPg.service.FileService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.net.URI;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private S3Presigner s3Presigner;

    @Value("${wasabi.bucket-name}")
    private String bucketName;

    @Value("${wasabi.access-key}")
    private String accessKey;

    @Value("${wasabi.secret-key}")
    private String secretKey;

    @Value("${wasabi.region}")
    private String region;

    @Value("${wasabi.endpoint}")
    private String endpoint;

    @PostConstruct
    public void init() {
        System.out.println("Wasabi Access Key: " + accessKey);
        System.out.println("Wasabi Secret Key: " + (secretKey != null ? "Loaded" : "NULL"));

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        s3Presigner = S3Presigner.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    @Override
    public Resource loadFile(String filename) {
        throw new UnsupportedOperationException("Local file loading not supported. Use generatePresignedUrl instead.");
    }

    @Override
    public String generatePresignedUrl(String filename) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
        return presignedRequest.url().toString();
    }
}