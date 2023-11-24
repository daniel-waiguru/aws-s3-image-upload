package com.danielwaiguru.s3imageupload.fileUpload.controller;

import com.danielwaiguru.s3imageupload.fileUpload.service.FileUploadService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/uploads")
public class FileUploadController {
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping(
            path = "{referenceId}/{file}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadFile(@PathVariable("referenceId") UUID referenceId, @RequestParam("file") MultipartFile file) {
        fileUploadService.uploadFile(referenceId, file);
    }
}
