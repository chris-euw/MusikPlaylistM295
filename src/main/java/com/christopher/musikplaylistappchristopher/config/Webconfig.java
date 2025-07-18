package com.christopher.musikplaylistappchristopher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Konfigurationsklasse für Spring MVC.
 *
 * Diese Klasse wird verwendet, um globale Web-Konfigurationen wie CORS (Cross-Origin Resource Sharing)
 * festzulegen. Sie wird mit @Configuration markiert, damit Spring sie automatisch erkennt.
 */
@Configuration
public class Webconfig {

    /**
     * Definiert eine Bean vom Typ WebMvcConfigurer, die verwendet wird, um benutzerdefinierte Web-Konfigurationen
     * zu registrieren. In diesem Fall wird eine CORS-Konfiguration hinzugefügt.
     *
     * @return ein WebMvcConfigurer mit definierter CORS-Konfiguration
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * Konfiguriert die CORS-Regeln für alle Endpunkte, die mit /api/ beginnen.
             *
             * - `allowedOrigins`: Legt fest, welche Ursprünge Anfragen stellen dürfen (z. B. das Frontend auf Port 5173).
             * - `allowedMethods`: Gibt an, welche HTTP-Methoden erlaubt sind.
             * - `allowedHeaders`: Erlaubt alle HTTP-Header in eingehenden Anfragen.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")                        // Gilt für alle Routen, die mit /api/ beginnen
                        .allowedOrigins("http://localhost:5173")     // Nur Anfragen vom lokalen Frontend zulassen
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Zulässige HTTP-Methoden
                        .allowedHeaders("*");                         // Alle Header sind erlaubt
            }
        };
    }
}
