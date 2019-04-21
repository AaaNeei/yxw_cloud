package com.yxw.yxw_pc.fegin;

import com.yxw.aip.service.PayService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 15:38
 * @Description:
 * @return:
 * @throws:
 */
@Component
@FeignClient("yxw-pay")
public interface PayServiceFegin extends PayService {
}
