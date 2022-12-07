package com.virgil.nenuoj.service.oj.impl;

import com.virgil.nenuoj.NenuojApplication;
import com.virgil.nenuoj.common.exception.StatusFailException;
import com.virgil.nenuoj.mappers.AccountMappers;
import com.virgil.nenuoj.service.oj.AccountService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HashMap;

@SpringBootTest(
        classes = NenuojApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMappers accountMappers;

    private final String username = "virgil";

    private final String password = Md5Crypt.md5Crypt("123456".getBytes());

    private final String email = "949508510@qq.com";

    private final String newPassword = Md5Crypt.md5Crypt("666666".getBytes());

    private final String newEmail = "virgiling7@gmail.com";

    @Test
    void login() {
        HashMap<String, String> header = new HashMap<>();
        header.put("x-forwarded-for", "127.0.0.1");
        try {
            HashMap<String, Object> result =  accountService.login(username ,password ,header);
            System.out.println(result);
        }
        catch (StatusFailException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    void register() {

    }

    @Test
    void modifyPassword() {
    }

    @Test
    void modifyEmail() {
    }

    @Test
    void verifyEmail() {
    }

    @Test
    void modifyInfo() {
    }
}