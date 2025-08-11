package com.springBoot.MyrPg.controller;

import com.springBoot.MyrPg.DTO.ApiResponseDTO;
import com.springBoot.MyrPg.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/pg/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;


//    // Optional endpoint to get download link as JSON
//    //only returns a JSON response like this:
//    @GetMapping("/file/download-link/{filename:.+}")
//    public ResponseEntity<ApiResponseDTO<String>> getDownloadLink(@PathVariable String filename) {
//        String downloadUrl = "/api/pg/files/" + filename;
//        ApiResponseDTO<String> response = new ApiResponseDTO<>("success", "Download link generated", downloadUrl);
//        return ResponseEntity.ok(response);
//    }





    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Void> redirectToWasabi(@PathVariable String filename) {
        String presignedUrl = fileService.generatePresignedUrl(filename);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(presignedUrl))
                .build();
    }



    // Mandatory endpoint that actually streams the file
//    //used for download link
//    @GetMapping("/files/{filename:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
//        Resource resource = fileService.loadFile(filename);
//
//        return ResponseEntity.ok()
//                .header("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }




}
