package com.yxw.api;

import com.yxw.base.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 17:38
 * @Description:
 * @return:
 * @throws:
 */
@RestController
@RequestMapping("/order")
public interface OrderService {

    /**
     * 修改订单状态,完成交易
     *
     * @param isPay
     * @param aliPayId
     * @param orderNumber
     * @return
     */
    @RequestMapping("/updateOrderIdInfo")
    ResponseResult updateOrderIdInfo(@RequestParam("isPay") Long isPay, @RequestParam("payId") String aliPayId,
                                     @RequestParam("orderNumber") String orderNumber);
}
