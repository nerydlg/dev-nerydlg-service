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
import org.springframework.stereotype.Service;

import java.util.List;

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
