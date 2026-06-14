package dev.nerydlg.repository;

import dev.nerydlg.entity.NContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NContactRepository extends JpaRepository<NContact, Long> {

    NContact save(NContact nContact);

    Page<NContact> findByDomain_Name(String domainName, Pageable pageable);
}
