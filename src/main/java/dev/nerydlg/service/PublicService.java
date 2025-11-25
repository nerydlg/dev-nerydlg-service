package dev.nerydlg.service;

import dev.nerydlg.configuration.ControllerConfiguration;
import dev.nerydlg.dto.BlogPost;
import dev.nerydlg.entity.NBlog;
import dev.nerydlg.mapper.BlogMapper;
import dev.nerydlg.repository.NBlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublicService {

    private final NBlogRepository nBlogRepository;
    private final BlogMapper blogMapper;
    private final Integer MAX_LATEST_POSTS = 5;

    public List<BlogPost> getMostRecentPosts(String lang, String hostHeader) {
        Pageable pageable = PageRequest.of(0, MAX_LATEST_POSTS);
        List<NBlog> posts = nBlogRepository.findTopPublishedByDomainNameAndLang(hostHeader, lang, MAX_LATEST_POSTS, pageable);
        return blogMapper.ListToBlogPostList(posts);
    }

    public BlogPost getPostByTitle(String title) {
        NBlog nBlog = nBlogRepository.findByTitle(title);
        return blogMapper.nBlogToBlogPost(nBlog);
    }


}
