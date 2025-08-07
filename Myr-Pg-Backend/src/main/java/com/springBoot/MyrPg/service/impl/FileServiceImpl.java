package com.springBoot.MyrPg.service.impl;

import com.springBoot.MyrPg.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final Path uploadsDir;


    @Override
    public Resource loadFile(String filename) throws IOException {
        Path filePath = uploadsDir.resolve(filename).normalize(); //get aadhar card if not valid if block will execute

        if (!filePath.startsWith(uploadsDir)) {
            throw new IllegalArgumentException("Invalid file path.");
        }

        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists()) {
            throw new IOException("File not found: " + filename);
        }

        return resource;
    }
}
