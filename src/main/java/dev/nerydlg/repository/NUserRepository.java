package dev.nerydlg.repository;

import dev.nerydlg.entity.NUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NUserRepository extends JpaRepository<NUser, Long> {
    NUser save(NUser nUser);
    NUser findByUsername(String username);
    NUser findById(long id);
}
