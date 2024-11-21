package com.minnthitoo.spring_jpa.service.impl;

import com.minnthitoo.spring_jpa.exceptions.FinancialException;
import com.minnthitoo.spring_jpa.model.entity.BankAccount;
import com.minnthitoo.spring_jpa.repository.BankAccountRepository;
import com.minnthitoo.spring_jpa.service.MoneyTransferService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional(rollbackOn = {FinancialException.class})
    @Override
    public void sendMoney(Long fromAccount, Long toAccount, Double amount) throws Exception {

        this.debit(fromAccount, amount);
        this.credit(toAccount, amount);

    }

    private void debit(Long fromAccount, Double amount) throws FinancialException, Exception {
        Optional<BankAccount> result = this.bankAccountRepository.findById(fromAccount);
        if (result.isPresent()){
            BankAccount account = result.get();
            if (account.getBalance() >= amount){
                account.setBalance(account.getBalance() - amount);
                this.bankAccountRepository.save(account);
            }else {
                throw new FinancialException("Invalid debit amount");
            }

        }else {
            throw new Exception("Invalid account.");
        }
    }

    private void credit(Long toAccount, Double amount) throws FinancialException, Exception {
        Optional<BankAccount> result = this.bankAccountRepository.findById(toAccount);
        if (result.isPresent()){
            BankAccount account = result.get();
            if (amount >= 0){
                account.setBalance(account.getBalance() + amount);
                this.bankAccountRepository.save(account);
            }else {
                throw new FinancialException("Invalid credit amount");
            }

        }else {
            throw new Exception("Invalid Account.");
        }
    }

}
