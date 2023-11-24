package com.danielwaiguru.s3imageupload.fileUpload.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.danielwaiguru.s3imageupload.fileUpload.utils.BucketName;
import com.danielwaiguru.s3imageupload.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileUploadService {
    private final AmazonS3 s3;
    private final UserRepository userRepository;

    @Autowired
    public FileUploadService(AmazonS3 s3, UserRepository userRepository) {
        this.s3 = s3;
        this.userRepository = userRepository;
    }

    private void save(
            String path,
            String fileName,
            Optional<Map<String, String>> metaData,
            InputStream inputStream
    ) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            metaData.ifPresent(map ->{
                if (!map.isEmpty()) {
                    map.forEach(objectMetadata::addUserMetadata);
                }
            });
            s3.putObject(path, fileName, inputStream, objectMetadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("File upload failed", e);
        }
    }

    public void uploadFile(UUID referenceId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "File is required!");
        }
        userRepository
                .getUsers()
                .stream()
                .filter(user -> user.getId().equals(referenceId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No entity with above id found"));

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        metadata.put("ReferenceId", String.valueOf(referenceId));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), referenceId);
        String filename = String.format("%s-%s", file.getName(), UUID.randomUUID());
        try {
            save(path, filename, Optional.of(metadata), file.getInputStream());
            //Update reference entity with the public file url
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
        }
    }
}
