package com.minnthitoo.spring_jpa.service;

import com.minnthitoo.spring_jpa.model.dto.FinancialException;

public interface MoneyTransferService{
    void sendMoney(Long fromAccount, Long toAccount, Double amount)throws FinancialException, Exception;
}
