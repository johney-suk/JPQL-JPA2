package org.zerock.j07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class J07Application {

    public static void main(String[] args) {
        SpringApplication.run(J07Application.class, args);
    }

}
