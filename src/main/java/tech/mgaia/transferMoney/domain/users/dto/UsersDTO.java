package tech.mgaia.transferMoney.domain.users.dto;

import java.math.BigDecimal;

public record UsersDTO(String firstName,
                       String lastName,
                       String document,
                       BigDecimal balance,
                       String email,
                       String password) {
}
