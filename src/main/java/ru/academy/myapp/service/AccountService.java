package ru.academy.myapp.service;

import ru.academy.myapp.entity.Account;
import ru.academy.myapp.entity.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<AccountDto> save(Account fact);


    Optional<AccountDto> findById(long id);

    List<AccountDto> findAll();


    List<AccountDto> findByEmail(String email);


    List<AccountDto> findByRussiaAndStarsFour();




}
