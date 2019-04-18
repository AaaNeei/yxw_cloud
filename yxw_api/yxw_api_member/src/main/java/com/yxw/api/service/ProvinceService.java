package com.yxw.api.service;


import com.yxw.entity.Province;

import java.util.List;

/**
 * @Author:阿倪
 * @Date: 2019/3/10 16:31
 * @Description:
 * @return:
 * @throws:
 */
public interface ProvinceService {

    /**
     * 注入页面获取所有省份
     *
     * @return
     */
    List<Province> selectAllProvince();

}
