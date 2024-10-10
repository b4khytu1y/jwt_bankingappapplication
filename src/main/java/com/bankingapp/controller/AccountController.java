package com.bankingapp.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.model.Account;
import com.bankingapp.model.AccountType;
import com.bankingapp.model.User;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.UserService;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(Authentication authentication, @RequestParam String accountNumber, @RequestParam String accountType) {
        User user = userService.findByUsername(authentication.getName());
        Account account = accountService.createAccount(user, accountNumber, AccountType.valueOf(accountType));
        return ResponseEntity.ok(account);
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getUserAccounts(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<Account> accounts = accountService.getAccountsByUserId(user.getId());
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/accounts/{accountId}/deposit")
    public ResponseEntity<?> deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        accountService.deposit(accountId, amount);
        return ResponseEntity.ok("Deposit successful");
    }

    @PostMapping("/accounts/{accountId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountId, amount);
        return ResponseEntity.ok("Withdrawal successful");
    }
}
