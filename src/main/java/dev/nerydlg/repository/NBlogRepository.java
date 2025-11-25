package dev.nerydlg.repository;

import dev.nerydlg.entity.NBlog;
import dev.nerydlg.entity.NDomain;
import dev.nerydlg.entity.NUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NBlogRepository extends JpaRepository<NBlog, Long> {
    NBlog save(NBlog nBlog);
    List<NBlog> findByUser(NUser user);
    List<NBlog> findByDomain_NameAndLangCode(String domainName, String langCode);
    NBlog findByTitle(String title);
    @Query("SELECT b FROM NBlog b WHERE b.domain.name = :domainName AND LOWER(b.langCode) = LOWER(:langCode) AND b.publicationDate < CURRENT_TIMESTAMP ORDER BY b.publicationDate DESC")
    List<NBlog> findTopPublishedByDomainNameAndLang(String domainName, String langCode, Integer limit, Pageable pageable);

    // NBlog findById(Long id);
}