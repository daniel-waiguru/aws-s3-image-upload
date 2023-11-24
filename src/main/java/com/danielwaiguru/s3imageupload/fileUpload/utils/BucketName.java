package com.danielwaiguru.s3imageupload.fileUpload.utils;

public enum BucketName {
    PROFILE_IMAGE("spring-image-uploads");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
