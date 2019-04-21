package com.yxw.yxw_pc.fegin;

import com.yxw.api.OrderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 17:49
 * @Description:
 * @return:
 * @throws:
 */
@Component
@FeignClient("yxw-order")
public interface OrderServiceFegin extends OrderService {
}
