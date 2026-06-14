package dev.nerydlg.dto;

public record Contact(String name,
                      String email,
                      String message,
                      String domain,
                      Integer status,
                      String createdAt,
                      String honeypot) {
    public Contact {
        if(honeypot != null && !honeypot.isBlank()) {
            throw new IllegalArgumentException("Honeypot cannot be filled - possible spam");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
        if (domain == null || domain.isBlank()) {
            throw new IllegalArgumentException("Domain cannot be blank");
        }
    }
}
