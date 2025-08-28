package dev.nerydlg.repository;

import dev.nerydlg.entity.NBlog;
import dev.nerydlg.entity.NDomain;
import dev.nerydlg.entity.NUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NBlogRepository extends JpaRepository<NBlog, Long> {
    NBlog save(NBlog nBlog);
    List<NBlog> findByUser(NUser user);
    List<NBlog> findByDomain_NameAndLangCode(String domainName, String langCode);
    List<NBlog> findByTitle(String title);
}