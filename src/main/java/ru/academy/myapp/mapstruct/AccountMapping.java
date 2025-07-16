package ru.academy.myapp.mapstruct;


import ru.academy.myapp.entity.Account;
import ru.academy.myapp.entity.AccountDto;

@Mapper()
public interface AccountMapping {
    AccountDto toDto(Account account);




}
