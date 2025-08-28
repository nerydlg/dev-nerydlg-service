package dev.nerydlg.dto;

public record JwtResponse (String username, String token) {
    public JwtResponse {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Token cannot be null or blank");
        }
    }
}