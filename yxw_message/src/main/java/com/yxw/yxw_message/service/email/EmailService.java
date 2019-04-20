package com.yxw.yxw_message.service.email;

import com.alibaba.fastjson.JSONObject;
import com.yxw.yxw_message.adapter.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 20:37
 * @Description:
 * @return:
 * @throws:
 */
@Slf4j
@Service
public class EmailService implements MessageAdapter {
    @Value("${msg.subject}")
    private String subject;
    @Value("${msg.text}")
    private String text;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMsg(JSONObject body) {
        String email = body.getString("email");
        log.info("email:{}" + email);
        if (StringUtils.isEmpty(email)) {
            return;
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发送
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo(email);
        // 标题
        simpleMailMessage.setSubject(subject);
        // 内容
        simpleMailMessage.setText(text.replace("{}", email));
        log.info("subject:{}" + subject + ",text:{}" + text);
        mailSender.send(simpleMailMessage);
    }

}
