package com.yxw.api.service;

import com.yxw.base.ResponseResult;
import com.yxw.base.ResultBase;
import com.yxw.entity.Student;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 19:37
 * @Description:
 * @return:
 * @throws:
 */
public interface MemberService {
    /**
     * 根据userId查询用户信息
     *
     * @param stuId
     * @return
     */
    ResponseResult findStudentById(int stuId);

    /**
     * 注册用户信息
     *
     * @param student
     * @return
     */
    ResponseResult regStudent(@RequestBody Student student);
}
