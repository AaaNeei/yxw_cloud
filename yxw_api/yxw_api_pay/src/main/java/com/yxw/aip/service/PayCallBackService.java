package com.yxw.aip.service;

import com.yxw.base.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 16:00
 * @Description: 支付宝回调接口
 * @return:
 * @throws:
 */
@RestController
@RequestMapping("/callBack")
public interface PayCallBackService {
    /**
     * 支付宝同步回调,直接相应给用户页面通知结果
     *
     * @param params
     * @return
     */
    @RequestMapping("/synCallBack")
    ResponseResult synCallBack(@RequestParam Map<String, String> params);

    /**
     * 支付宝异步回调,通知用户支付结果并处理修改订单操作
     *
     * @param params
     * @return
     */
    @RequestMapping("/asynCallBack")
    String asynCallBack(@RequestParam Map<String, String> params);
}
