package com.yxw.yxw_pay.fegin;

import com.yxw.api.OrderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 19:02
 * @Description:
 * @return:
 * @throws:
 */
@Component
@FeignClient("yxw-order")
public interface OrderServiceFegin extends OrderService {
}
