package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.model.ImgBBRes;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;

@Service
public class FileUploadService {

    private static String BASE_URL = "https://api.imgbb.com/1/upload?key=a6046a56f8b4c887f33b44d99cb0d7a0";

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Please select a file to upload.");
        }

        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("image", Base64.getEncoder().encode(file.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ImgBBRes> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, requestEntity, ImgBBRes.class);

        return response.getBody().getData().getDisplay_url();

//        String fileName = UUID.randomUUID().toString();
//        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
//        Path path = Paths.get(UPLOAD_DIRECTORY + fileName + "." + ext);
//        String fullFileName = fileName + "." + ext;
//
//        if (ext == null) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid file format.");
//        }
//
//        if (!(ext.equals("jpg") || ext.equals("png"))) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only images with extension .jpg or .png are allowed.");
//        }
//
//        try {
//            byte[] bytes = file.getBytes();
//            Files.write(path, bytes);
//            return fullFileName;
//        } catch (IOException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "File upload failed.");
//        }
    }
}
