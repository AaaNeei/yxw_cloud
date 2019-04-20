package com.yxw.yxw_member.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yxw.base.ResponseResult;
import com.yxw.base.ResponseResultBase;
import com.yxw.api.service.MemberService;
import com.yxw.base.ResultBase;
import com.yxw.entity.Student;
import com.yxw.util.MD5Util;
import com.yxw.util.RedisUtil;
import com.yxw.util.TokenUtil;
import com.yxw.yxw_member.mapper.StudentMapper;
import com.yxw.yxw_member.mq.RegisterMailboxProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

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
    private RedisUtil redisUtil;
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
    public ResponseResult findStudentByOpenId(@RequestParam("openId") String openId) {

        Student studentByOpenId = studentMapper.findStudentByOpenId(openId);

        if (studentByOpenId == null) {
            //说明该qq对应的openid为绑定网站的用户
            return setErrorResult(ResultBase.YXW_RESULT_ERROR_CODE_OPENID_UNBINDING, "还未绑定用户");
        }
        //已经绑定,直接授权登录返回token
        return loginReturnToken(studentByOpenId);
    }

    @Override
    public ResponseResult regStudent(@RequestBody Student student) {
        String password = student.getStuPassword();
        String md5Password = MD5Util.MD5(password);
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

    @Override
    public ResponseResult loginByToken(@RequestParam String token) {
        if (StringUtils.isEmpty(token)) {
            return setErrorResult("token不能为空!");
        }
        String stuId = redisUtil.getString(token);
        if (StringUtils.isEmpty(stuId)) {
            return setErrorResult("未查询到用户信息");
        }
        Student student = studentMapper.findStudentById(Integer.valueOf(stuId));
        if (student == null) {
            return setErrorResult("token已经失效,请重新登录");
        }
        //返回给前端用户对象一定把密码去掉
        student.setStuPassword(null);
        return setSuccessResult(student);
    }

    @Override
    public ResponseResult login(@RequestBody Student student) {
        ResponseResult loginResult = loginReturnStudent(student);
        student = (Student) loginResult.getData();
        return loginReturnToken(student);
    }


    @Override
    public ResponseResult qqLogin(@RequestBody Student student) {
        String openId = student.getOpenId();
        if (StringUtils.isEmpty(openId)) {
            return setErrorResult("openId不能为空");
        }
        ResponseResult responseResult = loginReturnStudent(student);
        //服务内可以直接强转为原对象
        student = (Student) responseResult.getData();
        //1,有网站账号的,直接绑定openId
        student.setOpenId(openId);
        student.setUpdateTime(new Date());
        Integer integer = studentMapper.updateStudentBindingOpenId(student);
        if (integer < 0) {
            setErrorResult("QQ授权绑定失败");
        }
        //2,没有网站账号的,注册账号并且绑定openId

        //绑定成功直接登录
        return loginReturnToken(student);
    }

    /**
     * 提取登录校验,返回登录对象
     *
     * @param student
     * @return
     */
    private ResponseResult loginReturnStudent(Student student) {
        if (StringUtils.isEmpty(student.getStuUsername())) {
            return setErrorResult("用户名不能为空!");
        }
        if (StringUtils.isEmpty(student.getStuPassword())) {
            return setErrorResult("密码不能为空!");
        }
        student.setStuPassword(MD5Util.MD5(student.getStuPassword()));
        Student resultStudent = studentMapper.selectByStudentLogin(student);
        if (resultStudent == null) {
            return setErrorResult("用户名或密码不正确!");
        }

        return setSuccessResult(resultStudent);
    }

    /**
     * 登录成功时返回的token
     *
     * @param student
     * @return
     */
    private ResponseResult loginReturnToken(Student student) {
        // 生成token
        String memberToken = TokenUtil.getMemberToken();
        //将token存入缓存
        redisUtil.setString(memberToken, student.getStuId(), ResultBase.YXW_LOGIN_TOKEN_TIMEOUT);
        log.info("#####登录成功,memberToken:{},stuId:{}", memberToken, student.getStuId());
        //使用json返回,方便前台理解接收
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", memberToken);

        return setSuccessResult(jsonObject);
    }

    @Override
    public ResponseResult loginOut(@RequestParam("token") String token) {
        redisUtil.delKey(token);
        return setSuccessResult("缓存已删除!");
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

    /**
     * 发送email 先放入mq,message工程从mq中获取
     *
     * @param json
     */
    private void sendMsg(String json) {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(messagesQueue);
        registerMailboxProducer.sendMsg(activeMQQueue, json);
    }
}
