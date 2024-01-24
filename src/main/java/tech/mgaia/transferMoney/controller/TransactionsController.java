package tech.mgaia.transferMoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.mgaia.transferMoney.domain.transaction.dto.TransactionsDTO;
import tech.mgaia.transferMoney.domain.transaction.model.Transaction;
import tech.mgaia.transferMoney.service.TransactionsService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionsDTO transaction) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionsService.createTransaction(transaction));
    }


}
