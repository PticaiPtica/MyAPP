package ru.academy.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.academy.myapp.mapstruct.AccountMapping;
import ru.academy.myapp.entity.Account;
import ru.academy.myapp.entity.AccountDto;
import ru.academy.myapp.repository.AccountRepository;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final AccountMapping accountMapping;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapping accountMapping) {
        this.accountRepository = accountRepository;
        this.accountMapping = accountMapping;
    }


    @Override
    public Optional<AccountDto> save(Account account) {
        accountRepository.save(account);
        return Optional.of(accountMapping.toDto(account));
    }

    @Override
    public Optional<AccountDto> findById(long id) {
        return accountRepository.findById(id).map(accountMapping::toDto);
    }

    @Override
    public List<AccountDto> findAll() {

        return List.of(accountRepository.findAll().stream().map(accountMapping::toDto).toArray(AccountDto[]::new));
    }

    @Override
    public List<AccountDto> findByEmail(String email) {
        List<Account> list = accountRepository.findAll();
        String test = email.substring(email.lastIndexOf("@") + 1);


        List<AccountDto> dtos = new ArrayList<>();

        for (Account account : list) {
            String email2 = account.getEmail();
            String Email = email2.substring(email2.lastIndexOf("@") + 1);

            if (Email.equals(test)) {

                dtos.add(accountMapping.toDto(account));

            }


        }


        return dtos;
    }

/*
    @Override
    public List<AccountDto> findByYear() {
        return List.of(accountRepository.findByYear().stream().map(accountMapping::toDto).toArray(AccountDto[]::new));
    }
*/

    @Override
    public List<AccountDto> findByRussiaAndStarsFour() {
        String str = "Russia";
        int integer = 4;

        return List.of(accountRepository.findAllByCountryAndStarsAfter(str, integer)
                .stream()
                .map(accountMapping::toDto)
                .toArray(AccountDto[]::new));
    }


}
