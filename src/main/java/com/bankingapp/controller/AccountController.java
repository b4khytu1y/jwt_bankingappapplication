package com.bankingapp.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Create a new account", description = "Creates a new account for the authenticated user.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account created successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(Authentication authentication, @RequestParam String accountNumber, @RequestParam String accountType) {
        Optional<User> optionalUser = userService.findByUsername(authentication.getName());
        
        // Проверяем, если пользователь существует
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = optionalUser.get(); // Извлекаем пользователя из Optional
        Account account = accountService.createAccount(user, accountNumber, AccountType.valueOf(accountType));
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Get user accounts", description = "Retrieves all accounts for the authenticated user.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Accounts retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/accounts")
    public ResponseEntity<?> getUserAccounts(Authentication authentication) {
        Optional<User> optionalUser = userService.findByUsername(authentication.getName());
        
        // Проверяем, если пользователь существует
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = optionalUser.get(); // Извлекаем пользователя из Optional
        List<Account> accounts = accountService.getAccountsByUserId(user.getId());
        return ResponseEntity.ok(accounts);
    }

    @Operation(summary = "Deposit money", description = "Deposit money into the account with the given ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deposit successful"),
        @ApiResponse(responseCode = "400", description = "Invalid account or amount")
    })
    @PostMapping("/accounts/{accountId}/deposit")
    public ResponseEntity<?> deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        accountService.deposit(accountId, amount);
        return ResponseEntity.ok("Deposit successful");
    }

    @Operation(summary = "Withdraw money", description = "Withdraw money from the account with the given ID.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Withdrawal successful"),
    @ApiResponse(responseCode = "400", description = "Invalid account or amount")
    })
    @PostMapping("/accounts/{accountId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountId, amount);
        return ResponseEntity.ok("Withdrawal successful");
    }
}
