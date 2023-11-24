package com.danielwaiguru.s3imageupload.fileUpload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("aws")
public record AwsProperties(
        String awsAccessKey,
        String awsSecretKey
) { }
