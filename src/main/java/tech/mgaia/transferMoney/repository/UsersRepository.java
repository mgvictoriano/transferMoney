package tech.mgaia.transferMoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.mgaia.transferMoney.domain.users.model.Users;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByDocument(String document);
}
