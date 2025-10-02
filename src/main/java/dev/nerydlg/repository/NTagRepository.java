package dev.nerydlg.repository;

import dev.nerydlg.entity.NTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NTagRepository extends JpaRepository<NTag, Long> {
    NTag save(NTag nTag);

    Page<NTag> findByNameStartingWith(Pageable pageable, String name);

    void deleteById(Long aLong);
}