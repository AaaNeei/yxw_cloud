package com.yxw.yxw_order.api.service.impl;

import com.yxw.api.OrderService;
import com.yxw.base.ResponseResult;
import com.yxw.base.ResponseResultBase;
import com.yxw.yxw_order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Service
public class OrderServiceImpl extends ResponseResultBase implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseResult updateOrderIdInfo(@RequestParam("isPay") Long isPay, @RequestParam("payId") String aliPayId,
                                            @RequestParam("orderNumber") String orderNumber) {
        int updateOrder = orderMapper.updateOrder(isPay, aliPayId, orderNumber);
        if (updateOrder <= 0) {
            return setErrorResult("系统错误!");
        }
        return setSuccessResult();
    }

}
