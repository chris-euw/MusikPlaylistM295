package com.christopher.musikplaylistappchristopher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Kombiniert @Configuration, @EnableAutoConfiguration und @ComponentScan
public class MusikPlaylistAppChristopherApplication {

    public static void main(String[] args) {
        // Wird von JVM beim Start aufgerufen
        // Implementierung: SpringApplication.run startet den eingebetteten Tomcat und lädt ApplicationContext
        SpringApplication.run(MusikPlaylistAppChristopherApplication.class, args);
    }
}