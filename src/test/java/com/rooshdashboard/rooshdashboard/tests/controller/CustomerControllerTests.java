package com.rooshdashboard.rooshdashboard.tests.controller;

import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCustomerException;
import com.rooshdashboard.rooshdashboard.controller.CustomerController;
import com.rooshdashboard.rooshdashboard.domain.Customer.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetCustomersUseCase getCustomersUseCase;

    @MockBean
    private GetCustomerByIdUseCase getCustomerByIdUseCase;

    @MockBean
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @MockBean
    private UpdateCustomerUseCase updateCustomerUseCase;

    @MockBean
    private CreateCustomerUseCase createCustomerUseCase;

    private Customer generateFakeCustomer(long id) {
        return Customer.builder()
                .id(id)
                .name("Customer" + id)
                .email("email" + id + "@example.com")
                .phoneNumber("1234567890")
                .build();
    }

    @Test
    void testGetCustomers_ShouldReturn200ResponseWithCustomersArray() throws Exception {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            customers.add(generateFakeCustomer(i + 1));
        }
        GetCustomersResponse response = GetCustomersResponse.builder().customers(customers).build();
        when(getCustomersUseCase.getCustomers()).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(get("/Customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(getCustomersUseCase).getCustomers();
    }

    @Test
    void testGetCustomers_ShouldReturnEmptyListWhenNoCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        GetCustomersResponse response = GetCustomersResponse.builder().customers(customers).build();
        when(getCustomersUseCase.getCustomers()).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(get("/Customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(getCustomersUseCase).getCustomers();
    }

    @Test
    void testGetCustomerById_ShouldReturn200ResponseWithCustomer() throws Exception {
        Customer customer = generateFakeCustomer(1L);
        GetCustomerByIdResponse response = GetCustomerByIdResponse.builder().customer(customer).build();
        when(getCustomerByIdUseCase.getCustomerById(customer.getId())).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(get("/Customers/" + customer.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(getCustomerByIdUseCase).getCustomerById(customer.getId());
    }

    @Test
    void testGetCustomerById_ShouldReturn404ResponseWhenCustomerNotFound() throws Exception {
        long customerId = 1L;
        when(getCustomerByIdUseCase.getCustomerById(customerId)).thenThrow(new InvalidCustomerException("CUSTOMER_NOT_FOUND"));
        mockMvc.perform(get("/Customers/" + customerId))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(getCustomerByIdUseCase).getCustomerById(customerId);
    }

    @Test
    void testCreateCustomer_ShouldReturn201ResponseWithCreatedCustomer() throws Exception {
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .name("CustomerName")
                .email("customer@example.com")
                .phoneNumber("1234567890")
                .build();
        CreateCustomerResponse response = CreateCustomerResponse.builder().customerId(1L).build();
        when(createCustomerUseCase.createCustomer(request)).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(post("/Customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(createCustomerUseCase).createCustomer(request);
    }

    @Test
    void testCreateCustomer_WithInvalidData_ShouldReturn400() throws Exception {
        CreateCustomerRequest request = new CreateCustomerRequest();
        mockMvc.perform(post("/Customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(createCustomerUseCase, never()).createCustomer(any(CreateCustomerRequest.class));
    }

    @Test
    void testUpdateCustomer_ShouldReturn200ResponseWithUpdatedCustomer() throws Exception {
        long id = 1L;
        UpdateCustomerRequest request = UpdateCustomerRequest.builder()
                .id(id)
                .name("UpdatedName")
                .email("updated@example.com")
                .phoneNumber("0987654321")
                .build();
        UpdateCustomerResponse response = UpdateCustomerResponse.builder().customerId(id).build();
        when(updateCustomerUseCase.updateCustomer(request)).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(request.getId());
        mockMvc.perform(put("/Customers/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(updateCustomerUseCase).updateCustomer(request);
    }

    @Test
    void testUpdateCustomer_ShouldReturn400WhenCustomerNotFound() throws Exception {
        long id = 999L;
        UpdateCustomerRequest request = UpdateCustomerRequest.builder()
                .id(id)
                .name("NotFound")
                .email("notfound@example.com")
                .phoneNumber("0000000000")
                .build();
        when(updateCustomerUseCase.updateCustomer(request)).thenThrow(new InvalidCustomerException("CUSTOMER_NOT_FOUND"));
        mockMvc.perform(put("/Customers/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(updateCustomerUseCase).updateCustomer(request);
    }

    @Test
    void testDeleteCustomer_ShouldReturn200() throws Exception {
        long customerId = 1L;
        DeleteCustomerResponse response = DeleteCustomerResponse.builder().customerId(customerId).build();

        when((deleteCustomerUseCase).deleteCustomer(customerId)).thenReturn(response);
        mockMvc.perform(delete("/Customers/" + customerId))
                .andDo(print())
                .andExpect(status().isOk());
        verify(deleteCustomerUseCase).deleteCustomer(customerId);
    }

    @Test
    void testDeleteCustomer_ShouldReturn400() throws Exception {
        long customerId = 999L;
        doThrow(new InvalidCustomerException("CUSTOMER_NOT_FOUND")).when(deleteCustomerUseCase).deleteCustomer(customerId);
        mockMvc.perform(delete("/Customers/" + customerId))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(deleteCustomerUseCase).deleteCustomer(customerId);
    }
}
