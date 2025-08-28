package dev.nerydlg.mapper;

import dev.nerydlg.dto.BlogPost;
import dev.nerydlg.entity.NBlog;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogMapper {

    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    @Mapping(source = "langCode", target = "lang")
    @Mapping(source = "user.username", target = "author")
    BlogPost nBlogToBlogPost(dev.nerydlg.entity.NBlog nBlog);

    @InheritInverseConfiguration(name = "nBlogToBlogPost")
    NBlog blogPostToNBlog(BlogPost blogPost);

    List<BlogPost> ListToBlogPostList(List<NBlog> nBlogs);

    // Instant -> Timestamp
    default Timestamp map(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }

    // Timestamp -> Instant
    default LocalDateTime map(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
