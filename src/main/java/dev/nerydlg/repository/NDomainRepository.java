package dev.nerydlg.repository;

import dev.nerydlg.entity.NDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NDomainRepository extends JpaRepository<NDomain, Long> {
    NDomain save(NDomain nDomain);
    NDomain findByName(String name);
    NDomain findById(long id);
}