package dev.nerydlg.controller;

import dev.nerydlg.dto.BlogPost;
import dev.nerydlg.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping ("/api/v1/blog")
public class BlogPostController {

    private final BlogService blogService;

    @PostMapping("/save")
    public BlogPost saveBlogPost(@RequestBody BlogPost blogPost) {
        return blogService.saveBlogPost(blogPost);
    }

    @GetMapping("/{lang}")
    public List<BlogPost> getPost(@PathVariable String lang,
                                  @RequestHeader(value = "Host") String hostHeader) {
        return blogService.findAll(hostHeader, lang);
    }
}
