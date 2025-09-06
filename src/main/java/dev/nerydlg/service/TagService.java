package dev.nerydlg.service;

import dev.nerydlg.dto.Tag;
import dev.nerydlg.entity.NTag;
import dev.nerydlg.mapper.TagMapper;
import dev.nerydlg.repository.NTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagMapper tagMapper;
    private final NTagRepository nTagRepository;

    public Tag saveOrUpdate(Tag tag) {
        NTag saved = nTagRepository.save(getNtag(tag));
        return getTag(saved);
    }

    public List<Tag> findTags(String name){
        return tagMapper.ListToTagList(nTagRepository.findByNameStartingWith(name));
    }

    public void delete(Long id){
        nTagRepository.deleteById(id);
    }

    private Tag getTag(NTag nTag) {
        return tagMapper.nTagToTag(nTag);
    }

    private NTag getNtag(Tag tag) {
        return tagMapper.tagToNTag(tag);
    }

    public List<Tag> findAll() {
        return tagMapper.ListToTagList(nTagRepository.findAll());
    }
}
