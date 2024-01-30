package tech.mgaia.transferMoney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.mgaia.transferMoney.dtos.TransactionsDTO;
import tech.mgaia.transferMoney.model.Transaction;
import tech.mgaia.transferMoney.model.Users;
import tech.mgaia.transferMoney.repository.TransactionsRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionsDTO transaction) throws Exception {
        Users sender = usersService.findById(transaction.senderId());
        Users receiver = usersService.findById(transaction.receiverId());

        usersService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        transactionsRepository.save(newTransaction);
        usersService.saveUser(sender);
        usersService.saveUser(receiver);

        notificationService.sendNotification(sender, "Transação realizada com sucesso");
        notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newTransaction;
    }

    public boolean authorizeTransaction(Users sender, BigDecimal value) {
        String url = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

        ResponseEntity<Map> authorizatioResponse = restTemplate.getForEntity(url, Map.class);

        if (authorizatioResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizatioResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;

    }

}
