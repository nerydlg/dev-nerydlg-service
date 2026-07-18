package dev.nerydlg.repository;

import dev.nerydlg.entity.NBlog;
import dev.nerydlg.entity.NDomain;
import dev.nerydlg.entity.NUser;
import dev.nerydlg.mapper.BlogMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NBlogRepository extends JpaRepository<NBlog, Long> {
    NBlog save(NBlog nBlog);
    List<NBlog> findByUser(NUser user);
    Page<NBlog> findByDomain_NameAndLangCode(Pageable pageable, String domainName, String langCode);
    NBlog findByTitle(String title);
    @Query("SELECT b FROM NBlog b WHERE b.domain.name = :domainName AND LOWER(b.langCode) = LOWER(:langCode) AND b.publicationDate < CURRENT_TIMESTAMP ORDER BY b.publicationDate DESC")
    List<NBlog> findTopPublishedByDomainNameAndLang(String domainName, String langCode, Pageable pageable);

    Page<NBlog> findByDomain_Name(Pageable pageable, String domain);

    // NBlog findById(Long id);
}