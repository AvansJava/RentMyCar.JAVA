package com.rentmycar.rentmycar.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {
    private static String UPLOAD_DIRECTORY = "E://storage//";

    public String uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Please select a file to upload.");
        }

        String fileName = UUID.randomUUID().toString();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIRECTORY + fileName + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "File upload failed.");
        }
    }
}
