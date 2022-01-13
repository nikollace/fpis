package fon.bg.ac.rs.fpis.trunks.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ClientConfiguration {

    @Value("${apis.google-api-url}")
    private String apiUrl;

    public String getApiUrl() { return apiUrl; }
}
