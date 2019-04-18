package com.yxw.yxw_message.service.email;

import com.alibaba.fastjson.JSONObject;
import com.yxw.yxw_message.adapter.MessageAdapter;
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
@Service
public class EmailService implements MessageAdapter {
    @Value("${msg.subject}")
    private String subject;
    @Value("${msg.text}")
    private String text;
    @Autowired
    private JavaMailSender mailSender; // 自动注入的Bean

    @Override
    public void sendMsg(JSONObject body) {
        String email = body.getString("email");
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
        mailSender.send(simpleMailMessage);
    }

}
