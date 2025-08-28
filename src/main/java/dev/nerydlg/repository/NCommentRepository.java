package dev.nerydlg.repository;

import dev.nerydlg.entity.NBlog;
import dev.nerydlg.entity.NComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NCommentRepository extends JpaRepository<NComment, Long> {
    NComment save(NComment nComment);
    List<NComment> findByBlog(NBlog blog);
    List<NComment> findByAuthor(String author);
}