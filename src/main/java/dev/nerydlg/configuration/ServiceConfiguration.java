package dev.nerydlg.configuration;

import dev.nerydlg.mapper.BlogMapper;
import dev.nerydlg.mapper.TagMapper;
import dev.nerydlg.repository.NBlogRepository;
import dev.nerydlg.repository.NDomainRepository;
import dev.nerydlg.repository.NTagRepository;
import dev.nerydlg.repository.NUserRepository;
import dev.nerydlg.service.BlogService;
import dev.nerydlg.service.DomainService;
import dev.nerydlg.service.TagService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

   @Bean
   public BlogService blogService(BlogMapper blogMapper, NBlogRepository nBlogRepository, NUserRepository nUserRepository) {
       return new BlogService(blogMapper, nBlogRepository, nUserRepository);
   }

   @Bean
   public TagService tagService(TagMapper tagMapper, NTagRepository nTagRepository) {
       return new TagService(tagMapper, nTagRepository);
   }

   @Bean
   public DomainService domainService(NDomainRepository domainRepository) {
       return new DomainService(domainRepository);
   }
}
