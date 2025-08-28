package dev.nerydlg.dto;

public record AuthRequest (String username, String password){

    public AuthRequest {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }

    // Optional: avoid printing sensitive data
    @Override
    public String toString() {
        return "AuthRequest[username=%s]".formatted(username);
    }

}