package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount, Long> {
}
