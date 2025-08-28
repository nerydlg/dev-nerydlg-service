package dev.nerydlg.repository;

import dev.nerydlg.entity.NBlog;
import dev.nerydlg.entity.NBlogTags;
import dev.nerydlg.entity.NTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NBlogTagsRepository extends JpaRepository<NBlogTags, Long> {
    NBlogTags save(NBlogTags nBlogTags);
    List<NBlogTags> findByBlog(NBlog blog);
    List<NBlogTags> findByTag(NTag tag);
}