package com.springBoot.MyrPg.service;

import org.springframework.core.io.Resource;
import java.io.IOException;

public interface FileService {
//admin download aadhar card
//    Resource loadFile(String filename) throws IOException;
//
//
//    String generatePresignedUrl(String filename);


    Resource loadFile(String filename) throws IOException;

    String generatePresignedUrl(String filename);
}
