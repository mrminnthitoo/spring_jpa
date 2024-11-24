package com.minnthitoo.spring_jpa.model.dto;

import com.minnthitoo.spring_jpa.model.entity.enums.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class ActorDto {

    private String firstName;
    private String lastName;
    private String fullName;

    private Date birthday;
    private Integer age;
    private Gender gender;

}
