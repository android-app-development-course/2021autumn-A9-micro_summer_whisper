package com.example.floor_myshop;

import com.example.floor_myshop.entity.Account;
import com.example.floor_myshop.service.IAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    IAccountService accountService;


    @Test
    void testRegist() {
        System.out.println("regist");
        Account account = new Account();
        account.setUserName("test4");
        account.setPassword("test4");
        account.setCreateTime(LocalDateTime.now());
        accountService.register(account);
    }
}
