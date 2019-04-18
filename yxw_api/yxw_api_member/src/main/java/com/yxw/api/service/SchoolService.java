package com.yxw.api.service;

import com.yxw.entity.School;

import java.util.List;
import java.util.Map;

/**
 * @Author:阿倪
 * @Date: 2019/3/10 17:27
 * @Description:
 * @return:
 * @throws:
 */
public interface SchoolService {
    //注册页面选择省份之后ajxa获取省份高校
    List<School> getSchoolByProvinceNum(Integer provinceNum);

    Map<String, List<School>> getSchoolAllByProvince();
}
