package com.yxw.yxw_member.mapper;


import com.yxw.base.ResultBase;
import com.yxw.entity.Student;

import java.util.List;

/**
 * @Author:阿倪
 * @Date: 2019/3/6 10:38
 * @Description:
 * @return:
 * @throws:
 */

public interface StudentMapper {
    /**
     * 根据id查询对象
     * @param stuId
     * @return
     */
    Student findStudentById(Integer stuId);

    int deleteByPrimaryKey(Integer userId);

    /**
     * 注册
     * @param student
     * @return
     */
    int insert(Student student);

    int insertSelective(Student student);


    Student selectByStudentLogin(Student student);

    int updateByPrimaryKeySelective(Integer stuId);

    //int updateByPrimaryKey(Integer stuId);
    //
    List<Student> selectAllStudent();

    Student ajaxCheckStuUsername(String stuUsername);

}
