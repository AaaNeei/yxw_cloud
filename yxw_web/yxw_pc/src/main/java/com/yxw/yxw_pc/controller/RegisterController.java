package com.yxw.yxw_pc.controller;


import com.yxw.base.ResponseResult;
import com.yxw.base.ResponseResultBase;
import com.yxw.base.ResultBase;
import com.yxw.entity.Student;
import com.yxw.yxw_pc.fegin.MemberServiceFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author:阿倪
 * @Date: 2019/4/18 20:21
 * @Description:
 * @return:
 * @throws:
 */
@Controller
@RequestMapping("/member")
public class RegisterController extends ResponseResultBase {
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";


    @Autowired
    private MemberServiceFegin memberServiceFegin;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet() {
        return REGISTER;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@RequestBody Student student, Model model) {
        ResponseResult responseResult = memberServiceFegin.regStudent(student);
        if (StringUtils.pathEquals(responseResult.getResultCode(), ResultBase.YXW_RESULT_SUCCESS_CODE)) {
            model.addAttribute("error", responseResult.getResultMsg());
            return REGISTER;
        }
        return LOGIN;
    }

}
