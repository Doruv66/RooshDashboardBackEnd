package com.rooshdashboard.rooshdashboard.tests.controller;

import com.rooshdashboard.rooshdashboard.business.IAccount.*;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidAccountException;
import com.rooshdashboard.rooshdashboard.configuration.security.token.AccessTokenDecoder;
import com.rooshdashboard.rooshdashboard.controller.UserController;
import com.rooshdashboard.rooshdashboard.domain.User.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rooshdashboard.rooshdashboard.persistance.entity.RoleEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetAccountsUseCase getAccountsUseCase;

    @MockBean
    private GetAccountUseCase getAccountUseCase;

    @MockBean
    private DeleteAccountUseCase deleteAccountUseCase;

    @MockBean
    private UpdateAccountUseCase updateAccountUseCase;

    @MockBean
    private CreateAccountUseCase createAccountUseCase;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    private User generateFakeAccount(long id) {
        Role role = Role.builder().roleName("User").build();
        Set<Role> roles = Set.of(role);

        return User.builder()
                .id(id)
                .name("Account" + id)
                .password("fakePass1234")
                .roles(roles)
                .build();
    }

    @Test
    void testGetAccounts_ShouldReturn200ResponseWithAccountArray() throws Exception {
        List<User> accounts = new ArrayList<>();
        accounts.add(generateFakeAccount(1));
        accounts.add(generateFakeAccount(2));
        GetUserResponse response = GetUserResponse.builder().accounts(accounts).build();
        when(getAccountsUseCase.getAccounts()).thenReturn(response);

        mockMvc.perform(get("/accounts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE));
        verify(getAccountsUseCase).getAccounts();
    }

    @Test
    void testGetAccounts_ShouldReturn404WhenNoAccounts() throws Exception {
        when(getAccountsUseCase.getAccounts()).thenThrow(new InvalidAccountException("ACCOUNT_NOT_FOUND"));
        mockMvc.perform(get("/accounts"))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(getAccountsUseCase).getAccounts();
    }

    @Test
    void testGetAccountById_ShouldReturn200ResponseWithAccount() throws Exception {
        User account = generateFakeAccount(1);
        when(getAccountUseCase.getAccount(account.getId())).thenReturn(Optional.of(account));

        mockMvc.perform(get("/accounts/" + account.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE));
        verify(getAccountUseCase).getAccount(account.getId());
    }

    @Test
    void testGetAccountById_ShouldReturn404WhenAccountNotFound() throws Exception {
        long accountId = 1;
        when(getAccountUseCase.getAccount(accountId)).thenReturn(Optional.empty());
        mockMvc.perform(get("/accounts/" + accountId))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getAccountUseCase).getAccount(accountId);
    }

    @Test
    void testCreateAccount_ShouldReturn201ResponseWithCreatedAccount() throws Exception {
        Set<UserRoleEntity> userRoleEntities = new HashSet<>();


        CreateUserRequest request = CreateUserRequest.builder()
                .name("Account")
                .password("fakePass1234")
                .roles(userRoleEntities)
                .build();
        CreateUserResponse response = CreateUserResponse.builder()
                .id(1L)
                .build();
        when(createAccountUseCase.CreateAccounts(request)).thenReturn(response);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE));
        verify(createAccountUseCase).CreateAccounts(request);
    }

    @Test
    void testCreateAccount_ShouldReturn400WhenInvalidData() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(createAccountUseCase, never()).CreateAccounts(request);
    }

    @Test
    void testDeleteAccount_ShouldReturn200() throws Exception {
        long accountId = 1;
        DeleteUserResponse response = DeleteUserResponse.builder().message("").build();
        when(deleteAccountUseCase.deleteAccount(accountId)).thenReturn(response);

        mockMvc.perform(delete("/accounts/" + accountId))
                .andDo(print())
                .andExpect(status().isOk());
        verify(deleteAccountUseCase).deleteAccount(accountId);
     }

    @Test
    void testDeleteAccount_ShouldReturn404WhenAccountNotFound() throws Exception {
        long accountId = 1;
        when(deleteAccountUseCase.deleteAccount(accountId)).thenThrow(new InvalidAccountException("ACCOUNT_NOT_FOUND"));

        mockMvc.perform(delete("/accounts/" + accountId))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(deleteAccountUseCase, never()).deleteAccount(accountId);
    }

    @Test
    void testUpdateAccount_ShouldReturn200ResponseWithUpdatedAccount() throws Exception {
        long accountId = 1;
        Set<UserRoleEntity> userRoleEntities = new HashSet<>();

        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .name("Account")
                .password("fakePass1234")
                .roles(userRoleEntities)
                .build();
        UpdateUserResponse response = UpdateUserResponse.builder().message("").build();

        when(updateAccountUseCase.updateAccount(request)).thenReturn(response);

        mockMvc.perform(put("/accounts/" + accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE));
        verify(updateAccountUseCase).updateAccount(request);
    }

    @Test
    void testUpdateAccount_ShouldReturn400WhenInvalidData() throws Exception {
        long accountId = 1;
        UpdateUserRequest request = new UpdateUserRequest();

        mockMvc.perform(put("/accounts/" + accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(updateAccountUseCase, never()).updateAccount(request);
    }

    @Test
    void testUpdateAccount_ShouldReturn400WhenAccountNotFound() throws Exception {
        long accountId = 1;
        UpdateUserRequest request = new UpdateUserRequest();

        when(updateAccountUseCase.updateAccount( request)).thenThrow(new InvalidAccountException("ACCOUNT_NOT_FOUND"));

        mockMvc.perform(put("/accounts/" + accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(updateAccountUseCase, never()).updateAccount( request);
    }

}
