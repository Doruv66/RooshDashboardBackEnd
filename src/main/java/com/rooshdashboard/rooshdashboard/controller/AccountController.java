package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.IAccount.*;
import com.rooshdashboard.rooshdashboard.domain.Account.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class AccountController {
    private final GetAccountsUseCase getAccountsUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    @GetMapping("{id}")
    public ResponseEntity<Account> getAccount(@PathVariable(value = "id") final long id) {
        final Optional<Account> accountOptional = getAccountUseCase.getAccount(id);
        if (accountOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(accountOptional.get());
    }

    @GetMapping
    public ResponseEntity<GetAccountResponse> getAccount() {
        return ResponseEntity.ok(getAccountsUseCase.getAccounts());
    }

    @PostMapping()
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request) {
        CreateAccountResponse response = createAccountUseCase.CreateAccounts(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{accountId}")
    public ResponseEntity<DeleteAccountResponse> deleteHouse(@PathVariable int accountId) {

        Optional<Account> OptionalAccountExists = getAccountUseCase.getAccount(accountId);
        if (OptionalAccountExists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deleteAccountUseCase.deleteAccount(accountId));
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateAccountResponse> updateAccount(@RequestBody @Valid UpdateAccountRequest request) {

        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
