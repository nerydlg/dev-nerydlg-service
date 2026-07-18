package dev.nerydlg.controller;

import dev.nerydlg.dto.BlogPost;
import dev.nerydlg.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping ("/api/v1/blog")
public class BlogPostController {

    private final BlogService blogService;

    @PostMapping("/save")
    @PutMapping("/update")
    public BlogPost createOrUpdateBlogPost(@RequestBody BlogPost blogPost) {
        return blogService.saveBlogPost(blogPost);
    }

    @GetMapping("/{lang}")
    public Page<BlogPost> getPostByLang(@PathVariable String lang,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestHeader(value = "Host") String hostHeader) {
        Pageable pageable = PageRequest.of(page, size);
        return blogService.findAll(pageable, hostHeader, lang);
    }

    @GetMapping
    public Page<BlogPost> getBlogPosts(@RequestHeader(value = "Host") String hostHeader,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return blogService.findAll(pageable, hostHeader, null);
    }

    @GetMapping("/find/{id}")
    public BlogPost getBlogPost(@PathVariable Long id) {
        return blogService.getBlogPostById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable Long id) {
        blogService.deleteBlogPost(id);
    }

}
