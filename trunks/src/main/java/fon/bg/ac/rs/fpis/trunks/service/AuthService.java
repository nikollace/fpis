package fon.bg.ac.rs.fpis.trunks.service;

import fon.bg.ac.rs.fpis.trunks.configuration.ClientConfiguration;
import fon.bg.ac.rs.fpis.trunks.exceptions.TokenVerificationFailedException;
import fon.bg.ac.rs.fpis.trunks.model.GoogleResponse;
import fon.bg.ac.rs.fpis.trunks.model.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private String uri;
    private WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public AuthService(ClientConfiguration configuration) {
        this.uri = configuration.getApiUrl();
        logger.info("Request API: " + this.uri);
        this.webClient = WebClient.create(uri);
    }

    private Map<String, Object> populateClaims(GoogleResponse response) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", response.getEmail());
        claims.put("picture", response.getPicture());
        return claims;
    }

    private Jwt createJWT(GoogleResponse response) {

        String token = Jwts.builder()
                .setSubject(response.getName())
                .addClaims(populateClaims(response))
                .setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(30)))
                .signWith(SignatureAlgorithm.HS256, "secure")
                .compact();

        Jwt jwt = new Jwt();
        jwt.setValue(token);
        return jwt;
    }

    public Jwt authenticate(String idTokenString) {
        logger.info("Calling google api...");
        GoogleResponse responseFromGoogle;
        try {
            responseFromGoogle = webClient.get().uri(
                            uriBuilder -> uriBuilder
                                    .path("/tokeninfo")
                                    .queryParam("id_token", idTokenString).build())
                    .retrieve().bodyToMono(GoogleResponse.class).block();
            logger.info("GoogleResponse: {}", responseFromGoogle);
        } catch (Exception e) {
            throw new TokenVerificationFailedException("Invalid token.");
        }

        return createJWT(responseFromGoogle);
    }
}
