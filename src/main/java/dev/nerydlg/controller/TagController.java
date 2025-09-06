package dev.nerydlg.controller;

import dev.nerydlg.dto.Tag;
import dev.nerydlg.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/tag")
public class TagController {

    private final TagService tagService;

    @GetMapping("/{name}")
    public List<Tag> findTags(@PathVariable String name){
        return tagService.findTags(name);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id){
        tagService.delete(id);
    }

    @GetMapping("/")
    public List<Tag> findAllTags(){
        return tagService.findAll();
    }

    @PostMapping("/save")
    public Tag saveTag(@RequestBody Tag tag){
        return tagService.saveOrUpdate(tag);
    }

    @PutMapping("/save")
    public Tag updateTag(@RequestBody Tag tag){
        return tagService.saveOrUpdate(tag);
    }
}
