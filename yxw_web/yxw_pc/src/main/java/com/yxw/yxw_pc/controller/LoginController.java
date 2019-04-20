package com.yxw.yxw_pc.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.yxw.base.ResponseResult;
import com.yxw.base.ResponseResultBase;
import com.yxw.base.ResultBase;
import com.yxw.entity.Student;
import com.yxw.entity.enumEntity.RedisKeyName;
import com.yxw.util.CookieUtil;
import com.yxw.yxw_pc.fegin.MemberServiceFegin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

/**
 * @Author:阿倪
 * @Date: 2019/4/19 22:48
 * @Description:
 * @return:
 * @throws:
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class LoginController extends ResponseResultBase {

    private static final String LOGIN = "login";
    private static final String INDEX = "redirect:/";
    private static final String ERROR = "error";

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
        return addTokenToCookie(responseResult, model, response);
    }


    /**
     * 跳转到QQ授权地址
     *
     * @param request
     * @return
     * @throws QQConnectException
     */
    @RequestMapping("/locaQQLogin")
    public String locaQQLogin(HttpServletRequest request) throws QQConnectException {
        String authorizeURL = new Oauth().getAuthorizeURL(request);
        return "redirect:" + authorizeURL;
    }

    /**
     * 配置的授权回调路径
     *
     * @param model
     * @param request
     * @param response
     * @return
     * @throws QQConnectException
     */
    @RequestMapping("/qqLoginCallback")
    public String qqLoginCallback(Model model, HttpServletRequest request, HttpServletResponse response)
            throws QQConnectException {
        AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
        if (accessTokenObj == null) {
            model.addAttribute("error", "qq授权失败!");
            return ERROR;
        }
        String accessToken = accessTokenObj.getAccessToken();
        if (StringUtils.isEmpty(accessToken)) {
            model.addAttribute("error", "qq授权失败!");
            return ERROR;
        }
        // 获取openid
        OpenID openIdObj = new OpenID(accessToken);
        if (openIdObj == null) {
            model.addAttribute("error", "qq授权失败!");
            log.info("####获取openid失败");
            return ERROR;
        }
        String userOpenID = openIdObj.getUserOpenID();
        if (StringUtils.isEmpty(userOpenID)) {
            model.addAttribute("error", "qq授权失败!");
            log.info("####获取openid失败");
            return ERROR;
        }
        log.info("#############获取到openId:{}", userOpenID);
        ResponseResult responseResult = memberServiceFegin.findStudentByOpenId(userOpenID);
        if (StringUtils.pathEquals(responseResult.getResultCode(), ResultBase.YXW_RESULT_ERROR_CODE_OPENID_UNBINDING)) {
            //未与用户绑定 缓存openid
            String yxwOpenIdKey = RedisKeyName.YXW_OPENID_KEY + request.getSession().getId() + userOpenID;
            redisUtil.setString(yxwOpenIdKey, userOpenID);
            // 跳转到绑定用户的登录页
            return "redirct:/login";
        }

        //用户绑定可以直接登录
        //将token放入cookie中
        return addTokenToCookie(responseResult, model, response);

    }

    /**
     * 登录成功，获取token信息放入cookie带到客户端
     *
     * @param responseResult
     * @param model
     * @param response
     * @return
     */

    private String addTokenToCookie(ResponseResult responseResult, Model model, HttpServletResponse response) {
        // 登录成功，获取token信息
        LinkedHashMap linkedHashMap = (LinkedHashMap) responseResult.getData();
        String token = (String) linkedHashMap.get("token");
        if (StringUtils.isEmpty(token)) {
            model.addAttribute("error", "token失效,重新登录");
            //登录失败回到登录页
            return LOGIN;
        }
        //将token放入cookie传给客户端
        CookieUtil.addCookie(response, ResultBase.YXW_MEMBER_COOKIE, token, ResultBase.YXW_LOGIN_COOKIE_TIMEOUT);
        //登录成功重定向到index
        return INDEX;
    }

}
