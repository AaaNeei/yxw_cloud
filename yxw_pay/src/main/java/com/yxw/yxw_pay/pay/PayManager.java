package com.yxw.yxw_pay.pay;

import com.alipay.api.AlipayApiException;
import com.yxw.entity.PaymentInfo;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 12:56
 * @Description:
 * @return:
 * @throws:
 */
public interface PayManager {
    public String payInfo(PaymentInfo paymentInfo) throws AlipayApiException;
}
