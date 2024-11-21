package com.minnthitoo.spring_jpa.service;

public interface MoneyTransferService {
    void sendMoney(Long fromAccount, Long toAccount, Double amount) throws Exception;
}
