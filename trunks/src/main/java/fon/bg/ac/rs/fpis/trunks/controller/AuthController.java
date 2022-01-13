package fon.bg.ac.rs.fpis.trunks.controller;

import fon.bg.ac.rs.fpis.trunks.model.GoogleResponse;
import fon.bg.ac.rs.fpis.trunks.model.Jwt;
import fon.bg.ac.rs.fpis.trunks.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public Jwt authenticate(@RequestParam(name = "id_token") String idTokenString) {
        logger.info("Authenticating id_token...");
        Jwt jwt = authService.authenticate(idTokenString);
        logger.info("Token: {}", jwt);
        return jwt;
    }
}
