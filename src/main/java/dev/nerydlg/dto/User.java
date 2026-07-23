package dev.nerydlg.dto;

import java.time.LocalDateTime;

public record User(
    Long id,
    String domain,
    String username,
    String password,
    Integer status,
    String email,
    String firstName,
    String lastName,
    LocalDateTime dob,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime expiration_date
) {

  public User(Long id,
              String domain,
              String username,
              String password,
              Integer status,
              String email,
              String firstName,
              String lastName,
              LocalDateTime dob){
    this(id, domain, username, password, status, email, firstName, lastName,
        dob, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.MAX);
  }
}
