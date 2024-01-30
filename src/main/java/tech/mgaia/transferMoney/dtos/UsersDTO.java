package tech.mgaia.transferMoney.dtos;

import tech.mgaia.transferMoney.model.UserType;

import java.math.BigDecimal;

public record UsersDTO(String firstName,
                       String lastName,
                       String document,
                       BigDecimal balance,
                       String email,
                       String password,
                       UserType userType) {
}
