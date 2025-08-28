package dev.nerydlg.dto;

import java.time.LocalDateTime;
import java.util.List;


public record BlogPost(String title,
                       String content,
                       String author,
                       String lang,
                       LocalDateTime publicationDate,
                       LocalDateTime updatedAt,
                       List<Tag> tags) {

}
