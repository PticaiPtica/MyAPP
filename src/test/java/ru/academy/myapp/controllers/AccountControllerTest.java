package ru.academy.myapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.academy.myapp.entity.Account;
import ru.academy.myapp.entity.Author;
import ru.academy.myapp.repository.AccountRepository;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String accountNameJson = "Kostya";
    private final String accountEmailJson = "kostya@gmail.com";
    private final String accountCountryJson = "Russia";
    private final Integer accountStarsJson = 5;
    private final Integer accountYearJson = 27;
    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void test_Create_Account_valid_Data_return_account() throws Exception {
        final Account account = new Account(accountNameJson,
                accountEmailJson,
                accountCountryJson,
                accountStarsJson,
                accountYearJson);


        mockMvc.perform(
                        post("/api/myApp/Account/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(account))
                ).andExpect(status().isOk())

                .andExpect(jsonPath("$.email").value(account.getEmail()))
                .andExpect(jsonPath("$.country").value(account.getCountry()))
                .andExpect(jsonPath("$.stars").value(account.getStars()))
                .andExpect(jsonPath("$.year").value(account.getYear()))
        ;

        accountRepository.delete(account);

    }

    @Test
    public void test_get_AccountById_valid_Data_return_account() throws Exception {
        final Account account = new Account(accountNameJson,
                accountEmailJson,
                accountCountryJson,
                accountStarsJson,
                accountYearJson);
        String lastId = String.valueOf(accountRepository.findAll().size());

        accountRepository.delete(account);
        mockMvc.perform(get("/api/myApp/")).andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(account.getEmail()))
                .andExpect(jsonPath("$.country").value(account.getCountry()));
    }
}
