package com.rooshdashboard.rooshdashboard.domain.Customer;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
}
