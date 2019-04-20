package com.yxw.api.service;

import com.yxw.base.ResponseResult;
import com.yxw.base.ResultBase;
import com.yxw.entity.Student;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 19:37
 * @Description:
 * @return:
 * @throws:
 */
@RestController
@RequestMapping("/member")
public interface MemberService {
    /**
     * 根据userId查询用户信息
     *
     * @param stuId
     * @return
     */
    @RequestMapping("/findStudentById")
    ResponseResult findStudentById(@RequestParam("stuId") int stuId);

    /**
     * 根据openId查询用户信息
     *
     * @param openId
     * @return
     */
    @RequestMapping("/findStudentByOpenId")
    ResponseResult findStudentByOpenId(@RequestParam("openId") String openId);


    /**
     * 注册用户信息
     *
     * @param student
     * @return
     */
    @RequestMapping("/regStudent")
    ResponseResult regStudent(@RequestBody Student student);

    /**
     * 通过token实现登录,可实现多平台登录
     *
     * @param token
     * @return
     */
    @RequestMapping("/loginByToken")
    ResponseResult loginByToken(@RequestParam("token") String token);

    /**
     * 登录接口
     *
     * @param student
     * @return
     */
    @RequestMapping("/login")
    ResponseResult login(@RequestBody Student student);

    /**
     * QQ登录接口
     *
     * @param student
     * @return
     */
    @RequestMapping("/qqLogin")
    ResponseResult qqLogin(@RequestBody Student student);

    /**
     * 登出接口
     *
     * @param token
     * @return
     */
    @RequestMapping("/loginOut")
    ResponseResult loginOut(@RequestParam("token") String token);


}
