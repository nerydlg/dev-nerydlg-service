package dev.nerydlg.mapper;

import dev.nerydlg.dto.Contact;
import dev.nerydlg.entity.NContact;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public  interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    @Mapping(target = "message", source = "comment")
    @Mapping(target = "domain", source = "domain.name")
    @Mapping(target = "honeypot", ignore = true)
    Contact NContactToContact(NContact nContact);

    @InheritInverseConfiguration(name="NContactToContact")
    @Mappings({
            @Mapping(target = "createdAt", source = "createdAt",  dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
            ,@Mapping(target = "updatedAt", source = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    })
    NContact ContactToNContact(Contact contact);

    // String -> Timestamp
    default Timestamp map(String timestamp) {
        if(timestamp == null) return null;
        Date date = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date = sdf.parse(timestamp);
        }catch (Exception e) {
            // swallow
        }
        return new Timestamp(date.getTime());
    }

    // Timestamp -> String
    default String map(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toString();
    }
}
