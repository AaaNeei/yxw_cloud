package com.yxw.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:阿倪
 * @Date: 2019/3/6 9:39
 * @Description:
 * @return:
 * @throws:
 */

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

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuAddr() {
        return stuAddr;
    }

    public void setStuAddr(String stuAddr) {
        this.stuAddr = stuAddr;
    }

    public String getStuHobbies() {
        return stuHobbies;
    }

    public void setStuHobbies(String stuHobbies) {
        this.stuHobbies = stuHobbies;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStuUsername() {
        return stuUsername;
    }

    public void setStuUsername(String stuUsername) {
        this.stuUsername = stuUsername;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getStuCredits() {
        return stuCredits;
    }

    public void setStuCredits(int stuCredits) {
        this.stuCredits = stuCredits;
    }

    public String getStuMobile() {
        return stuMobile;
    }

    public void setStuMobile(String stuMobile) {
        this.stuMobile = stuMobile;
    }

    public String getStuEmil() {
        return stuEmil;
    }

    public void setStuEmil(String stuEmil) {
        this.stuEmil = stuEmil;
    }
}
