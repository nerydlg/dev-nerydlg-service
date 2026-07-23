package dev.nerydlg.configuration;

import dev.nerydlg.mapper.BlogMapper;
import dev.nerydlg.mapper.ContactMapper;
import dev.nerydlg.mapper.TagMapper;
import dev.nerydlg.mapper.UserMapper;
import dev.nerydlg.repository.NBlogRepository;
import dev.nerydlg.repository.NBlogTagsRepository;
import dev.nerydlg.repository.NContactRepository;
import dev.nerydlg.repository.NDomainRepository;
import dev.nerydlg.repository.NTagRepository;
import dev.nerydlg.repository.NUserRepository;
import dev.nerydlg.service.BlogService;
import dev.nerydlg.service.DomainService;
import dev.nerydlg.service.PublicService;
import dev.nerydlg.service.TagService;
import dev.nerydlg.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

   @Bean
   public BlogService blogService(BlogMapper blogMapper, NBlogRepository nBlogRepository, NUserRepository nUserRepository, NBlogTagsRepository nBlogTagsRepository, TagMapper tagMapper) {
       return new BlogService(blogMapper, nBlogRepository, nUserRepository, nBlogTagsRepository, tagMapper);
   }

   @Bean
   public TagService tagService(TagMapper tagMapper, NTagRepository nTagRepository) {
       return new TagService(tagMapper, nTagRepository);
   }

   @Bean
   public DomainService domainService(NDomainRepository domainRepository) {
       return new DomainService(domainRepository);
   }

   @Bean
   public PublicService publicService(NBlogRepository nBlogRepository,
                                      BlogMapper blogMapper,
                                      NContactRepository nContactRepository,
                                      ContactMapper contactMapper,
                                      NDomainRepository domainRepository) {
       return new PublicService(nBlogRepository, blogMapper, nContactRepository, contactMapper, domainRepository);
   }

   @Bean
   public UserService userService(UserMapper userMapper, NUserRepository  nUserRepository) {
     return new UserService(nUserRepository, userMapper);
   }
}
