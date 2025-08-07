package com.springBoot.MyrPg.admin;

import com.springBoot.MyrPg.entity.User;
import com.springBoot.MyrPg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {
    private final UserRepository userRepository; //findByEmail using able to login
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdminIfNotExists() {
        return args -> {
            String adminEmail = "satya341@gmail.com";
            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = User.builder()
                        .name("Admin")
                        .email(adminEmail)
                        .password(passwordEncoder.encode("satya99"))
                        .role("ROLE_ADMIN")
                        .build();

                userRepository.save(admin);
                System.out.println(" Admin user created.");
            } else {
                System.out.println(" Admin already exists.");
            }
        };
    }


    //@Bean
    //public CommandLineRunner createAdminIfNotExists() {
    //    return args -> {
    //        String adminEmail = "satya341@gmail.com";
    //        if (userRepository.findByEmail(adminEmail).isEmpty()) {
    //            User admin = User.builder()
    //                    .name("Admin")
    //                    .email(adminEmail)
    //                    .password("satya99") //  plain text
    //                    .role("ROLE_ADMIN")
    //                    .build();
    //
    //            userRepository.save(admin);
    //            System.out.println("✅ Admin user created with plain text password.");
    //        } else {
    //            System.out.println("✔️ Admin already exists.");
    //        }
    //    };
    //}
}
