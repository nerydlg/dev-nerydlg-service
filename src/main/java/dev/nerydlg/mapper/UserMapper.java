package dev.nerydlg.mapper;

import dev.nerydlg.dto.User;
import dev.nerydlg.entity.NUser;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(source = "domain.name", target = "domain")
  @Mapping(source = "password", ignore = true, target = "password")
  User nUserToUser(NUser nUser);

  @InheritInverseConfiguration(name="nUserToUser")
  NUser userToNUser(User user);

  default LocalDateTime map(Timestamp timestamp) {
    return timestamp == null ? null : timestamp.toLocalDateTime();
  }

  default Timestamp map(LocalDateTime localDateTime) {
    return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
  }

}
