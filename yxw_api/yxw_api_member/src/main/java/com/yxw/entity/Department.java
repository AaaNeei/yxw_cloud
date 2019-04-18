package com.yxw.entity;

import java.io.Serializable;

/**
 * @Author:阿倪
 * @Date: 2019/3/7 21:14
 * @Description:  院系 与school多对多  与 专业表多对多
 * @return:
 * @throws:
 */
public class Department implements Serializable {

    private static final long serialVersionUID = -7032891847800031055L;
    private Integer depId;
    private String depName;
    private String depIntro;//简介
    private String depCategory;//院系类别(如:机电学院,文史学院......)
    private Integer depNum;//院系编号


}
