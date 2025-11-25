package dev.nerydlg.controller;

import dev.nerydlg.dto.BlogPost;
import dev.nerydlg.service.PublicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    private final PublicService publicService;

    @GetMapping("/latestPost/{lang}")
    public List<BlogPost> getLatestPost(@PathVariable String lang,
                                        @RequestParam String site)  {
        return publicService.getMostRecentPosts(lang, site);
    }

    @GetMapping("/blog/{title}")
    public BlogPost getBlog(@PathVariable String title) {
        return publicService.getPostByTitle(title);
    }



}
