package com.yxw.yxw_pc.controller;

import com.alibaba.fastjson.JSON;
import com.yxw.base.ResponseResult;
import com.yxw.base.ResultBase;
import com.yxw.entity.Student;
import com.yxw.util.CookieUtil;
import com.yxw.yxw_pc.fegin.MemberServiceFegin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * @Author:阿倪
 * @Date: 2019/4/19 21:29
 * @Description:
 * @return:
 * @throws:
 */
@Slf4j
@Controller
public class IndexController {
    private static final String INDEX = "index";


    @Autowired
    private MemberServiceFegin memberServiceFegin;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Student student, Model model, HttpServletRequest request) {
        //如果有token就是登陆,回显用户名
        String token = CookieUtil.getCookie(request, ResultBase.YXW_MEMBER_COOKIE);
        if (!StringUtils.isEmpty(token)) {
            ResponseResult responseResult = memberServiceFegin.loginByToken(token);
            if (StringUtils.equals(responseResult.getResultCode(), ResultBase.YXW_RESULT_SUCCESS_CODE)) {
                //远程调用时会 转linkedHashMap是业务封装之后又json转换
                LinkedHashMap data = (LinkedHashMap) responseResult.getData();
                log.info("=======token:{}认证,用户名:{},data:{}", token, data.get("stuUsername"), JSON.toJSONString(data));
                model.addAttribute("stuUsername", data.get("stuUsername"));
            }
        }
        //没有token就是直接到首页
        log.info("####登陆的token:{}", token);
        return INDEX;
    }
}
