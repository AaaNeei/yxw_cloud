package com.yxw.yxw_member.controller;

import com.yxw.api.service.MemberService;
import com.yxw.base.ResponseResult;
import com.yxw.entity.Student;
import com.yxw.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 20:21
 * @Description:
 * @return:
 * @throws:
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/findStudentById")
    public ResponseResult findStudentById(Integer stuId) {
        return memberService.findStudentById(stuId);
    }

    @RequestMapping("/regStudent")
    public ResponseResult regStudent(@RequestBody Student student) {
        return memberService.regStudent(student);

    }


}
