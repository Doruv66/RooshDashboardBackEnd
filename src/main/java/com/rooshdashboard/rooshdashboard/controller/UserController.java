package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.IAccount.*;
import com.rooshdashboard.rooshdashboard.domain.User.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class UserController {
    private final GetAccountsUseCase getAccountsUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    @GetMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<User> getAccount(@PathVariable(value = "id") final long id) {
        final Optional<User> accountOptional = getAccountUseCase.getAccount(id);
        if (accountOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(accountOptional.get());
    }

    @GetMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetUserResponse> getAccount() {
        return ResponseEntity.ok(getAccountsUseCase.getAccounts());
    }

    @PostMapping()
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<CreateUserResponse> createAccount(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createAccountUseCase.CreateAccounts(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{accountId}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<DeleteUserResponse> deleteHouse(@PathVariable int accountId) {

        Optional<User> OptionalAccountExists = getAccountUseCase.getAccount(accountId);
        if (OptionalAccountExists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deleteAccountUseCase.deleteAccount(accountId));
    }

    @PutMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UpdateUserResponse> updateAccount(@RequestBody @Valid UpdateUserRequest request) {

        UpdateUserResponse response = updateAccountUseCase.updateAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
