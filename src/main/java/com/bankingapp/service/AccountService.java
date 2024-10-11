package com.bankingapp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.model.Account;
import com.bankingapp.model.AccountType;
import com.bankingapp.model.User;
import com.bankingapp.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(User user, String accountNumber, AccountType accountType) {
        Account account = new Account();
        account.setUser(user);
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public List<Account> getAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    public void deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }
}
