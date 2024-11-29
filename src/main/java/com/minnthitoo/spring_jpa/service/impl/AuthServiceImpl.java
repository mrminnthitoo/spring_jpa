package com.minnthitoo.spring_jpa.service.impl;

import com.minnthitoo.spring_jpa.model.dto.UserDto;
import com.minnthitoo.spring_jpa.model.entity.Role;
import com.minnthitoo.spring_jpa.model.entity.User;
import com.minnthitoo.spring_jpa.repository.UserRepository;
import com.minnthitoo.spring_jpa.security.JwtService;
import com.minnthitoo.spring_jpa.security.SecurityUtil;
import com.minnthitoo.spring_jpa.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Transactional
    @Override
    public void register(UserDto userDto) throws Exception {

        User existingUser = this.userRepository.findByUsername(userDto.getUsername());
        if (existingUser != null){
            throw new Exception("User Already Exist");
        }else {
            User userToRegister = new User();
            userToRegister.setUsername(userDto.getUsername());
            userToRegister.setPassword(this.securityUtil.getHash(userDto.getPassword()));

            Role role1 = new Role();
            role1.setRole("ROLE_USER");

            role1.setUser(userToRegister);
            userToRegister.getRoles().add(role1);

            this.userRepository.save(userToRegister);

            userDto.setPassword(userToRegister.getPassword());

        }

    }

    @Override
    public String login(UserDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        User user = this.userRepository.findByUsername(userDto.getUsername());
        return jwtService.generateToken(user);
    }
}
