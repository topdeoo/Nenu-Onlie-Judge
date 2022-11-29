package com.virgil.nenuoj.service.common.Impl;

import com.virgil.nenuoj.service.common.EmailService;
import com.virgil.nenuoj.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;


    @Async
    @Override
    public void sendMail( String email ,String code, String expireTime ) throws MessagingException {

        Context context = new Context();
        context.setVariable(Constant.Email.OJ_SHORT_NAME.getVal() , "NENUOJ");
        context.setVariable(Constant.Email.OJ_NAME.getVal() , "NENU Online Judge");
        context.setVariable(Constant.Email.OJ_URL.getVal() , "https://acm.ldggzqjj.wiki");
        context.setVariable(Constant.Email.EMAIL_BACKGROUND_IMG.getVal() , "https://virgil-civil-1311056353.cos.ap-shanghai.myqcloud.com/img/img-202211272038.png");
        context.setVariable(Constant.Email.EMAIL_CODE.getVal() , code);
        context.setVariable(Constant.Email.EMAIL_EXPIRE_TIME.getVal() , expireTime);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(email);
        helper.setSubject("[NENUOJ] 注册验证码");
        String html = templateEngine.process("emailTemplate_registerCode", context);
        helper.setText(html, true);
        javaMailSender.send(message);
    }
}
