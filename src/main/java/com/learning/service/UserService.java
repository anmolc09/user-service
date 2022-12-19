package com.learning.service;

import com.learning.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public UserDto createUser(UserDto user);
    public UserDto getUser(String email);
    UserDto getUserByUserId(String userId);
    UserDto updateUser(String userId, UserDto user);
    void deleteUser(String userId);
    List<UserDto> getUsers(int page, int limit);
}
