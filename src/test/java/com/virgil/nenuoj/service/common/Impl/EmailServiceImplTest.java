package com.virgil.nenuoj.service.common.Impl;

import com.virgil.nenuoj.NenuojApplication;
import com.virgil.nenuoj.service.common.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@SpringBootTest(
        classes = NenuojApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
class EmailServiceImplTest {

    @Resource
    private EmailService emailService;

    @Test
    void sendMail() throws MessagingException {
        emailService.sendMail("949508510@qq.com", "123456", "2021-11-28 20:38");
    }
}