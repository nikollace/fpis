package fon.bg.ac.rs.fpis.trunks.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type", "Origin")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("POST", "DELETE", "GET", "PATCH")
                .allowCredentials(false)
                .maxAge(3600);
    }
}

