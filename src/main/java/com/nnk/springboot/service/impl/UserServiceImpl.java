package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.mapper.UserMapper;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private  final UserMapper mapper;
    private final UserRepository repository;


    /**
     * Find all user in database
     * @return List<UserDto>
     */
    @Override
    public List<UserDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    /**
     * Save Bid in database
     * @param userDto UserDto
     * @return List<UserDto>
     */
    @Override
    public UserDto save(UserDto userDto) {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        repository.save(mapper.toEntity(userDto));
        return userDto;
    }

    /**
     * Delete user in database
     * @param id Integer
     */
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    /**
     * Update existing user in database
     * @param id Integer
     * @param userDto UserDto
     * @return UserDto
     */
    @Override
    public UserDto update(Integer id, UserDto userDto) {
        User userToUpdate = mapper.toEntity(userDto);
        userToUpdate.setId(id);
        userToUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
        repository.save(userToUpdate);
        return userDto;
    }

    /**
     * Find user with specific id in database
     * @param id Integer
     * @return UserDto
     */
    @Override
    public UserDto findById(Integer id) {
        Optional<User> optionnalEntity = repository.findById(id);
        if (optionnalEntity.isPresent()) {
            return mapper.toDto(optionnalEntity.get());
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

}
