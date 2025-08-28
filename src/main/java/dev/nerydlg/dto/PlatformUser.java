package dev.nerydlg.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlatformUser {
    private final String username;
    private final String password;
    private final boolean isBlocked;
    private final boolean isValid;
}
