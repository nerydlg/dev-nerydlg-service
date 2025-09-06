package dev.nerydlg.dto;

public record Tag(String id, String name) {
    public Tag {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tag name cannot be null or blank");
        }
    }
}
