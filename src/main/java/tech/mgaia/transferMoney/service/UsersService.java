package tech.mgaia.transferMoney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.mgaia.transferMoney.domain.users.dto.UsersDTO;
import tech.mgaia.transferMoney.domain.users.mapper.UsersMapper;
import tech.mgaia.transferMoney.domain.users.model.UserType;
import tech.mgaia.transferMoney.domain.users.model.Users;
import tech.mgaia.transferMoney.infra.ServiceCrud;
import tech.mgaia.transferMoney.repository.UsersRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UsersService extends ServiceCrud<Users, Long> {

    @Autowired
    private UsersRepository usersRepository;
    private final UsersMapper userMapper = UsersMapper.INSTANCE;

    @Override
    public Users create(Users user) {
        return usersRepository.save(user);
    }

    public Users create(UsersDTO usersDTO) {
        Users user = userMapper.toObject(usersDTO);
        create(user);
        return user;
    }

    @Override
    public Users update(Users user) {
        return usersRepository.save(user);
    }

    public Users update(UsersDTO usersDTO) {
        Users user = userMapper.toObject(usersDTO);
        update(user);
        return user;
    }

    @Override
    public Users findById(Long userId) {
        Optional<Users> usersOptional = usersRepository.findById(userId);
        return usersOptional.orElseThrow();
    }

    @Override
    public Page<Users> findAll(Users users, Pageable pageable) {
        return usersRepository.findAll(Example.of(users), pageable);
    }

    @Override
    public void removeById(Long id) {
        findById(id);
        usersRepository.deleteById(id);
    }

    public void saveUser(Users user) {
        usersRepository.save(user);
    }

    public void validateTransaction(Users sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transações");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

}
