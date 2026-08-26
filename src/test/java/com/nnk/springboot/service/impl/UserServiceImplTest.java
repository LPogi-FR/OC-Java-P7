package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.mapper.UserMapper;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserMapper mapper;
    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private PasswordEncoder passwordEncoder;



    @Test
    void itShouldFindAll() {
        //GIVEN
        User entity = new User();
        List<User> entityList = List.of(entity);
        UserDto dto = new UserDto();
        List<UserDto> dtoList = List.of(dto);
        when(repository.findAll()).thenReturn(entityList);
        when(mapper.toDtoList(entityList)).thenReturn(dtoList);
        //WHEN
        List<UserDto> result = service.findAll();
        //THEN
        verify(repository).findAll();
        verify(mapper).toDtoList(entityList);
        assertThat(result).isEqualTo(dtoList);
    }

    @Test
    void itShouldSave() {
        //GIVEN
        User entity = new User();
        UserDto dto = new UserDto();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        //WHEN
        UserDto result = service.save(dto);
        //THEN
        verify(repository).save(entity);
        verify(mapper).toEntity(dto);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void itShouldDelete() {
        //GIVEN
        //WHEN
        service.delete(1);
        //THEN
        verify(repository).deleteById(1);
    }

    @Test
    void itShouldUpdate() {
        //GIVEN
        User entity = new User();
        UserDto dto = new UserDto();
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toEntity(dto)).thenReturn(entity);
        //WHEN
        UserDto result = service.update(1,dto);
        //THEN
        verify(repository).save(entity);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void itShouldFindById() {
        //GIVEN
        User entity = new User();
        UserDto dto = new UserDto();
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);
        //WHEN
        UserDto result = service.findById(1);
        //THEN
        verify(repository).findById(1);
        verify(mapper).toDto(entity);
        assertThat(result).isEqualTo(dto);
    }
}