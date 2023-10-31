package com.rooshdashboard.rooshdashboard.domain.Account;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetAccountResponse {
    private List<Account> accounts;
}
