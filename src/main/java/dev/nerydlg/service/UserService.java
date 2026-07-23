package dev.nerydlg.service;

import dev.nerydlg.dto.User;
import dev.nerydlg.entity.NUser;
import dev.nerydlg.mapper.UserMapper;
import dev.nerydlg.repository.NUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final NUserRepository userRepository;
  private final UserMapper userMapper;


  public Page<User> findAll(Pageable pageable) {
    Page<User> users = userRepository.findAll(pageable)
        .map(this::map);
    return users;
  }

  private User  map(final NUser user) {
    return userMapper.nUserToUser(user);
  }
}
