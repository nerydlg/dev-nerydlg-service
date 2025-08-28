package dev.nerydlg.controller;

import dev.nerydlg.dto.Tag;
import dev.nerydlg.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/tag")
public class TagController {

    private final TagService tagService;

    @GetMapping("/")
    public List<Tag> findTags(Tag tag){
        return tagService.findTags(tag);
    }

    @PostMapping("/save")
    public Tag saveTag(Tag tag){
        return tagService.saveTag(tag);
    }
}
