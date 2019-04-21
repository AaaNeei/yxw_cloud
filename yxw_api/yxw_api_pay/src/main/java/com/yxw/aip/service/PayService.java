package com.yxw.aip.service;

import com.yxw.entity.PaymentInfo;
import com.yxw.base.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 12:02
 * @Description:
 * @return:
 * @throws:
 */
@RestController
@RequestMapping("/pay")
public interface PayService {
    /**
     * 创建支付令牌
     *
     * @param PaymentInfo
     * @return
     */
    @RequestMapping("/createPayToken")
    public ResponseResult createToken(@RequestBody PaymentInfo PaymentInfo);

    /**
     * 使用支付令牌查找支付信息
     *
     * @param payToken
     * @return
     */
    @RequestMapping("/findPayToken")
    public ResponseResult findPayToken(@RequestParam("payToken") String payToken);
}
