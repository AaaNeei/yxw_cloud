package com.yxw.yxw_message.mq;

import com.alibaba.fastjson.JSONObject;
import com.yxw.base.ResultBase;
import com.yxw.yxw_message.adapter.MessageAdapter;
import com.yxw.yxw_message.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 20:35
 * @Description: 消息管理接口
 * @return:
 * @throws:
 *
 * {
 *     "header": {
 *         "interfaceType": "接口类型"
 *     },
 *     "content": {}
 * }
 */
@Slf4j
@Component
public class ConsumerDistribute {
    @Autowired
    private EmailService emailService;
    @Autowired
    private MessageAdapter messageAdapter;
    @JmsListener(destination = "messages_email")
    public void distribute(String json) {
        log.info("####ConsumerDistribute###distribute() 消息服务平台接受 json参数:" + json);

        if (StringUtils.isEmpty(json)) {
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject header = jsonObject.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");

        if (StringUtils.isEmpty(interfaceType)) {
            return;
        }
        if (StringUtils.pathEquals(ResultBase.YXW_SEND_EMAIL,interfaceType)) {
            messageAdapter = emailService;
        }
        if (messageAdapter == null) {
            return;
        }
        JSONObject body = jsonObject.getJSONObject("content");
        messageAdapter.sendMsg(body);


    }

}
