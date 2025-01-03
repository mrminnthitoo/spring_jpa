package com.minnthitoo.spring_jpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getHash(String plainPassword){
        return this.passwordEncoder.encode(plainPassword);
    }
}
