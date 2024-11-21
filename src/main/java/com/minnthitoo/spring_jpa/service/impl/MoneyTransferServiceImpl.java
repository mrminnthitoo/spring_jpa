package com.minnthitoo.spring_jpa.service.impl;

import com.minnthitoo.spring_jpa.model.entity.BankAccount;
import com.minnthitoo.spring_jpa.repository.BankAccountRepository;
import com.minnthitoo.spring_jpa.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public void sendMoney(Long fromAccount, Long toAccount, Double amount) {

        this.debit(fromAccount, amount);
        this.credit(toAccount, amount);

    }

    private void debit(Long fromAccount, Double amount){
        Optional<BankAccount> result = this.bankAccountRepository.findById(fromAccount);
        if (result.isPresent()){
            BankAccount account = result.get();
            account.setBalance(account.getBalance() - amount);
            this.bankAccountRepository.save(account);
        }
    }

    private void credit(Long toAccount, Double amount){
        Optional<BankAccount> result = this.bankAccountRepository.findById(toAccount);
        if (result.isPresent()){
            BankAccount account = result.get();
            account.setBalance(account.getBalance() + amount);
            this.bankAccountRepository.save(account);
        }
    }

}
