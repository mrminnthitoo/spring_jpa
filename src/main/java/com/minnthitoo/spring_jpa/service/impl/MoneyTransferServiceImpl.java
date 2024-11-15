package com.minnthitoo.spring_jpa.service.impl;

import com.minnthitoo.spring_jpa.dao.BankAccountDao;
import com.minnthitoo.spring_jpa.model.dto.FinancialException;
import com.minnthitoo.spring_jpa.model.entity.BankAccount;
import com.minnthitoo.spring_jpa.service.MoneyTransferService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    @Autowired
    private BankAccountDao bankAccountDao;

    @Transactional(rollbackOn = {FinancialException.class})
    @Override
    public void sendMoney(Long fromAccount, Long toAccount, Double amount) throws FinancialException, Exception{
            this.debit(fromAccount, amount);
            this.credit(toAccount, amount);

    }

    private void debit(Long fromAccount, Double amount) throws FinancialException, Exception {
        Optional<BankAccount> result = this.bankAccountDao.findById(fromAccount);
        if (result.isPresent()){
            BankAccount account = result.get();
            if (account.getBalance() >= amount){
                account.setBalance(account.getBalance() - amount);
                this.bankAccountDao.save(account);
            }else{
                throw new FinancialException("Invalid debit balance.");
            }

        }else {
            throw new Exception("Invalid account");
        }
    }

    private void credit(Long toAccount, Double amount) throws FinancialException, Exception {
        Optional<BankAccount> result = this.bankAccountDao.findById(toAccount);
        if (result.isPresent()){
            BankAccount bankAccount = result.get();
            if (amount > 0){
                bankAccount.setBalance(bankAccount.getBalance() + amount);
                this.bankAccountDao.save(bankAccount);
            }else{
                throw new FinancialException("Invalid credit amount.");
            }

        }else {
            throw new Exception("Invalid Account");
        }
    }
}
