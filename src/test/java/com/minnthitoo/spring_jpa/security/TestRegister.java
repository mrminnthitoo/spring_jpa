package com.minnthitoo.spring_jpa.security;

import com.minnthitoo.spring_jpa.model.entity.Role;
import com.minnthitoo.spring_jpa.model.entity.User;
import com.minnthitoo.spring_jpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRegister {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityUtil securityUtil;

    @Test
    public void testRegister(){

        User user = new User();
//        user.setUsername("admin");
        user.setUsername("user");
        user.setPassword(securityUtil.getHash("admin"));

        Role role = new Role();
//        role.setRole("ROLE_ADMIN");
        role.setRole("ROLE_USER");

        user.getRoles().add(role);
        role.setUser(user);

        this.userRepository.save(user);

    }

}
