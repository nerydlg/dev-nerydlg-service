package dev.nerydlg.service;

import dev.nerydlg.dto.BlogPost;
import dev.nerydlg.entity.NBlog;
import dev.nerydlg.entity.NUser;
import dev.nerydlg.mapper.BlogMapper;
import dev.nerydlg.repository.NBlogRepository;
import dev.nerydlg.repository.NUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogMapper blogMapper;
    private final NBlogRepository nBlogRepository;
    private final NUserRepository nUserRepository;


    public BlogPost saveBlogPost(BlogPost blogPost) {
        NUser user = nUserRepository.findByUsername(blogPost.author());
        NBlog blog = getNBlog(blogPost);
        blog.setUser(user);
        blog.setDomain(user.getDomain());
        NBlog saved = nBlogRepository.save(blog);
        return getBlogPost(saved);
    }

    public List<BlogPost> findAll(String hostHeader, String lang) {
        String domain = hostHeader.contains(":") ?
                hostHeader.substring(0, hostHeader.indexOf(":"))
                : hostHeader;

        return blogMapper.ListToBlogPostList(nBlogRepository.findByDomain_NameAndLangCode(domain, lang));
    }

    public BlogPost getBlogPost(NBlog nBlog) {
        return blogMapper.nBlogToBlogPost(nBlog);
    }

    public NBlog getNBlog(BlogPost blogPost) {
        return blogMapper.blogPostToNBlog(blogPost);
    }
}
