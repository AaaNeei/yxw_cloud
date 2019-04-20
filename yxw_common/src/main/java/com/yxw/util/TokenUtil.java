package com.yxw.util;

import com.yxw.base.ResultBase;

import java.util.UUID;


public class TokenUtil {

    public static String getMemberToken() {
        return ResultBase.YXW_MEMBER_TOKEN + "-" + UUID.randomUUID();
    }

}
