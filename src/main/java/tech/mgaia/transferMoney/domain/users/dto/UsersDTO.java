package tech.mgaia.transferMoney.domain.users.dto;

import tech.mgaia.transferMoney.domain.users.model.UserType;

import java.math.BigDecimal;

public record UsersDTO(String firstName,
                       String lastName,
                       String document,
                       BigDecimal balance,
                       String email,
                       String password,
                       UserType userType) {
}
