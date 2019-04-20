package com.yxw.yxw_pc.controller;

import com.yxw.base.ResponseResult;
import com.yxw.base.ResponseResultBase;
import com.yxw.base.ResultBase;
import com.yxw.entity.Student;
import com.yxw.util.CookieUtil;
import com.yxw.yxw_pc.fegin.MemberServiceFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

/**
 * @Author:阿倪
 * @Date: 2019/4/19 22:48
 * @Description:
 * @return:
 * @throws:
 */
@Controller
@RequestMapping("/member")
public class LoginController {

    private static final String LOGIN = "login";
    private static final String INDEX = "redirect:/";

    @Autowired
    private MemberServiceFegin memberServiceFegin;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return LOGIN;
    }

    @RequestMapping("/loginOutPc")
    public String loginOut(Model model, HttpServletResponse response, HttpServletRequest request) {
        String token = CookieUtil.getCookie(request, ResultBase.YXW_MEMBER_COOKIE);
        //删除缓存
        ResponseResult responseResult = memberServiceFegin.loginOut(token);
        if (!StringUtils.pathEquals(responseResult.getResultCode(), ResultBase.YXW_RESULT_SUCCESS_CODE)) {
            model.addAttribute("error", "退出失败");
            return INDEX;
        }
        //清除cookie
        CookieUtil.removeCookie(response, ResultBase.YXW_MEMBER_COOKIE);

        return INDEX;
    }

    @RequestMapping(value = "/loginToken", method = RequestMethod.POST)
    public String loginToken(@RequestParam String token) {
        ResponseResult responseResult = memberServiceFegin.loginByToken(token);
        if (StringUtils.pathEquals(responseResult.getResultCode(), ResultBase.YXW_RESULT_SUCCESS_CODE)) {

        }


        return INDEX;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Student student, Model model, HttpServletResponse response) {
        ResponseResult responseResult = memberServiceFegin.login(student);
        if (!StringUtils.pathEquals(responseResult.getResultCode(), ResultBase.YXW_RESULT_SUCCESS_CODE)) {
            model.addAttribute("error", responseResult.getResultMsg());
            return LOGIN;

        }
        // 登录成功，获取token信息
        LinkedHashMap linkedHashMap = (LinkedHashMap) responseResult.getData();
        String token = (String) linkedHashMap.get("token");
        if (StringUtils.isEmpty(token)) {
            model.addAttribute("error", "token失效,重新登录");
            return LOGIN;
        }
        //将token放入cookie传给客户端
        CookieUtil.addCookie(response, ResultBase.YXW_MEMBER_COOKIE, token, ResultBase.YXW_LOGIN_COOKIE_TIMEOUT);
        //登录成功重定向到index
        return INDEX;
    }

}
