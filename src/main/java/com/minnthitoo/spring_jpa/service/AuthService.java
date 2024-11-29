package com.minnthitoo.spring_jpa.service;

import com.minnthitoo.spring_jpa.model.dto.UserDto;

public interface AuthService {
    void register(UserDto userDto) throws Exception;
    String login(UserDto userDto);
}
