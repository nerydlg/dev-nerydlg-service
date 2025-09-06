package dev.nerydlg.repository;

import dev.nerydlg.entity.NTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NTagRepository extends JpaRepository<NTag, Long> {
    NTag save(NTag nTag);

    List<NTag> findByNameStartingWith(String name);

    void deleteById(Long aLong);
}