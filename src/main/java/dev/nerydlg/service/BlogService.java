package dev.nerydlg.service;

import dev.nerydlg.dto.BlogPost;
import dev.nerydlg.dto.Tag;
import dev.nerydlg.entity.NBlog;
import dev.nerydlg.entity.NBlogTags;
import dev.nerydlg.entity.NTag;
import dev.nerydlg.entity.NUser;
import dev.nerydlg.mapper.BlogMapper;
import dev.nerydlg.mapper.TagMapper;
import dev.nerydlg.repository.NBlogRepository;
import dev.nerydlg.repository.NBlogTagsRepository;
import dev.nerydlg.repository.NUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogMapper blogMapper;
    private final NBlogRepository nBlogRepository;
    private final NUserRepository nUserRepository;
    private final NBlogTagsRepository nBlogTagsRepository;
    private final TagMapper tagMapper;

    public BlogPost saveBlogPost(BlogPost blogPost) {
        NUser user = nUserRepository.findByUsername(blogPost.author());
        NBlog blog = getNBlog(blogPost);
        blog.setUser(user);
        blog.setDomain(user.getDomain());
        NBlog savedBlog = nBlogRepository.save(blog);

        if(blogPost.tags() != null && !blogPost.tags().isEmpty()) {
            saveBlogPost(savedBlog, blogPost.tags());
        }

        return getBlogPost(savedBlog);
    }

    private void saveBlogPost(NBlog savedBlog, List<Tag> tags) {
        for (Tag tag : tags) {
            NTag ntag = tagMapper.tagToNTag(tag);
            NBlogTags blogTags = new NBlogTags();
            blogTags.setBlog(savedBlog);
            blogTags.setTag(ntag);
            nBlogTagsRepository.save(blogTags);
        }
    }

    public Page<BlogPost> findAll(Pageable pageable, String hostHeader, String lang) {
        String domain = hostHeader.contains(":") ?
                hostHeader.substring(0, hostHeader.indexOf(":"))
                : hostHeader;
        if(lang == null) {
            return nBlogRepository.findByDomain_Name(pageable, domain)
                .map(this::getBlogPost);
        } else {
            return nBlogRepository.findByDomain_NameAndLangCode(pageable, domain, lang)
                .map(this::getBlogPost);
        }
    }

    public BlogPost getBlogPost(NBlog nBlog) {
        return blogMapper.nBlogToBlogPost(nBlog);
    }

    public NBlog getNBlog(BlogPost blogPost) {
        return blogMapper.blogPostToNBlog(blogPost);
    }

  public void deleteBlogPost(Long id) {
    nBlogRepository.deleteById(id);
  }

    public BlogPost getBlogPostById(Long id) {
        Optional<NBlog> byId = nBlogRepository.findById(id);
        if(byId.isPresent()) {
            return blogMapper.nBlogToBlogPost(byId.get());
        }
        return null;
    }
}
