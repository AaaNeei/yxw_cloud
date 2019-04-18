package com.yxw.entity.enumEntity;

/**
 * @Author:阿倪
 * @Date: 2019/3/7 21:44
 * @Description:    专业类别枚举
 * @return:
 * @throws:
 */
public enum ProfessionCategoryEnum {

/*
   中国大学共有13个学科，92个大学专业类，630个大学专业。
   13个学科分别是：哲学philosophy 、经济学economics、法学legal、教育学pedagogy、文学 literature、
   历史学history、
   理学Natural Science、工学engineering、农学agronomy、
   医学medicine 、军事学military science 、管理学management 、艺术学art theory
*/
    PHILOSOPHY("哲学", 1),
    ECONOMICS("经济学", 2),
    LEGAL("法学", 3),
    PEDAGOGY("教育学", 4),
    LITERATURE("文学", 5),
    HISTORY("历史学", 6),
    NATURAL_SCIENCE("理学", 7),
    ENGINEERING("工学", 8),
    AGRONOMY("农学", 9),
    MEDICINE("医学", 10),
    MILITARY_SCIENCE("军事学", 11),
    MANAGEMENT("管理学", 12),
    ART_THEORY("艺术学", 13);

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        this.categoryNum = categoryNum;
    }

    ProfessionCategoryEnum(String categoryName, int categoryNum) {

        this.categoryName = categoryName;
        this.categoryNum = categoryNum;
    }

    private int categoryNum;


}
