package tech.mgaia.transferMoney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mgaia.transferMoney.domain.users.model.UserType;
import tech.mgaia.transferMoney.domain.users.model.Users;
import tech.mgaia.transferMoney.repository.UsersRepository;

import java.math.BigDecimal;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public void validateTransaction(Users sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transações");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public Users findById(Long id) throws Exception {
        return usersRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(Users user) {
        usersRepository.save(user);
    }

}
