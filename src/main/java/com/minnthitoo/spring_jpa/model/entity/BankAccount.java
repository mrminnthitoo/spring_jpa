package com.minnthitoo.spring_jpa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class BankAccount extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column
    private String accountName;

    @Column
    private Double balance;
}
