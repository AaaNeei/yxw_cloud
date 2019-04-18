package com.yxw.entity;

import java.io.Serializable;

/**
 * @Author:阿倪
 * @Date: 2019/3/8 21:19
 * @Description:
 * @return:
 * @throws:
 */
public class Province implements Serializable {

    private static final long serialVersionUID = -5632695264859503362L;
    private Integer provinceId;
    private String provinceName;
    private String comment;
    private Integer provinceNum;
    private Integer countryNum;
    private String provinceShort;


    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getProvinceNum() {
        return provinceNum;
    }

    public void setProvinceNum(Integer provinceNum) {
        this.provinceNum = provinceNum;
    }

    public Integer getCountryNum() {
        return countryNum;
    }

    public void setCountryNum(Integer countryNum) {
        this.countryNum = countryNum;
    }

    public String getProvinceShort() {
        return provinceShort;
    }

    public void setProvinceShort(String provinceShort) {
        this.provinceShort = provinceShort;
    }
}
