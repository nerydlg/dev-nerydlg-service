package dev.nerydlg.dto;

import java.time.LocalDateTime;

public record Domain(
    Long id,
    String name,
    String desc,
    LocalDateTime expirationDate
    ) {

}
