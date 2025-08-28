package dev.nerydlg.service;

import dev.nerydlg.dto.PlatformUser;
import dev.nerydlg.entity.NUser;
import dev.nerydlg.repository.NUserRepository;
import dev.nerydlg.security.PlatformUserDetails;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Timestamp;
import java.time.Instant;

@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final NUserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Looking for user with username {}", username);
        NUser user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("No user found with username {}", username);
            throw new UsernameNotFoundException("User not found");
        }
        // convert to platform user
        PlatformUser platformUser = PlatformUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .isBlocked(user.getStatus() == 2)
                .isValid(user.getExpirationDate().after(Timestamp.from(Instant.now())))
                .build();
        logger.debug("Found user with username {}", username);
        return new PlatformUserDetails(platformUser);
    }
}
