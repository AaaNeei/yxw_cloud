package com.yxw.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:阿倪
 * @Date: 2019/3/17 21:37
 * @Description:
 * @return:
 * @throws:
 */
public class MobileCode implements Serializable {
    private static final long serialVersionUID = -7493845126425505162L;
    private int id;
    private String mobile;
    private String code;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
