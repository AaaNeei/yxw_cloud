package com.yxw.yxw_pc.controller;

import com.yxw.base.ResponseResult;
import com.yxw.base.ResultBase;
import com.yxw.yxw_pc.fegin.PayCallBackServiceFegin;
import com.yxw.yxw_pc.fegin.PayServiceFegin;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 15:58
 * @Description:
 * @return:
 * @throws:
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {
    private final static String ERROR = "error";
    private final static String PAY_SUCCESS = "pay_success";

    @Autowired
    private PayCallBackServiceFegin payCallBackServiceFegin;
    @Autowired
    private PayServiceFegin payServiceFegin;

    @Value("${yxw.pay.url}")
    private String url;

    /**
     * 使用token 进行支付 跳转到支付页面
     *
     * @param payToken
     * @param response
     * @throws IOException
     */
    @RequestMapping("/aliPay")
    public void aliPay(String payToken, HttpServletResponse response) {
        //保证回显为页面,而不是字符串
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            // 1.参数验证
            if (StringUtils.isEmpty(payToken)) {
                return;
            }
            // 2.调用支付服务接口 获取支付宝html元素
            ResponseResult responseResult = payServiceFegin.findPayToken(payToken);
            if (!StringUtils.pathEquals(responseResult.getResultCode(), ResultBase.YXW_RESULT_SUCCESS_CODE)) {
                String msg = responseResult.getResultMsg();
                writer.println(msg);
                return;
            }
            // 3.返回可以执行的html元素给客户端
            LinkedHashMap data = (LinkedHashMap) responseResult.getData();
            String payHtml = (String) data.get("payHtml");
            log.info("####PayController###payHtml:{}", payHtml);
            // 4. 页面上进行渲染
            writer.println(payHtml);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            writer.close();
        }

    }


    @RequestMapping("/synCallBack")
    public void synCallBack(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            Map<String, String[]> requestParams = request.getParameterMap();
            Map<String, String> params = new HashMap<>();
            log.info("controller层支付参数params:{}", params);
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String[] values = requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                // 乱码解决，这段代码在出现乱码时使用
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            log.info("#####开始#####调用同步回调接口");
            writer = response.getWriter();
            ResponseResult responseResult = payCallBackServiceFegin.synCallBack(params);
            if (!StringUtils.pathEquals(responseResult.getResultCode(), ResultBase.YXW_RESULT_SUCCESS_CODE)) {
                log.error("支付出错,msg:{}", responseResult.getResultMsg());
            }
            log.info("#####结束#####调用同步回调接口");
            // 商户订单号
            String outTradeNo = params.get("out_trade_no");
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 付款金额
            String totalAmount = params.get("total_amount");
            //模仿支付宝,将回调的get请求再次封装为post请求,解决url爆露参数问题
            //封装成html模仿页面重新post提交
            String htmlFrom = "<form name='punchout_form'"
                    + " method='post' action='" + url + "/pay/synSuccessPage' >"
                    + "<input type='hidden' name='outTradeNo' value='" + outTradeNo + "'>"
                    + "<input type='hidden' name='tradeNo' value='" + tradeNo + "'>"
                    + "<input type='hidden' name='totalAmount' value='" + totalAmount + "'>"
                    + "<input type='submit' value='立即支付' style='display:none'>"
                    + "</form><script>document.forms[0].submit();" + "</script>";
            writer.println(htmlFrom);
            log.info("支付完成,商户订单号:{},支付宝交易号:{},付款金额:{}", outTradeNo, tradeNo, totalAmount);
        } catch (Exception e) {
            log.error("系统粗错#####" + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 转发同步回调 ,解决隐藏参数
     *
     * @param model
     * @param outTradeNo
     * @param tradeNo
     * @param totalAmount
     * @return
     */
    @RequestMapping(value = "/synSuccessPage", method = RequestMethod.POST)
    public String synSuccessPage(Model model, String outTradeNo, String tradeNo, String totalAmount) {
        model.addAttribute("outTradeNo", outTradeNo);
        model.addAttribute("tradeNo", tradeNo);
        model.addAttribute("totalAmount", totalAmount);
        return PAY_SUCCESS;
    }

    /**
     * 异步回调
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/asynCallBack")
    public String asynCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> requestParams = request.getParameterMap();
        Map<String, String> params = new HashMap<String, String>();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return null;
    }
}
