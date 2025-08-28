package dev.nerydlg.controller;

import dev.nerydlg.dto.AuthRequest;
import dev.nerydlg.dto.JwtResponse;
import dev.nerydlg.service.JwtService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public JwtResponse login(@RequestBody AuthRequest authRequest) {
        logger.info("Logging request: {}", authRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            return new JwtResponse(authentication.getName(), jwtService.GenerateToken(authRequest.username()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
