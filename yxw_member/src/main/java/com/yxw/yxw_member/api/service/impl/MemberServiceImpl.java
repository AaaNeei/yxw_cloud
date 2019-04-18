package com.yxw.yxw_member.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yxw.base.ResponseResult;
import com.yxw.base.ResponseResultBase;
import com.yxw.api.service.MemberService;
import com.yxw.base.ResultBase;
import com.yxw.entity.Student;
import com.yxw.util.MD5Util;
import com.yxw.yxw_member.mapper.StudentMapper;
import com.yxw.yxw_member.mq.RegisterMailboxProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 19:37
 * @Description:
 * @return:
 * @throws:
 */
@Service
@Slf4j
public class MemberServiceImpl extends ResponseResultBase implements MemberService {

    /**
     *
     */
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;
    @Value("${messages.queue}")
    private String messagesQueue;

    @Override
    public ResponseResult findStudentById(int stuId) {
        Student student = studentMapper.findStudentById(stuId);
        if (student == null) {
            return setErrorResult("用户信息不存在!");
        }
        return setSuccessResult(student);
    }

    @Override
    public ResponseResult regStudent(@RequestBody Student student) {
        String password = student.getStuPassword();
        String md5Password = MD5Util.MD5(password + "salt");
        student.setStuPassword(md5Password);
        //按照标准制定json
        String jSon = emailJSon(student.getStuEmil());
        log.info("按照标准制定json-----" + jSon + ",推送消息到mq");
        //发送邮件
        sendMsg(jSon);
        log.info("推送完毕");
        int insert = studentMapper.insert(student);
        if (insert > 0) {
            return setSuccessResult("注册成功...");
        }
        return setErrorResult("注册失败!!!");
    }

    private String emailJSon(String email) {
        JSONObject root = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", ResultBase.YXW_SEND_EMAIL);
        JSONObject content = new JSONObject();
        content.put("email", email);
        root.put("header", header);
        root.put("content", content);
        return root.toJSONString();
    }

    private void sendMsg(String json) {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(messagesQueue);
        registerMailboxProducer.sendMsg(activeMQQueue, json);
    }
}
