package com.minnthitoo.spring_jpa.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "{required.user.username}")
    private String username;

    @NotBlank(message = "{required.user.password}")
    private String password;

}
