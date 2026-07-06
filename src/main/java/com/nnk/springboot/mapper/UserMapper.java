package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "srping")
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);

    List<User> toEntity(List<UserDto> userDtoList);
    List<UserDto> toDto(List<User> userList);
}
