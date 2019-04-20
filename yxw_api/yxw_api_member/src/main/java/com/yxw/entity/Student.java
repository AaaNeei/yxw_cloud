package com.yxw.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:阿倪
 * @Date: 2019/3/6 9:39
 * @Description:
 * @return:
 * @throws:
 */
@Getter
@Setter
public class Student implements Serializable {
    private static final long serialVersionUID = -1810858983160635755L;
    private int stuId;
    private String stuName;
    private String stuSex;
    private int stuAge;
    //学号
    private int stuNum;
    private String stuAddr;
    private String stuHobbies;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String stuUsername;
    private String stuPassword;
    private String comment;
    private int schoolId;
    private int stuCredits;
    private String stuMobile;
    private String stuEmil;
    private String openId;
}
