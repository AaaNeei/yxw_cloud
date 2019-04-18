package com.yxw.api.service;


import com.yxw.entity.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author:阿倪
 * @Date: 2019/3/6 9:46
 * @Description:
 * @return:
 * @throws:
 */

public interface StudentService {
    int addUser(Student student, HttpServletRequest request);

    List<Student> findAllUser(int pageNum, int pageSize);

    Student ajaxCheckStuUsername(String stuUsername);
}
