package dev.nerydlg.service;

import dev.nerydlg.dto.Tag;
import dev.nerydlg.entity.NTag;
import dev.nerydlg.mapper.TagMapper;
import dev.nerydlg.repository.NTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TagService {
    private final TagMapper tagMapper;
    private final NTagRepository nTagRepository;

    public Tag saveOrUpdate(Tag tag) {
        NTag saved = nTagRepository.save(getNtag(tag));
        return getTag(saved);
    }

    public Page<Tag> findTags(String name, Pageable pageable){
        return nTagRepository.findByNameStartingWithIgnoreCase(pageable, name)
                .map(this::getTag);
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

    public Page<Tag> findAll(Pageable pageable) {
        return nTagRepository.findAll(pageable)
                .map(this::getTag);
    }
}
