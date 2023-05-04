package com.dotseven.poc.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository repository) {
        return agrs -> {
            User u1 = new User(
                    "user",
                    "pass"
            );

            User u2 = new User(
                    "antonio",
                    "antòòòò"
            );

            repository.saveAll(
                    List.of(u1, u2)
            );
        };
    }
}
