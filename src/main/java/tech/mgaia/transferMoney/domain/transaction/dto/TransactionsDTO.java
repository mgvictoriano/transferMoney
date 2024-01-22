package tech.mgaia.transferMoney.domain.transaction.dto;

import java.math.BigDecimal;

public record TransactionsDTO(
        BigDecimal value,
        Long senderId,
        Long receiverId
) {


}
