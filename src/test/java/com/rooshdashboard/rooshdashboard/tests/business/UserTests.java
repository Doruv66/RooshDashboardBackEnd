package com.rooshdashboard.rooshdashboard.tests.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.rooshdashboard.rooshdashboard.business.exception.AccountAlreadyExistsException;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidAccountException;
import com.rooshdashboard.rooshdashboard.business.impl.account.*;
import com.rooshdashboard.rooshdashboard.domain.User.*;
import com.rooshdashboard.rooshdashboard.persistance.UserRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.RoleEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class UserTests {

    @Mock
    private UserRepository mockAccountRepository;

    @InjectMocks
    private CreateUserUseCaseImpl createAccountUseCase;
    @InjectMocks
    private UpdateUserUseCaseImpl updateAccountUseCase;
    @InjectMocks
    private DeleteUserUseCaseImpl deleteAccountUseCase;
    @InjectMocks
    private GetAccountUseCaseImpl getAccountUseCase;
    @InjectMocks
    private GetUserUseCaseImpl getAccountsUseCase;
    @Test
    public void testCreateAccountWithUniqueEmail() {
        // Happy Flow
        Set<UserRoleEntity> userRoleEntities = new HashSet<>();

        CreateUserRequest validRequest = new CreateUserRequest("John", "pass123", userRoleEntities);
        UserEntity savedAccount = UserEntity.builder().id(1L).username("John").password("pass123").userRoles(userRoleEntities).build();
        when(mockAccountRepository.checkIfNameIsUsed(validRequest.getName())).thenReturn(String.valueOf(Optional.empty()));
        when(mockAccountRepository.save(any(UserEntity.class))).thenReturn(savedAccount);

        // Act
        CreateUserResponse response = createAccountUseCase.CreateAccounts(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(savedAccount.getId(), response.getId());
        verify(mockAccountRepository).checkIfNameIsUsed(validRequest.getName());
        verify(mockAccountRepository).save(any(UserEntity.class));
    }

    @Test
    public void testCreateAccountWithDuplicateEmail() {
        // Arrange
        Set<UserRoleEntity> userRoleEntities = new HashSet<>();

        CreateUserRequest requestWithDuplicateName = CreateUserRequest.builder()
                .password("pass456")
                .name("Jane")
                .roles(userRoleEntities)
                .build();

        when(mockAccountRepository.checkIfNameIsUsed(requestWithDuplicateName.getName())).thenReturn("Jane");

        // Act & Assert
        assertThrows(AccountAlreadyExistsException.class, () -> createAccountUseCase.CreateAccounts(requestWithDuplicateName));
        verify(mockAccountRepository).checkIfNameIsUsed(requestWithDuplicateName.getName());
        verify(mockAccountRepository, never()).save(any(UserEntity.class));
    }

    @Test
    public void testUpdateAccountWithValidRequest() {
        // Arrange
        Set<UserRoleEntity> userRoleEntities = new HashSet<>();

        long validAccountId = 1L;
        UpdateUserRequest validRequest = UpdateUserRequest.builder()
                .id(validAccountId)
                .password("pass456")
                .name("Jane")
                .roles(userRoleEntities)
                .build();
        UserEntity existingAccount = UserEntity.builder().id(validAccountId).username("John").password("pass123").userRoles(userRoleEntities).build();
        when(mockAccountRepository.findById(validAccountId)).thenReturn(Optional.of(existingAccount));

        // Act
        UpdateUserResponse response = updateAccountUseCase.updateAccount(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Successfully update account with ID 1", response.getMessage());
        verify(mockAccountRepository).findById(validAccountId);
    }

    @Test
    public void testUpdateAccountWithInvalidId() {
        // Arrange
        Set<UserRoleEntity> userRoleEntities = new HashSet<>();

        long invalidAccountId = 99L;
        UpdateUserRequest invalidRequest = new UpdateUserRequest(invalidAccountId, "Jane", "pass456", userRoleEntities);


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
        DeleteUserResponse response = deleteAccountUseCase.deleteAccount(validId);

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
        Set<UserRoleEntity> userRoleEntities = new HashSet<>();

        Long validId = 1L;
        UserEntity accountEntity = UserEntity.builder().id(validId).username("John").password("pass123").userRoles(userRoleEntities).build();
        when(mockAccountRepository.findById(validId)).thenReturn(Optional.of(accountEntity));

        // Act
        Optional<User> response = getAccountUseCase.getAccount(validId);

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
        GetUserResponse response = getAccountsUseCase.getAccounts();

        // Assert
        assertNotNull(response);
        assertTrue(response.getAccounts().isEmpty());
    }

    @Test
    public void testGetAllAccountsWithFilledRepository() {
        // Happy Flow
        Set<UserRoleEntity> userRoleEntities = new HashSet<>();



        List<UserEntity> accounts = List.of(
                UserEntity.builder().id(1L).username("John").password("pass123").userRoles(userRoleEntities).build(),
                UserEntity.builder().id(2L).username("Jane").password("pass456").userRoles(userRoleEntities).build()
        );
        when(mockAccountRepository.findAll()).thenReturn(accounts);

        // Act
        GetUserResponse response = getAccountsUseCase.getAccounts();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.getAccounts().size());
    }}

