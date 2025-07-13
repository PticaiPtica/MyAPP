package ru.academy.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.academy.myapp.entity.Account;
import ru.academy.myapp.entity.AccountDto;
import ru.academy.myapp.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("api/myApp/Account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<AccountDto> list = accountService.findAll();
        if (list.isEmpty()) {

            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (accountService.findById(id).isPresent()) {
            return ResponseEntity.ok(accountService.findById(id).get());
        }
       return ResponseEntity.noContent().build();
    }


    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Account account) {

        return ResponseEntity.ok(accountService.save(account));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        accountService.findByEmail(email);
        if (accountService.findByEmail(email).isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accountService.findByEmail(email));
    }

    @GetMapping("/country")
    public ResponseEntity<?> findByCountry() {
        if (accountService.findByRussiaAndStarsFour().isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accountService.findByRussiaAndStarsFour());
    }

/*    @GetMapping("/year")
    public ResponseEntity<?> findByYear() {

        if (accountService.findByYear().isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accountService.findByYear());
    }*/


}
