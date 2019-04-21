package com.yxw.base;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 17:11
 * @Description:
 * @return:
 * @throws:
 */
public interface ResultBase {

    String YXW_RESULT_SUCCESS_CODE = "200";
    String YXW_RESULT_ERROR_CODE = "500";
    String YXW_RESULT_ERROR_CODE_OPENID_UNBINDING = "101";


    String YXW_RESULT_PAY_FAIL = "fail";
    String YXW_RESULT_PAY_SUCCESS = "success";
    /**
     * 1为已经是平台用户
     */
    String YXW_QQ_LOGIN_STATUS = "1";


    String YXW_RESULT_SUCCESS_MSG = "true";
    String YXW_RESULT_ERROR_MSG = "false";
    String YXW_SEND_EMAIL = "email";
    String YXW_MEMBER_TOKEN = "yxw_member_token";
    String YXW_PAY_TOKEN = "yxw_pay_token";
    String YXW_MEMBER_COOKIE = "yxw_member_cookie";
    Long YXW_LOGIN_TOKEN_TIMEOUT = 60 * 60 * 24 * 90L;
    /**
     * 支付订单失效时间15分钟
     */
    Long YXW_PAY_TOKEN_TIMEOUT = 60 * 15L;
    /**
     * 比token早失效一天
     */
    Integer YXW_LOGIN_COOKIE_TIMEOUT = 60 * 60 * 24 * 89;


}
