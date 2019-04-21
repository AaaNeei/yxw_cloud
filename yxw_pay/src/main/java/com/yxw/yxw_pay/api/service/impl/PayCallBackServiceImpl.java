package com.yxw.yxw_pay.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.yxw.aip.service.PayCallBackService;
import com.yxw.base.ResponseResult;
import com.yxw.base.ResponseResultBase;
import com.yxw.yxw_pay.pay.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 16:05
 * @Description:
 * @return:
 * @throws:
 */
@Slf4j
@Service
public class PayCallBackServiceImpl extends ResponseResultBase implements PayCallBackService {


    @Override
    public ResponseResult synCallBack(@RequestParam Map<String, String> params) {
        try {
            //正常业务回调日志必须存入数据库作为证据保存
            log.info("####同步回调开始####params:", params);
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            if (!signVerified) {
                return setErrorResult("验签失败!");
            }
            return setSuccessResult();
        } catch (Exception e) {
            log.info("######PayCallBackServiceImpl##ERROR:#####", e);
            return setErrorResult("系统错误!");
        } finally {
            log.info("####同步回调结束####params:", params);
        }
    }

    @Override
    public String asynCallBack(@RequestParam Map<String, String> params) {


        return null;
    }
}
