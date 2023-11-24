package com.danielwaiguru.s3imageupload;

import com.danielwaiguru.s3imageupload.fileUpload.config.AwsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AwsProperties.class)
@SpringBootApplication
public class S3ImageUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(S3ImageUploadApplication.class, args);
    }

}
