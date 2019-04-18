package com.yxw.entity.enumEntity;

/**
 * @Author:阿倪
 * @Date: 2019/3/7 21:39
 * @Description: 高校等级枚举类
 * @return:
 * @throws:
 */

public enum SchoolGradeEnum {

    INTERNARIONALITY_COLLEGE("国际高校", 1),
    DOMESTIC_COLLEGE("国内高校", 2),
    CHAIN_985_COLLEGE("中国985高校", 3),
    CHAIN_211_COLLEGE("中国211高校", 4),
    ONE_COLLEGE("一类本科", 5),
    TWO_COLLEGE("二类本科", 6),
    THREE_COLLEGE("三类本科", 7),
    JUNIOR_COLLEGE("大专", 8),
    OTHER_COLLEGE("其他", 9);

    private String gradeName;
    private int gradeNum;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(int gradeNum) {
        this.gradeNum = gradeNum;
    }

    SchoolGradeEnum(String gradeName, int gradeNum) {

        this.gradeName = gradeName;
        this.gradeNum = gradeNum;
    }
}
