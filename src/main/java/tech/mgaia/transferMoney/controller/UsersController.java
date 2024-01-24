package tech.mgaia.transferMoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.mgaia.transferMoney.domain.users.dto.UsersDTO;
import tech.mgaia.transferMoney.domain.users.model.Users;
import tech.mgaia.transferMoney.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> createUsers(@RequestBody UsersDTO usersDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usersService.create(usersDTO));
    }

    @PostMapping("/update")
    public ResponseEntity<Users> updateUsers(@RequestBody UsersDTO usersDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usersService.update(usersDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.findById(id));
    }

    @PostMapping("/findAll")
    public ResponseEntity<Page<Users>> findAll(@RequestBody Users users,
                                               @PageableDefault() Pageable pageable) {

        Page<Users> usersPage = usersService.findAll(users, pageable);
        return ResponseEntity.ok(usersPage);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsers(@PathVariable Long id) {
        usersService.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Atualizado com sucesso!");
    }


}
