package dev.nerydlg.mapper;

import dev.nerydlg.dto.Tag;
import dev.nerydlg.entity.NTag;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    Tag nTagToTag(dev.nerydlg.entity.NTag nTag);

    @InheritInverseConfiguration
    NTag tagToNTag(Tag tag);

    List<Tag> ListToTagList(List<NTag> nTags);
}
