package dev.nerydlg.controller;

import dev.nerydlg.dto.User;
import dev.nerydlg.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  @GetMapping
  public Page<User> getUsers(@RequestParam final Integer page, @RequestParam final Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return userService.findAll(pageable);
  }
}
