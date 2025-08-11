package com.springBoot.MyrPg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class UploadPathConfig {


//    @Bean
//    public Path uploadsDir() {
//        return Paths
//                .get("F:/MYR-PG-Registration/Myr-Pg-Backend/uploads/")//download aadhar card from here
//                .toAbsolutePath()
//                .normalize();
//    }

//    @Bean
//    public Path uploadsDir() {
//        return Paths
//                .get("F:/project/PG-Management-System/Myr-Pg-Backend/uploads/")
//                .toAbsolutePath()
//                .normalize();
//    }

    @Bean
    public Path uploadsDir() {
        return Paths
                .get("/app/uploads/")
                .toAbsolutePath()
                .normalize();
    }
}
