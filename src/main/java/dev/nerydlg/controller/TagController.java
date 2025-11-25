package dev.nerydlg.controller;

import dev.nerydlg.dto.Tag;
import dev.nerydlg.service.TagService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/tag")
public class TagController {

    private final TagService tagService;

    @GetMapping("/findTag")
    public Page<Tag> findTags(@RequestParam(defaultValue = "") String name) {
        Pageable pageable = PageRequest.of(0, 30);
        return tagService.findTags(name, pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.delete(id);
    }

    @GetMapping("/")
    public Page<Tag> findAllTags(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return tagService.findAll(pageable);
    }

    @PostMapping("/save")
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.saveOrUpdate(tag);
    }

    @PutMapping("/save")
    public Tag updateTag(@RequestBody Tag tag) {
        return tagService.saveOrUpdate(tag);
    }
}
