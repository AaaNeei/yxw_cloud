package com.yxw.yxw_pay.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.yxw.aip.service.PayCallBackService;
import com.yxw.base.ResponseResult;
import com.yxw.base.ResponseResultBase;
import com.yxw.base.ResultBase;
import com.yxw.entity.PaymentInfo;
import com.yxw.yxw_pay.fegin.OrderServiceFegin;
import com.yxw.yxw_pay.mapper.PaymentInfoMapper;
import com.yxw.yxw_pay.pay.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;
    @Autowired
    private OrderServiceFegin orderServiceFegin;

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
        // 1.日志记录
        log.info("#####支付宝异步通知synCallBack#####开始,params:{}", params);
        // 2.验签操作
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            log.info("#####支付宝异步通知signVerified:{}######", signVerified);
            if (!signVerified) {
                return ResultBase.YXW_RESULT_PAY_FAIL;
            }
            // 商户订单号
            String outTradeNo = params.get("out_trade_no");
            PaymentInfo paymentInfo = paymentInfoMapper.getByOrderIdPayInfo(outTradeNo);
            if (paymentInfo == null) {
                return ResultBase.YXW_RESULT_PAY_FAIL;
            }
            // 支付宝重试机制
            Integer state = paymentInfo.getState();
            String totalAmount = params.get("total_amount");
            Long price = paymentInfo.getPrice();
            //1为已支付
            if (state == 1) {
                //直接返回解决支付幂等性问题
                //支付宝直到收到success才会停止重试
                return ResultBase.YXW_RESULT_PAY_SUCCESS;
            }
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 判断实际付款金额与商品金额是否一致(查表核实)
            if (!StringUtils.pathEquals(totalAmount, price.toString())) {
                //不一致状态为异常 -1  标识为异常支付
                paymentInfo.setState(-1);
            } else {
                // 标识为已经支付
                paymentInfo.setState(1);
            }
            paymentInfo.setPayMessage(params.toString());
            paymentInfo.setPlatformorderId(tradeNo);

            // 修改 支付表状态
            // 集群情况下zookeeper分布式锁,非集群使用jvm锁 防止程序延时导致支付宝重试出现并发
            //需要事物
            Integer updateResult = paymentInfoMapper.updatePayInfo(paymentInfo);
            if (updateResult <= 0) {
                return ResultBase.YXW_RESULT_PAY_FAIL;
            }

            //需要事物
            // 调用订单接口通知 支付状态
            ResponseResult orderResult = orderServiceFegin.updateOrderIdInfo(Long.valueOf(paymentInfo.getState()), tradeNo, outTradeNo);
            if (!orderResult.getResultCode().equals(ResultBase.YXW_RESULT_SUCCESS_CODE)) {
                // 回滚 手动回滚 rollback
                return ResultBase.YXW_RESULT_PAY_FAIL;
            } // 2PC 3PC TCC MQ
            // 手动 提交 comiit;


            //调用积分服务,修改积分

            //......


            return ResultBase.YXW_RESULT_PAY_SUCCESS;
        } catch (Exception e) {
            log.error("####支付宝异步通知出现异常,ERROR:", e);
            // 回滚 手动回滚 rollback
            return ResultBase.YXW_RESULT_PAY_FAIL;
        } finally {
            log.info("#####支付宝异步通知synCallBack#####结束,params:{}", params);
        }
    }
}
