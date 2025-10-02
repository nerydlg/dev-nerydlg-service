package dev.nerydlg.configuration;

import dev.nerydlg.controller.BlogPostController;
import dev.nerydlg.controller.TagController;
import dev.nerydlg.service.BlogService;
import dev.nerydlg.service.TagService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@Configuration
public class ControllerConfiguration {

    @Bean
    public BlogPostController blogPostController(BlogService blogService) {
        return new BlogPostController(blogService);
    }

    @Bean
    public TagController tagController(TagService tagService) {
        return new TagController(tagService);
    }
}
