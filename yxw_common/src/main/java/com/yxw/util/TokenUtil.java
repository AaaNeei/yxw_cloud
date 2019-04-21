package com.yxw.util;

import com.yxw.base.ResultBase;

import java.util.UUID;


public class TokenUtil {
    /**
     * 用户登录token
     *
     * @return
     */
    public static String getMemberToken() {
        return ResultBase.YXW_MEMBER_TOKEN + "-" + UUID.randomUUID();
    }

    /**
     * 支付token
     *
     * @return
     */
    public static String getPayToken() {
        return ResultBase.YXW_PAY_TOKEN + "-" + UUID.randomUUID();
    }

}
