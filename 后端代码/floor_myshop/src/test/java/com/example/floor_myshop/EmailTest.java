package com.example.floor_myshop;

import cn.hutool.extra.mail.Mail;
import com.example.floor_myshop.model.EmailModel;
import com.example.floor_myshop.util.MailSendUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {
    @Autowired
    MailSendUtils mailSendUtils;
    @Test
    void testSendEmail() {
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTheme("测试");
        emailModel.setRecieverName("测试");
        emailModel.setEmailContent("测试");
        emailModel.setRecieverEmailAddress("plmoknpwh@163.com");

        mailSendUtils.sendEmailAsSysExceptionHtml(emailModel);
    }
}
