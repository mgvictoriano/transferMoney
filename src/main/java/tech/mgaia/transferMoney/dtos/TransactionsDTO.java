package tech.mgaia.transferMoney.dtos;

import java.math.BigDecimal;

public record TransactionsDTO(
        BigDecimal value,
        Long senderId,
        Long receiverId
) {


}
