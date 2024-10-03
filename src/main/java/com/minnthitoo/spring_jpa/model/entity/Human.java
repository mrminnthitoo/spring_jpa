package com.minnthitoo.spring_jpa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Formula;

import java.io.Serial;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper=true)
@Data
@MappedSuperclass
public class Human extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    Name name;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Date birthday;


}
