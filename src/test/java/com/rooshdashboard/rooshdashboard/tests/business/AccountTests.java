package com.rooshdashboard.rooshdashboard.tests.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.rooshdashboard.rooshdashboard.business.exception.AccountAlreadyExistsException;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidAccountException;
import com.rooshdashboard.rooshdashboard.business.impl.account.*;
import com.rooshdashboard.rooshdashboard.domain.Account.*;
import com.rooshdashboard.rooshdashboard.persistance.AccountRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.AccountEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.RoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountTests {

    @Mock
    private AccountRepository mockAccountRepository;

    @InjectMocks
    private CreateAccountUseCaseImpl createAccountUseCase;
    @InjectMocks
    private UpdateAccountUseCaseImpl updateAccountUseCase;
    @InjectMocks
    private DeleteAccountUseCaseImpl deleteAccountUseCase;
    @InjectMocks
    private GetAccountUseCaseImpl getAccountUseCase;
    @InjectMocks
    private GetAccountsUseCaseImpl getAccountsUseCase;
    @Test
    public void testCreateAccountWithUniqueEmail() {
        // Happy Flow
        RoleEntity role = RoleEntity.builder().roleName("user").build();

        CreateAccountRequest validRequest = new CreateAccountRequest("John", "john@example.com", "pass123", role);
        AccountEntity savedAccount = AccountEntity.builder().id(1L).name("John").email("john@example.com").password("pass123").role(role).build();

        when(mockAccountRepository.checkIfEmailIsUsed(validRequest.getEmail())).thenReturn(String.valueOf(Optional.empty()));
        when(mockAccountRepository.save(any(AccountEntity.class))).thenReturn(savedAccount);

        // Act
        CreateAccountResponse response = createAccountUseCase.CreateAccounts(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(savedAccount.getId(), response.getId());
        verify(mockAccountRepository).checkIfEmailIsUsed(validRequest.getEmail());
        verify(mockAccountRepository).save(any(AccountEntity.class));
    }

    @Test
    public void testCreateAccountWithDuplicateEmail() {
        // Arrange
        RoleEntity role = RoleEntity.builder().roleName("user").build();

        CreateAccountRequest requestWithDuplicateEmail = CreateAccountRequest.builder()
                .email("jane@example.com")
                .password("pass456")
                .name("Jane")
                .role(role)
                .build();

        when(mockAccountRepository.checkIfEmailIsUsed(requestWithDuplicateEmail.getEmail())).thenReturn("jane@example.com");

        // Act & Assert
        assertThrows(AccountAlreadyExistsException.class, () -> createAccountUseCase.CreateAccounts(requestWithDuplicateEmail));
        verify(mockAccountRepository).checkIfEmailIsUsed(requestWithDuplicateEmail.getEmail());
        verify(mockAccountRepository, never()).save(any(AccountEntity.class));
    }

    @Test
    public void testUpdateAccountWithValidRequest() {
        // Arrange
        RoleEntity role = RoleEntity.builder().roleName("user").build();

        long validAccountId = 1L;
        UpdateAccountRequest validRequest = UpdateAccountRequest.builder()
                .id(validAccountId)
                .email("jane@example.com")
                .password("pass456")
                .name("Jane")
                .role(role)
                .build();        AccountEntity existingAccount = AccountEntity.builder().id(validAccountId).name("John").email("john@example.com").password("pass123").role(role).build();
        when(mockAccountRepository.findById(validAccountId)).thenReturn(Optional.of(existingAccount));

        // Act
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Successfully update account with ID 1", response.getMessage());
        verify(mockAccountRepository).findById(validAccountId);
    }

    @Test
    public void testUpdateAccountWithInvalidId() {
        // Arrange
        RoleEntity role = RoleEntity.builder().roleName("user").build();

        long invalidAccountId = 99L;
        UpdateAccountRequest invalidRequest = new UpdateAccountRequest(invalidAccountId, "Jane", "jane@example.com", "pass456", role);


        // Act & Assert
        assertThrows(InvalidAccountException.class, () -> updateAccountUseCase.updateAccount(invalidRequest));
        verify(mockAccountRepository).findById(invalidAccountId);
    }

    @Test
    public void testDeleteAccountWithValidId() {
        // Happy Flow
        Long validId = 1L;
        when(mockAccountRepository.existsById(validId)).thenReturn(true);

        // Act
        DeleteAccountResponse response = deleteAccountUseCase.deleteAccount(validId);

        // Assert
        assertNotNull(response);
        assertEquals("Successfully deleted account with id 1", response.getMessage());
        verify(mockAccountRepository).existsById(validId);
        verify(mockAccountRepository).deleteById(validId);
    }

    @Test
    public void testDeleteAccountWithInvalidId() {
        // Sad Flow
        Long invalidId = 99L;
        when(mockAccountRepository.existsById(invalidId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidAccountException.class, () -> deleteAccountUseCase.deleteAccount(invalidId));
        verify(mockAccountRepository).existsById(invalidId);
        verify(mockAccountRepository, never()).deleteById(invalidId);
    }

    @Test
    public void testGetAccountByIdWithExistingAccount() {
        // Arrange
        RoleEntity role = RoleEntity.builder().roleName("user").build();

        Long validId = 1L;
        AccountEntity accountEntity = AccountEntity.builder().id(validId).name("John").email("john@example.com").password("pass123").role(role).build();
        when(mockAccountRepository.findById(validId)).thenReturn(Optional.of(accountEntity));

        // Act
        Optional<Account> response = getAccountUseCase.getAccount(validId);

        // Assert
        assertNotNull(response);
        assertEquals(accountEntity.getId(), response.get().getId());
        verify(mockAccountRepository).findById(validId);
    }

    @Test
    public void testGetAccountByIdWithNonExistingAccount() {
        // Sad Flow
        Long nonExistentId = 999L;
        when(mockAccountRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act
        getAccountUseCase.getAccount(nonExistentId);

        // Assert
        verify(mockAccountRepository).findById(nonExistentId);
    }

    @Test
    public void testGetAllAccountsWithEmptyRepository() {
        // Sad Flow
        when(mockAccountRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        GetAccountResponse response = getAccountsUseCase.getAccounts();

        // Assert
        assertNotNull(response);
        assertTrue(response.getAccounts().isEmpty());
    }

    @Test
    public void testGetAllAccountsWithFilledRepository() {
        // Happy Flow
        RoleEntity role = RoleEntity.builder().roleName("user").build();


        List<AccountEntity> accounts = List.of(
                AccountEntity.builder().id(1L).name("John").email("john@example.com").password("pass123").role(role).build(),
                AccountEntity.builder().id(2L).name("Jane").email("jane@example.com").password("pass456").role(role).build()
        );
        when(mockAccountRepository.findAll()).thenReturn(accounts);

        // Act
        GetAccountResponse response = getAccountsUseCase.getAccounts();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.getAccounts().size());
    }}

