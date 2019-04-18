package com.yxw.entity;

import java.io.Serializable;

/**
 * @Author:阿倪
 * @Date: 2019/3/8 20:28
 * @Description: 专业
 * @return:
 * @throws:
 */
public class Profession implements Serializable {

    private static final long serialVersionUID = 2264508849982978239L;
    private int professionId;
    private String professionName;
    private String professionCategory;
    private String professionIntro;
    private String comment;
    private String professionNum;

    public String getProfessionNum() {
        return professionNum;
    }

    public void setProfessionNum(String professionNum) {
        this.professionNum = professionNum;
    }

    public int getProfessionId() {
        return professionId;
    }

    public void setProfessionId(int professionId) {
        this.professionId = professionId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getProfessionCategory() {
        return professionCategory;
    }

    public void setProfessionCategory(String professionCategory) {
        this.professionCategory = professionCategory;
    }

    public String getProfessionIntro() {
        return professionIntro;
    }

    public void setProfessionIntro(String professionIntro) {
        this.professionIntro = professionIntro;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
