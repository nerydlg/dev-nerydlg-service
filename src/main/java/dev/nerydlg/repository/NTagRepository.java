package dev.nerydlg.repository;

import dev.nerydlg.entity.NTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface NTagRepository extends JpaRepository<NTag, Long> {
    NTag save(NTag nTag);

    @Query("SELECT t FROM NTag t WHERE LOWER(t.name) LIKE LOWER(CONCAT(:name, '%'))")
    Page<NTag> findByNameStartingWithIgnoreCase(Pageable pageable, String name);

    void deleteById(Long aLong);
}