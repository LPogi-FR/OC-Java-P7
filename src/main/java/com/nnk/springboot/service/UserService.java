package com.nnk.springboot.service;

import com.nnk.springboot.dto.UserDto;
import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto save(UserDto tradeDto);

    void delete(Integer id);

    UserDto update(Integer id, UserDto tradeDto);

    UserDto findById(Integer id);
}
