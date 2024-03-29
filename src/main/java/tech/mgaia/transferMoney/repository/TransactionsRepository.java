package tech.mgaia.transferMoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.mgaia.transferMoney.model.Transaction;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
}
