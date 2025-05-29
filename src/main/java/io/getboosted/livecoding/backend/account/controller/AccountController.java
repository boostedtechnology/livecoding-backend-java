package io.getboosted.livecoding.backend.account.controller;

import io.getboosted.livecoding.backend.account.dto.AccountDTO;
import io.getboosted.livecoding.backend.account.dto.CreateAccountDTO;
import io.getboosted.livecoding.backend.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody CreateAccountDTO createAccountDTO) {
        AccountDTO createdAccount = accountService.createAccount(createAccountDTO);
        return ResponseEntity.ok(createdAccount);
    }
}
