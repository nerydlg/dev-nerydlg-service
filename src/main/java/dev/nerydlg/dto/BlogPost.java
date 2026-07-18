package dev.nerydlg.dto;

import java.time.LocalDateTime;
import java.util.List;


public record BlogPost(
    Integer id,
    String title,
    String content,
    String author,
    String summary,
    String lang,
    LocalDateTime publicationDate,
    LocalDateTime updatedAt,
    List<Tag> tags) {

}
