package com.yxw.yxw_pay.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.yxw.base.ResultBase;
import com.yxw.entity.PaymentInfo;
import com.yxw.aip.service.PayService;
import com.yxw.base.ResponseResult;
import com.yxw.base.ResponseResultBase;
import com.yxw.util.TokenUtil;
import com.yxw.yxw_pay.mapper.PaymentInfoMapper;
import com.yxw.yxw_pay.pay.PayManager;
import com.yxw.yxw_pay.pay.impl.AliBaBaManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 12:14
 * @Description:
 * @return:
 * @throws:
 */
@Service
public class PayServiceImpl extends ResponseResultBase implements PayService {
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;
    @Autowired
    private AliBaBaManagerImpl aliBaBaManagerImpl;

    @Override
    public ResponseResult createToken(@RequestBody PaymentInfo paymentInfo) {

        //向支付表插入支付数据
        Integer savePaymentType = paymentInfoMapper.savePaymentType(paymentInfo);
        if (savePaymentType < 0) {
            return setErrorResult("支付数据保存失败");
        }
        //生成token
        String payToken = TokenUtil.getPayToken();
        //获取插入数据的id
        Integer id = paymentInfo.getId();
        //存入缓存设置超时时间
        redisUtil.setString(payToken, id, ResultBase.YXW_PAY_TOKEN_TIMEOUT);
        //返回token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payToken", payToken);
        return setSuccessResult(jsonObject);
    }

    @Override
    public ResponseResult findPayToken(@RequestParam("payToken") String payToken) {
        //判断token
        if (StringUtils.isEmpty(payToken)) {
            return setErrorResult("token不能为空");
        }
        //根据token获取支付订单id
        String id = redisUtil.getString(payToken);
        if (StringUtils.isEmpty(id)) {
            return setErrorResult("token过期,支付订单失效");
        }
        //根据支付订单id获取支付订单
        PaymentInfo paymentInfo = paymentInfoMapper.getPaymentInfo(Long.valueOf(id));
        if (paymentInfo == null) {
            return setErrorResult("订单有误,支付失败");
        }
        // 判断类型 调用 具体业务接口
        Long typeId = paymentInfo.getTypeId();
        PayManager payManager = null;
        // 调用支付接口
        if (typeId == 1) {
            payManager = aliBaBaManagerImpl;
        }
        try {
            //调用支付宝接口封装支付参数
            String payHtml = payManager.payInfo(paymentInfo);
            JSONObject payInfoJSON = new JSONObject();
            payInfoJSON.put("payHtml", payHtml);
            //返回生成的html跳转页面
            return setSuccessResult(payInfoJSON);
        } catch (AlipayApiException e) {
            return setErrorResult("支付错误!");
        }

    }
}
