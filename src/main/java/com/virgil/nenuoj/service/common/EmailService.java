package com.virgil.nenuoj.service.common;


import javax.mail.MessagingException;

public interface EmailService {

    void sendMail( String email, String code, String expireTime ) throws MessagingException;

}
