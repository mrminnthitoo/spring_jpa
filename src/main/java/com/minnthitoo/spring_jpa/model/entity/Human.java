package com.minnthitoo.spring_jpa.model.entity;

import com.minnthitoo.spring_jpa.model.entity.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Formula;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@MappedSuperclass
public class Human extends BaseEntity{
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Formula("CONCAT(first_name, \" \", last_name)")
    private String fullName;

    @Column
    private Date birthday;

    @Formula("TIMESTAMPDIFF(YEAR, birthday, CURDATE())")
    private Integer age;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

}
