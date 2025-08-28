package dev.nerydlg.service;

import dev.nerydlg.dto.Tag;
import dev.nerydlg.entity.NTag;
import dev.nerydlg.mapper.TagMapper;
import dev.nerydlg.repository.NTagRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagMapper tagMapper;
    private final NTagRepository nTagRepository;

    public Tag saveTag(Tag tag) {
        NTag saved = nTagRepository.save(getNtag(tag));
        return getTag(saved);
    }

    public List<Tag> findTags(Tag tag){
        return tagMapper.ListToTagList(nTagRepository.findByNameStartingWith(tag.name()));
    }

    private Tag getTag(NTag nTag) {
        return tagMapper.nTagToTag(nTag);
    }

    private NTag getNtag(Tag tag) {
        return tagMapper.tagToNTag(tag);
    }

}
